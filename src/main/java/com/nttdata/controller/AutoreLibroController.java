package com.nttdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.database.AutoreLibroMapper;
import com.nttdata.database.AutoreMapper;
import com.nttdata.database.LibroMapper;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.Autore;
import com.nttdata.model.AutoreLibro;
import com.nttdata.model.Libro;

@RestController
public class AutoreLibroController {

	@Autowired
	private AutoreLibroMapper autoreLibroMapper;

	@Autowired
	private AutoreMapper autoreMapper;
	
	@Autowired
	private LibroMapper libroMapper;

	@RequestMapping(method = RequestMethod.POST, value = "autore/{idAutore}/autoreLibro")
	public AutoreLibro add(@RequestBody AutoreLibro autoreLibro,
			@PathVariable(value = "idAutore", required = true) int idAutore,
			@PathVariable(value = "idLibro", required = true) int idLibro) {

		Autore findByIdAutore = autoreMapper.findByIdAutore(idAutore);
		if (findByIdAutore == null)
			throw new ResourceNotFoundException("l'autore non esiste");
		

		Libro findByIdLibro = libroMapper.findByIdLibro(idLibro);
		if (findByIdLibro == null)
			throw new ResourceNotFoundException("il libro non esiste");

		AutoreLibro foundAutoreLibro = autoreLibroMapper.findByIdAutoreIdLibro(autoreLibro.getIdAutore(),
				autoreLibro.getIdLibro());
		if (foundAutoreLibro != null)
			throw new ResourceConflictException();

		autoreLibroMapper.add(autoreLibro);
		return autoreLibro;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "autore/{idAutore}/autoreLibro/{idLibro}")
	public void delete(@PathVariable(value = "idAutore", required = true) int idAutore,
			@PathVariable(value = "idLibro", required = true) int idLibro) {
		AutoreLibro foundAutoreLibro = autoreLibroMapper.findByIdAutoreIdLibro(idAutore, idLibro);
		if (foundAutoreLibro == null)
			throw new NoContentException();
		autoreLibroMapper.delete(idAutore, idLibro);
	}

	public AutoreMapper getAutoreMapper() {
		return autoreMapper;
	}

	public void setAutoreMapper(AutoreMapper autoreMapper) {
		this.autoreMapper = autoreMapper;
	}

}
