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
import com.nttdata.database.UserMapper;
import com.nttdata.exception.BadRequestException;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.Telefoni;
import com.nttdata.model.User;


@RestController

public class TelefoniController {
	
	@Autowired
	private TelefonoMapper telefonoMapper;
	
	@Autowired
	private UserMapper userMapper;

	@RequestMapping(method = RequestMethod.GET, value = "user/{badgeId}/telefono")
	public List<Telefoni> listTelefoni(@PathVariable (value = "badgeId", required = true)int badgeId) {
		List<Telefoni> findAll = telefonoMapper.findAll(badgeId);
		if (findAll != null && findAll.isEmpty())
			throw new ResourceNotFoundException();
		return findAll;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "user/{badgeId}/telefono/{idCell}")
	public Telefoni get(@PathVariable(value = "idCell", required = true) int idCell, @PathVariable(value = "badgeId", required = true) int badgeId ) {
		Telefoni telefono = telefonoMapper.findByIdCell(idCell, badgeId);
		if (telefono != null)
			return telefono;
		else
			throw new ResourceNotFoundException();
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "user/{badgeId}/telefono")
	public Telefoni add(@RequestBody Telefoni telefono, 
			@PathVariable (value= "badgeId", required=true) int badgeId) {
		
		User findByBadgeId = userMapper.findByBadgeId(badgeId);
		if(findByBadgeId == null)
			throw new ResourceNotFoundException("utente non esiste");
		
		if (!validateTelefoni(telefono)) {
			throw new BadRequestException();
		}
		Telefoni foundTelefoni = telefonoMapper.findByIdCell(telefono.getIdCell(), badgeId); //TODO:modificare per verificare con numero di telefono
		if (foundTelefoni != null)
			throw new ResourceConflictException();

		telefono.setIdUtente(badgeId);
		telefonoMapper.add(telefono);
		return telefono;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "user/{badgeId}/telefono/{idCell}")
	public Telefoni update(@RequestBody Telefoni telefono, @PathVariable(value = "idCell", required = true) int idCell,@PathVariable(value = "badgeId", required = true) int badgeId) {
		Telefoni foundTelefoni = telefonoMapper.findByIdCell(idCell, badgeId);
		if (foundTelefoni == null)
			throw new ResourceNotFoundException("Telefono che si sta cercando non esiste");

		telefono.setIdCell(idCell);
		telefono.setIdUtente(badgeId);
		telefonoMapper.update(telefono);
		return telefono;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "user/{badgeId}/telefono/{idCell}")
	public void delete(@PathVariable(value = "idCell", required = true) int idCell, @PathVariable(value = "badgeId", required = true) int badgeId) {
		Telefoni foundTelefoni = telefonoMapper.findByIdCell (idCell, badgeId);
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
