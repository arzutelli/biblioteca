package com.nttdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.database.IndirizziMapper;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.Indirizzi;

	@RestController
	
public class IndirizziController {
	
		@Autowired
		private IndirizziMapper indirizziMapper;
		
		
		@RequestMapping(method = RequestMethod.GET, value = "/indirizzi")
		public List<Indirizzi> listLibr() {
			List<Indirizzi> findAll = indirizziMapper.findAll();
			if (findAll != null && findAll.isEmpty())
				throw new ResourceNotFoundException();
			return findAll;
		}
		
		@RequestMapping(method = RequestMethod.GET, value = "/indirizzi/{badgeId}")
		public Indirizzi get(@PathVariable(value = "badgeId", required = true) String badgeId) {
			Indirizzi indirizzi = indirizziMapper.findByBadgeId(badgeId);
			if (indirizzi != null)
				return indirizzi;
			else
				throw new ResourceNotFoundException();
		}
		
		@RequestMapping(method = RequestMethod.POST, value = "/indirizzi")
		public Indirizzi add(@RequestBody Indirizzi indirizzi) {
			Indirizzi foundIndirizzi = indirizziMapper.findByBadgeId(indirizzi.getBadgeId());
			if (foundIndirizzi != null)
				throw new ResourceConflictException();

			indirizziMapper.add(indirizzi);
			return indirizzi;
		}
		
		
		@RequestMapping(method = RequestMethod.PUT, value = "/indirizzi/{badgeId}")
		public Indirizzi update(@RequestBody Indirizzi indirizzi, @PathVariable(value = "badgeId", required = true) String badgeId) {
		Indirizzi foundIndirizzi = indirizziMapper.findByBadgeId (badgeId);
			if (foundIndirizzi == null)
				throw new NoContentException();

			indirizzi.setBadgeId(badgeId);
			indirizziMapper.update(indirizzi);
			return indirizzi;
		}
		
		@RequestMapping(method = RequestMethod.DELETE, value = "/indirizzi/{badgeId}")
		public void delete(@PathVariable(value = "badgeId", required = true) String badgeId) {
			Indirizzi foundIndirizzi = indirizziMapper.findByBadgeId (badgeId);
			if (foundIndirizzi == null)
				throw new NoContentException();
			indirizziMapper.delete(badgeId);
		}
	

}
