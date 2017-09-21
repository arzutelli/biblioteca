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

import com.nttdata.database.IndirizziMapper;
import com.nttdata.database.UserMapper;
import com.nttdata.exception.BadRequestException;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.Indirizzi;
import com.nttdata.model.User;

	@RestController
	
public class IndirizziController {
	
		@Autowired
		private IndirizziMapper indirizziMapper;
		
		@Autowired
		private UserMapper userMapper;
		
		
		@RequestMapping(method = RequestMethod.GET, value = "user/{badgeId}/indirizzi")
		public List<Indirizzi> listIndirizzi(@PathVariable(value = "badgeId", required = true) int badgeId,
				@RequestParam(value="citta",required=false) String citta,
	    		@RequestParam(value="provincia",required=false) String provincia,
	    		@RequestParam(value="cap",required=false) String cap){
			
			Indirizzi params = new Indirizzi();
			params.setIdUtente(badgeId);
	    	params.setCitta(citta);
	    	params.setProvincia(provincia);
	    	params.setCap(cap);
			List<Indirizzi> findAll = indirizziMapper.findAll(params);
	    	
			if (findAll != null && findAll.isEmpty())
				throw new ResourceNotFoundException();
			return findAll;
		}
				
		
		@RequestMapping(method = RequestMethod.GET, value = "user/{badgeId}/indirizzi/{idIndirizzi}")
		public Indirizzi get(@PathVariable(value = "idIndirizzi", required = true) int idIndirizzi, @PathVariable(value = "badgeId", required = true) int badgeId) {
			Indirizzi indirizzi = indirizziMapper.findByIdIndirizzi(idIndirizzi, badgeId);
			if (indirizzi != null)
				return indirizzi;
			else
				throw new ResourceNotFoundException();
		}
		
		@RequestMapping(method = RequestMethod.POST, value = "user/{badgeId}/indirizzi")
		public Indirizzi add(@RequestBody Indirizzi indirizzi,
				@PathVariable(value = "badgeId", required = true) int badgeId) {
			
			 if(!validateIndirizzi(indirizzi)){
			        throw new BadRequestException();
			        }
			
			User findByBadgeId = userMapper.findByBadgeId(badgeId);
			if (findByBadgeId == null)
				throw new ResourceNotFoundException("L'utente a cui vorresti aggiunger un nuovo indirizzo non esiste");
			
			Indirizzi foundIndirizzi = indirizziMapper.findByIdIndirizzi(indirizzi.getIdIndirizzi(), badgeId);
			if (foundIndirizzi != null)
				throw new ResourceConflictException();
			
			indirizzi.setIdUtente(badgeId);
			indirizziMapper.add(indirizzi);
			return indirizzi;
		}
		
		
		@RequestMapping(method = RequestMethod.PUT, value = "user/{badgeId}/indirizzi/{idIndirizzi}")
		public Indirizzi update(@RequestBody Indirizzi indirizzi, @PathVariable(value = "idIndirizzi", required = true) int idIndirizzi, @PathVariable(value = "badgeId", required = true) int badgeId) {
		Indirizzi foundIndirizzi = indirizziMapper.findByIdIndirizzi (idIndirizzi, badgeId);
		
		if(!validateIndirizzi(indirizzi)){
	        throw new BadRequestException();
	        }
		
			if (foundIndirizzi == null)
				throw new NoContentException();
			
			indirizzi.setIdUtente(badgeId);
			indirizzi.setIdIndirizzi(idIndirizzi);
			indirizziMapper.update(indirizzi);
			return indirizzi;
		}
		
		@RequestMapping(method = RequestMethod.DELETE, value = "user/{badgeId}/indirizzi/{idIndirizzi}")
		public void delete(@PathVariable(value = "idIndirizzi", required = true) int idIndirizzi, @PathVariable(value = "badgeId", required = true) int badgeId) {
			Indirizzi foundIndirizzi = indirizziMapper.findByIdIndirizzi(idIndirizzi, badgeId);
			if (foundIndirizzi == null)
				throw new NoContentException();
			indirizziMapper.delete(idIndirizzi);
		}
	
		private boolean validateIndirizzi(Indirizzi indirizzi) {
	    	
			if(StringUtils.isBlank(indirizzi.getVia()))
				return false;
			if(StringUtils.isBlank(indirizzi.getCitta()))
				return false;
			if(StringUtils.isBlank(indirizzi.getProvincia()))
				return false;

			
			return true;
		}

}
