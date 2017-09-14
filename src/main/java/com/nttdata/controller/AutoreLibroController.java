package com.nttdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.database.AutoreLibroMapper;
import com.nttdata.exception.BadRequestException;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.AutoreLibro;


@RestController
public class AutoreLibroController {

	@Autowired
	private AutoreLibroMapper autoreLibroMapper;

	@RequestMapping(method = RequestMethod.GET, value = "/autoreLibro")
	public List<AutoreLibro> listAutore() {
		List<AutoreLibro> findAll = autoreLibroMapper.findAll();
		if (findAll != null && findAll.isEmpty())
			throw new ResourceNotFoundException();
		return findAll;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/autoreLibro/{idAutore}")
	public AutoreLibro get(@PathVariable(value = "idAutore", required = true) int idAutore) {
		AutoreLibro autoreLibro = autoreLibroMapper.findByIdAutore(idAutore);
		if (autoreLibro != null)
			return autoreLibro;
		else
			throw new ResourceNotFoundException();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/autoreLibro")
	public AutoreLibro add(@RequestBody AutoreLibro autoreLibro) {
		

		if (!validateAutoreLibro(autoreLibro)) {
			throw new BadRequestException();
		}
		
		AutoreLibro foundAutoreLibro = autoreLibroMapper.findByIdAutoreIdLibro(autoreLibro.getIdAutore(),
				autoreLibro.getIdLibro());
		if (foundAutoreLibro != null)
			throw new ResourceConflictException();

		autoreLibroMapper.add(autoreLibro);
		return autoreLibro;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/autoreLibro/{idAutore}/{idLibro}")
	public void delete(@PathVariable(value = "idAutore", required = true) int idAutore,
			@PathVariable(value = "idLibro", required = true) int idLibro) {
		AutoreLibro foundAutoreLibro = autoreLibroMapper.findByIdAutoreIdLibro(idAutore, idLibro);
		if (foundAutoreLibro == null)
			throw new NoContentException();
		autoreLibroMapper.delete(idAutore, idLibro);
	}
	
private boolean validateAutoreLibro(AutoreLibro autoreLibro) {
    	
	//	if(NumberUtils.(autoreLibro.getIdLibro())
		//	return false; 
	//	if(StringUtils.(autoreLibro.getIdLibro()))
		//	return false;

		
		return true;
	}

}
