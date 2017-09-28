package com.nttdata.controller;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nttdata.database.UserMapper;
import com.nttdata.exception.BadRequestException;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.Indirizzi;
import com.nttdata.model.Noleggio;
import com.nttdata.model.Telefoni;
import com.nttdata.model.User;
import com.nttdata.utils.Utils;

//indica che la classe è un controller per servizi REST (Framework SPRING WEB)
@RestController 
public class UserController {

	private static final String baseUrl = "http://localhost:8080";
	
	@Autowired // gestisce il dependecy injection di Spring ignettando l'istanza dello UserMapper (Framework SPRING CORE)
	private UserMapper userMapper;

	/**
	 * Metodo che gestisce la ricerca di utenti su URL /user
	 * @param name       parametro di ricerca opzionale
	 * @param surname    parametro di ricerca opzionale
	 * @param email      parametro di ricerca opzionale
	 * @param minEta     parametro di ricerca opzionale
	 * @param citta      parametro di ricerca opzionale
	 * @param numero     parametro di ricerca opzionale
	 * @return la lista degli utenti corrispondenti ai filtri di ricerca
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user") // gestisce il matching dell'http method + l'url (risorsa) e il metodo java che contiene la logica 
	public List<User> find(
			// parametri inseriti nella richiesta nell'url
			// required = false <-- parametro non obbligatorio
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surname", required = false) String surname,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "minEta", required = false) Integer minEta,
			@RequestParam(value = "citta", required = false) String citta,
			@RequestParam(value = "numero", required = false) String numero
			) {

		Map<String, Object> params = new HashMap<>(); //dichiarazione della mappa con i parametri di ricerca (Database)

		User utente = new User();
		utente.setName(name);
		utente.setSurname(surname);
		utente.setEmail(email);

		params.put("user", utente);

		Indirizzi ind = new Indirizzi();
		ind.setCitta(citta);

		params.put("indirizzo", ind);

		Telefoni telefono = new Telefoni();
		telefono.setNumero(numero);

		params.put("telefono", telefono);

		//Ricerca su database tramite parametri usando la liberia Mybatis
		List<User> findAll = userMapper.findByParams(params);

		List<User> filtered = new ArrayList<>();

		// filtro la lista findAll sulla base dell'eta 
		for (User u : findAll) {
			u.setEta(Utils.getEta(u.getDataNascita()));
			// se il parametro minEta e' valorizzato allora eseguo il controllo per il filtro
			if (minEta != null) {
				if (u.getEta() >= minEta) {
					filtered.add(u);
				}
				// se non il parametro minEta non e' valorizzato allora non eseguo il controllo per il filtro e aggiungo tutti gli utenti
			} else {
				filtered.add(u);
			}

		}

		//se la lista finale da restituire è vuota
		if (filtered.isEmpty())
			throw new ResourceNotFoundException(); //eccezione per restituire errore 404

		return filtered; // risponde con la lista degli utenti e http status 200
	}

	
	/**
	 * ricerca tramite like (i parametri contengono la stringa mandata in input) su vari parametri dell'utente 
	 * @param query unico parametro di ricerca
	 * @return la lista degli utenti corrispondenti al parametro di ricerca
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/search")
	public List<User> find(@RequestParam(value = "query", required = false) String query){
		
		List<User> findAll = userMapper.findByQuery(query);
		
		for (User u : findAll) {
			u.setEta(Utils.getEta(u.getDataNascita()));
		}
		
		if (findAll != null && findAll.isEmpty())
			throw new ResourceNotFoundException();
		
		return findAll;
	}
	
	/**
	 * Metodo per il recupero di un utente specifico
	 * @param badgeId identificativo dell'utente
	 * @return l'utente corrispondente al badge id
	 * @throws ResourceNotFoundException se non trova un utente
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/{badgeId}")
	public User get(
			//associazione tra il parametro del metodo badgeID e la variabile specificata nel path dell'URL
			@PathVariable(value = "badgeId", required = true) int badgeId 
			) {
		
		
		User user = userMapper.findByBadgeId(badgeId);
		if (user != null) {
			//COMPOSIZIONE DI SERVIZI
			//recupero gli indirizzi utilizzando il servizio di get degli indirizzi di un utente
			RestTemplate restTemplate = new RestTemplate(); // strumento per chiamare servizi REST
			String URL = MessageFormat.format("{0}/user/{1}/indirizzi", baseUrl,badgeId); // creo il link senza utilizzare la concatenazione tra stringhe (poco efficiente)
			/**
			 * richiama il servizio di ricerca degli indirizzi di un utente (http method GET)
			 * restituisce un array di Indirizzi il primo parametro è l'URL il secondo è il tipo di oggetto che 
			 * ci si aspetta come ritorno
			 */
			Indirizzi[] indirizzi = restTemplate.getForObject(URL, Indirizzi[].class); 
			// Trasforma l'array in una lista tramite la classe Arrays
			user.setIndirizzi(Arrays.asList(indirizzi));
			
