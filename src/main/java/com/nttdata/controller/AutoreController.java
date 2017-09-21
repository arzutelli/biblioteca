package com.nttdata.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.database.AutoreMapper;
import com.nttdata.exception.BadRequestException;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.Autore;
import com.nttdata.model.Libro;



@RestController
public class AutoreController {

	@Autowired
	private AutoreMapper autoreMapper;
	

	@RequestMapping(method = RequestMethod.GET, value = "/autore")
	public List<Autore> listAutore(@RequestParam(value="nome",required=false) String nome,
    		@RequestParam(value="cognome",required=false) String cognome,
    		@RequestParam(value="email",required=false) String email) {	
		
		Autore params = new Autore();
    	params.setNome(nome);
    	params.setCognome(cognome);
    	params.setEmail(email);
    	List<Autore> findAll = autoreMapper.findAll(params);

		if (findAll != null && findAll.isEmpty())
			throw new ResourceNotFoundException();
		return findAll;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/autore/{idAutore}")
	public Autore get(@PathVariable(value = "idAutore", required = true) int idAutore) {
		Autore autore = autoreMapper.findByIdAutore(idAutore);
		if (autore != null)
			return autore;

		else
			throw new ResourceNotFoundException();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/autore")
	public Autore add(@RequestBody Autore autore) {

		if (!validateAutore(autore)) {
			throw new BadRequestException();
		}

		Autore foundAutore = autoreMapper.findByIdAutore(autore.getIdAutore());
		if (foundAutore != null)
			throw new ResourceConflictException();

		autoreMapper.add(autore);
		return autore;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/autore/{idAutore}")
	public Autore update(@RequestBody Autore autore,
			@PathVariable(value = "idAutore", required = true) int idAutore) {
		
		if (!validateAutore(autore)) {
			throw new BadRequestException();
		}
		
		Autore foundAutore = autoreMapper.findByIdAutore(idAutore);		
		if (foundAutore == null)
			throw new NoContentException();

		autore.setIdAutore(idAutore);
		autoreMapper.update(autore);
		return autore;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/autore/{idAutore}")
	public void delete(@PathVariable(value = "idAutore", required = true) int idAutore) {
		Autore foundAutore = autoreMapper.findByIdAutore(idAutore);
		if (foundAutore == null)
			throw new NoContentException();
		autoreMapper.delete(idAutore);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "autore/{idAutore}/libro")
	public List<Libro> listLibro(@PathVariable(value = "idAutore", required = true) int idAutore) {
	
		List<Libro> findAll = autoreMapper.findLibri(idAutore);
		if (findAll != null && findAll.isEmpty())
			throw new ResourceNotFoundException();
		return findAll;
	}
	
	
private boolean validateAutore(Autore autore) {
    	
		if(StringUtils.isBlank(autore.getNome()))
			return false;
		if(StringUtils.isBlank(autore.getCognome()))
			return false;
		if(StringUtils.isBlank(autore.getEmail()))
			return false;

		return true;
	}

}
