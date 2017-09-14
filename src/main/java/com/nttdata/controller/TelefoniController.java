package com.nttdata.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.database.TelefonoMapper;
import com.nttdata.exception.BadRequestException;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.Telefoni;


@RestController

public class TelefoniController {
	
	@Autowired
	private TelefonoMapper telefonoMapper;

	@RequestMapping(method = RequestMethod.GET, value = "user/{badgeId}/telefono")
	public List<Telefoni> listTelefoni(@PathVariable (value = "badgeId", required = true)int badgeId) {
		List<Telefoni> findAll = telefonoMapper.findAll(badgeId);
		if (findAll != null && findAll.isEmpty())
			throw new ResourceNotFoundException();
		return findAll;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/telefono/{idCell}")
	public Telefoni get(@PathVariable(value = "idCell", required = true) int idCell) {
		Telefoni telefono = telefonoMapper.findByIdCell(idCell);
		if (telefono != null)
			return telefono;
		else
			throw new ResourceNotFoundException();
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/telefono")
	public Telefoni add(@RequestBody Telefoni telefono) {
		
		if (!validateTelefoni(telefono)) {
			throw new BadRequestException();
		}
		Telefoni foundTelefoni = telefonoMapper.findByIdCell(telefono.getIdCell());
		if (foundTelefoni != null)
			throw new ResourceConflictException();

		telefonoMapper.add(telefono);
		return telefono;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/telefono/{idCell}")
	public Telefoni update(@RequestBody Telefoni telefono, @PathVariable(value = "idCell", required = true) int idCell) {
		Telefoni foundTelefoni = telefonoMapper.findByIdCell(idCell);
		if (foundTelefoni == null)
			throw new NoContentException();

		telefono.setIdCell(idCell);
		telefonoMapper.update(telefono);
		return telefono;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/telefono/{idCell}")
	public void delete(@PathVariable(value = "idCell", required = true) int idCell) {
		Telefoni foundTelefoni = telefonoMapper.findByIdCell (idCell);
		if (foundTelefoni == null)
			throw new NoContentException();
		telefonoMapper.delete(idCell);
	}
	
private boolean validateTelefoni(Telefoni telefono) {
    	
		if(StringUtils.isBlank(telefono.getNumero()))
			return false;
		if(StringUtils.isBlank(telefono.getTipo()))
			return false;

		return true;
	}
	
	
	

}
