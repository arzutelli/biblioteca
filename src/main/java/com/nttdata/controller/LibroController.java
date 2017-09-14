package com.nttdata.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.database.LibroMapper;
import com.nttdata.exception.BadRequestException;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.Libro;

@RestController
public class LibroController {

	@Autowired
	private LibroMapper libroMapper;

	@RequestMapping(method = RequestMethod.GET, value = "/libro")
	public List<Libro> listLibr() {
		List<Libro> findAll = libroMapper.findAll();
		if (findAll != null && findAll.isEmpty())
			throw new ResourceNotFoundException();
		return findAll;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/libro/{idLibro}")
	public Libro get(@PathVariable(value = "idLibro", required = true) int idLibro) {
		Libro libro = libroMapper.findByIdLibro(idLibro);
		if (libro != null)
			return libro;
		else
			throw new ResourceNotFoundException();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/libro")
	public Libro add(@RequestBody Libro libro) {
		
	    if(!validateLibro(libro)){
	        throw new BadRequestException();
	        }

		
		Libro foundLibro = libroMapper.findByIdLibro(libro.getIdLibro());
		if (foundLibro != null)
			throw new ResourceConflictException();

		libroMapper.add(libro);
		return libro;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/libro/{idLibro}")
	public Libro update(@RequestBody Libro libro, @PathVariable(value = "idLibro", required = true) int idLibro) {
		Libro foundLibro = libroMapper.findByIdLibro(idLibro);
		if (foundLibro == null)
			throw new NoContentException();

		libro.setIdLibro(idLibro);
		libroMapper.update(libro);
		return libro;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/libro/{idLibro}")
	public void delete(@PathVariable(value = "idLibro", required = true) int idLibro) {
		Libro foundLibro = libroMapper.findByIdLibro(idLibro);
		if (foundLibro == null)
			throw new NoContentException();
		libroMapper.delete(idLibro);
	}
	
private boolean validateLibro(Libro libro) {
    	
		if(StringUtils.isBlank(libro.getTitolo()))
			return false;
		if(StringUtils.isBlank(libro.getGenere()))
			return false;
		if(libro.getPrezzo().compareTo(new BigDecimal(0))<=0)
			return false;
		if(StringUtils.isBlank(libro.getScaffale()))
			return false;
		
		return true;
	}
}