			URL = MessageFormat.format("{0}/user/{1}/telefono", baseUrl,badgeId);
			Telefoni[] telefoni = restTemplate.getForObject(URL, Telefoni[].class);
			user.setTelefoni(Arrays.asList(telefoni));

			user.setEta(Utils.getEta(user.getDataNascita()));
			
			
			return user;
		}
		else
			throw new ResourceNotFoundException();
	}

	
	/**
	 * Metodo che gestisce l'inserimento di un nuovo utente (HTTP method POST)
	 * @param user utente da inserire
	 * @return l'utente inserito con valorizzato il badgeId (assegnato automaticamente)
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public User add(
			//mapping tra il body della request con contentType:application/json e l'oggetto User
			@RequestBody User user
			) {

		if (!validateUser(user)) {
			throw new BadRequestException();
		}

		User foundUser = userMapper.findByBadgeId(user.getBadgeId());
		if (foundUser != null)
			throw new ResourceConflictException();

		// aggiunge sul database un Utente
		userMapper.add(user);
		return user;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/user/{badgeId}")
	public User update(@RequestBody User user, @PathVariable(value = "badgeId", required = true) int badgeId) {

		if (!validateUser(user)) {
			throw new BadRequestException();
		}

		User foundUser = userMapper.findByBadgeId(badgeId);
		if (foundUser == null)
			throw new NoContentException();

		// setta all'iterno dell'utente inviato nel body della richiesta il valore del badgeId preso dall'URL
		user.setBadgeId(badgeId);
		
		// aggiorna l'utente sul database
		userMapper.update(user);
		return user;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{badgeId}")
	public void delete(@PathVariable(value = "badgeId", required = true) int badgeId) {
		User foundUser = userMapper.findByBadgeId(badgeId);
		if (foundUser == null)
			throw new NoContentException();
		
		/**
		 * COMPOSIZIONE DI SERVIZI
		 * cancella tutte le entita' associate all'utente che manderebbero in errore la cancellazione dell'utente
		 * a causa di vincoli di integrita' referenziale violati (DATABASE)
		 */
		// TROVA TUTTI GLI INDIRIZZI ASSOCIATI AD UN UTENTE
		RestTemplate template = new RestTemplate();
		String URL = MessageFormat.format("{0}/user/{1}/indirizzi", baseUrl,badgeId);
		Indirizzi[] indirizzi = template.getForObject(URL, Indirizzi[].class);
		
		//CANCELLA TUTTI GLI INDIRIZZI ASOCIATI AD UN UTENTE
		for(Indirizzi i : Arrays.asList(indirizzi)) {
			URL = MessageFormat.format("{0}/user/{1}/indirizzi/{2}", baseUrl,badgeId,i.getIdIndirizzi());
			template.delete(URL);
		}
		
		URL = MessageFormat.format("{0}/user/{1}/telefono", baseUrl,badgeId);
		Telefoni[] telefoni = template.getForObject(URL, Telefoni[].class);
		
		for(Telefoni t : Arrays.asList(telefoni)) {
			URL = MessageFormat.format("{0}/user/{1}/telefono/{2}", baseUrl,badgeId,t.getIdCell());
			template.delete(URL);
		}
		
		URL = MessageFormat.format("{0}/user/{1}/noleggio", baseUrl, badgeId);
		Noleggio[] noleggi = template.getForObject(URL, Noleggio[].class);
	
		for(Noleggio n : Arrays.asList(noleggi)) {
			URL = MessageFormat.format("{0}/user/{1}/noleggio/{2}", baseUrl, badgeId, n.getIdNoleggio());
		template.delete(URL);
		}
		
		// cancella l'utente dopo aver cancellato tutte le entita' a lui associate
		userMapper.delete(badgeId);
	}

	
	// verifica che l'input inserito nel body di chiamate in http method POST / PUT sia corretto
	private boolean validateUser(User user) {

		if (StringUtils.isBlank(user.getName())) //metodo di utilita' per la verifica not null e not blank
			return false;
		if (StringUtils.isBlank(user.getSurname()))//metodo di utilita' per la verifica not null e not blank
			return false;
		if (StringUtils.isBlank(user.getEmail()))//metodo di utilita' per la verifica not null e not blank
			return false;
		return true;
	}

}
