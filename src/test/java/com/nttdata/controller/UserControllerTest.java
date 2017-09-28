/**
 * 
 */
package com.nttdata.controller;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.nttdata.model.User;

/**
 * @author LoeschTh
 *
 */
public class UserControllerTest {
	private static final String baseUrl = "http://localhost:8080";
	

	@Test // indica che il metodo è un Test di JUnit (Framework JUnit)
	public void test() {
		String URL = baseUrl+"/user";
		
		RestTemplate restTemplate = new RestTemplate();
		
		User[] users = restTemplate.getForObject(URL, User[].class);
		
		/**
		 * asserzioni per la verifica dell'esito del test
		 * se anche una sola asserzione è false il test è fallito
		 */
		assertTrue(users!=null);
		assertTrue(users.length > 0);
		
	}

	
	@Test
	public void testPost() {
		String URL = baseUrl+"/user";
		
		RestTemplate restTemplate = new RestTemplate();
		
		User utenteInput = new User();
		utenteInput.setSurname("Thomas");
		utenteInput.setName("Loesch");
		utenteInput.setEmail("thomas.loesch@nttdata.com");
		utenteInput.setDataNascita(new Date());
		
		/**
		 * metodo per creare l'header per il content-type:Application-json
		 * necessario per la corretta lettura della request
		 */
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		/**
		 * creazione della request comprensiva di body (utenteInput) e headers
		 */
		HttpEntity<User> request = new HttpEntity<User>(utenteInput ,headers);
		
		/**
		 * invocazione del metodo post sulla risorsa identificata da URL
		 * con User come tipo di ritorno della risposta
		 */
		User user = restTemplate.postForObject(URL, request , User.class);
		
		assertTrue(user!=null);
		assertTrue(user.getBadgeId() != 0);
		
		/**
		 * ricerca dell'utente appena inserito per successiva veifica di corretto inserimento
		 */
		User userGet = restTemplate.getForObject(URL+"/"+user.getBadgeId(), User.class);
		assertTrue(userGet!=null);
		
		/**
		 * è stato necessario effettuare l'override del metodo equals per fare in modo
		 * che gli oggetti siano uguali anche se sono istanze diverse 
		 * è stata ignorata volutamente la property eta perchè calcolata a runtime solo 
		 * in risposta al metodo get e non alla post
		 */
		assertTrue(userGet.equals(user));
		
	}
	
	
	
	
	@Test
	public void testPut() {
		String URL = baseUrl+"/user";
		
		RestTemplate restTemplate = new RestTemplate();
		
		User request = new User();
		request.setSurname("Maynor");
		request.setName("Arzù");
		request.setEmail("maynor.arzu@nttdata.com");
		request.setDataNascita(new Date());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<User> entity = new HttpEntity<User>(request ,headers);
		
		User user = restTemplate.postForObject(URL, entity , User.class);
		
		assertTrue(user!=null);
		assertTrue(user.getBadgeId() != 0);
		
		user.setSurname("normay");
		user.setName("zuar");
		user.setEmail("zuar.normay@nttdata.com");
		
		HttpHeaders headersPut = new HttpHeaders();
		headersPut.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<User> entityPut = new HttpEntity<User>(user ,headersPut);
		
		restTemplate.put(URL+"/"+user.getBadgeId(),  entityPut , User.class);
		
		User usernew = restTemplate.getForObject(URL+"/"+user.getBadgeId(), User.class);
		assertTrue(usernew!=null);
		
		assertTrue(usernew.equals(user));
		
		
	}
	
	

	@Test
	public void testDelete() {
		String URL = baseUrl+"/user";
		
		RestTemplate restTemplate = new RestTemplate();
		
		User request = new User();
		request.setSurname("Thomas");
		request.setName("Loesch");
		request.setEmail("thomas.loesch@nttdata.com");
		request.setDataNascita(new Date());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<User> entity = new HttpEntity<User>(request ,headers);
		
		User user = restTemplate.postForObject(URL, entity , User.class);
		
		assertTrue(user!=null);
		assertTrue(user.getBadgeId() != 0);
		
		restTemplate.delete(URL+"/"+user.getBadgeId());
		
		try {
			restTemplate.getForObject(URL+"/"+user.getBadgeId(), User.class);
	    }
	    catch (final HttpClientErrorException e) {
	    	assertTrue(e.getStatusCode().equals(HttpStatus.NOT_FOUND));
	    }
		
		
		
	}
	
	
}
