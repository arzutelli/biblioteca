package com.nttdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.database.NoleggioMapper;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.Noleggio;


@RestController

public class NoleggioController {
	
	@Autowired
	private NoleggioMapper noleggioMapper;
	
	@RequestMapping(method = RequestMethod.GET, value = "/noleggio")
	public List<Noleggio> listNoleggio() {
		List<Noleggio> findAll = noleggioMapper.findAll();
		if (findAll != null && findAll.isEmpty())
			throw new ResourceNotFoundException();
		return findAll;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/noleggio/{idNoleggio}")
	public Noleggio get(@PathVariable(value = "idNoleggio", required = true) int idNoleggio) {
		Noleggio noleggio = noleggioMapper.findByIdNoleggio(idNoleggio);
		if (noleggio != null)
			return noleggio;
		else
			throw new ResourceNotFoundException();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/noleggio")
	public Noleggio add(@RequestBody Noleggio noleggio) {
		Noleggio foundNoleggio = noleggioMapper.findByIdNoleggio(noleggio.getIdNoleggio());
		if (foundNoleggio != null)
			throw new ResourceConflictException();

		noleggioMapper.add(noleggio);
		return noleggio;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/noleggio/{idNoleggio}")
	public Noleggio update(@RequestBody Noleggio noleggio, @PathVariable(value = "idNoleggio", required = true) int idNoleggio) {
		Noleggio foundNoleggio = noleggioMapper.findByIdNoleggio (idNoleggio);
		if (foundNoleggio == null)
			throw new NoContentException();

		noleggio.setIdNoleggio(idNoleggio);
		noleggioMapper.update(noleggio);
		return noleggio;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/noleggio/{idNoleggio}")
	public void delete(@PathVariable(value = "idNoleggio", required = true) int idNoleggio) {
		Noleggio foundNoleggio = noleggioMapper.findByIdNoleggio (idNoleggio);
		if (foundNoleggio == null)
			throw new NoContentException();
		noleggioMapper.delete(idNoleggio);
	}

}
