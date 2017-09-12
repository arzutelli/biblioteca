package com.nttdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.database.AutoreMapper;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.Autore;

@RestController
public class AutoreController {

	@Autowired
	private AutoreMapper autoreMapper;

	@RequestMapping(method = RequestMethod.GET, value = "/autore")
	public List<Autore> listAutore() {
		List<Autore> findAll = autoreMapper.findAll();
		if (findAll != null && findAll.isEmpty())
			throw new ResourceNotFoundException();
		return findAll;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/autore/{idAutore}")
	public Autore get(@PathVariable(value = "idAutore", required = true) String idAutore) {
		Autore autore = autoreMapper.findByIdAutore(idAutore);
		if (autore != null)
			return autore;
		
		else
			throw new ResourceNotFoundException();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/autore")
	public Autore add(@RequestBody Autore autore) {
		Autore foundAutore = autoreMapper.findByIdAutore(autore.getIdAutore());
		if (foundAutore != null)
			throw new ResourceConflictException();

		autoreMapper.add(autore);
		return autore;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/autore/{idAutore}")
	public Autore update(@RequestBody Autore autore, @PathVariable(value = "idAutore", required = true) String idAutore) {
		Autore foundAutore = autoreMapper.findByIdAutore(idAutore);
		if (foundAutore == null)
			throw new NoContentException();

		autore.setIdAutore(idAutore);
		autoreMapper.update(autore);
		return autore;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/autore/{idAutore}")
	public void delete(@PathVariable(value = "idAutore", required = true) String idAutore) {
		Autore foundAutore = autoreMapper.findByIdAutore(idAutore);
		if (foundAutore == null)
			throw new NoContentException();
		autoreMapper.delete(idAutore);
	}

}
