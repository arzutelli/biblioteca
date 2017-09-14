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
		
		@RequestMapping(method = RequestMethod.GET, value = "/indirizzi/{idIndirizzi}")
		public Indirizzi get(@PathVariable(value = "idIndirizzi", required = true) int idIndirizzi) {
			Indirizzi indirizzi = indirizziMapper.findByIdIndirizzi(idIndirizzi);
			if (indirizzi != null)
				return indirizzi;
			else
				throw new ResourceNotFoundException();
		}
		
		@RequestMapping(method = RequestMethod.POST, value = "/indirizzi")
		public Indirizzi add(@RequestBody Indirizzi indirizzi) {
			Indirizzi foundIndirizzi = indirizziMapper.findByIdIndirizzi(indirizzi.getIdIndirizzi());
			if (foundIndirizzi != null)
				throw new ResourceConflictException();

			indirizziMapper.add(indirizzi);
			return indirizzi;
		}
		
		
		@RequestMapping(method = RequestMethod.PUT, value = "/indirizzi/{idIndirizzi}")
		public Indirizzi update(@RequestBody Indirizzi indirizzi, @PathVariable(value = "idIndirizzi", required = true) int idIndirizzi) {
		Indirizzi foundIndirizzi = indirizziMapper.findByIdIndirizzi (idIndirizzi);
			if (foundIndirizzi == null)
				throw new NoContentException();

			indirizzi.setIdIndirizzi(idIndirizzi);
			indirizziMapper.update(indirizzi);
			return indirizzi;
		}
		
		@RequestMapping(method = RequestMethod.DELETE, value = "/indirizzi/{idIndirizzi}")
		public void delete(@PathVariable(value = "idIndirizzi", required = true) int idIndirizzi) {
			Indirizzi foundIndirizzi = indirizziMapper.findByIdIndirizzi (idIndirizzi);
			if (foundIndirizzi == null)
				throw new NoContentException();
			indirizziMapper.delete(idIndirizzi);
		}
	

}
