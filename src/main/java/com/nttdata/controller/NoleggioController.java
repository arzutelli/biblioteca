package com.nttdata.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.database.NoleggioMapper;
import com.nttdata.database.UserMapper;
import com.nttdata.exception.BadRequestException;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.Noleggio;
import com.nttdata.model.User;
import com.nttdata.utils.Utils;

@RestController

public class NoleggioController {

	@Autowired
	private NoleggioMapper noleggioMapper;

	@Autowired
	private UserMapper userMapper;
	

	@RequestMapping(method = RequestMethod.GET, value = "/noleggio")
	public List<Noleggio> listNoleggio(@RequestParam(value="ritardo",required=false) Boolean isritardo) {
		List<Noleggio> findAll = noleggioMapper.findAll();
		
		
		List<Noleggio> filtered = new ArrayList<>();

		for (Noleggio n : findAll) {
			Date dataNoleggio30 = Utils.addDays(n.getDataPrelievo(), 30);
			boolean ritardo = dataNoleggio30.before(new Date()) && n.getDataConsegna() == null;
			n.setRitardo(ritardo);
			
			if(isritardo != null ) {
				if(n.isRitardo() == true) {
					filtered.add(n);
				}
			
			}else {
				filtered.add(n);
			}
				
		}
		
		if (filtered != null && filtered.isEmpty())
			throw new ResourceNotFoundException("Non ci sono noleggi");

		return filtered;
	}

	@RequestMapping(method = RequestMethod.GET, value = "user/{badgeId}/noleggio")
	public List<Noleggio> listNoleggio(@PathVariable(value = "badgeId", required = true) int badgeId,
			@RequestParam(value="ritardo",required=false) Boolean ritardo,
			@RequestParam(value="nonritardo",required=false) Boolean nonritardo) {

		User findByBadgeId = userMapper.findByBadgeId(badgeId);
		if (findByBadgeId == null)
			throw new ResourceNotFoundException("L'utente non esiste"+badgeId+" non esiste");
		
		List<Noleggio> findAll = noleggioMapper.findAllByUtente(badgeId);
		List<Noleggio> filtered = new ArrayList<>();
		
		for(Noleggio n : findAll) {
			if(ritardo != null) {
				if(n.isRitardo() == true) {
					filtered.add(n);
				}
			}else {
				filtered.add(n);
			}
		}
		
		if (findAll != null && findAll.isEmpty())
			throw new ResourceNotFoundException("L'utente inserito non ha fatto nessun noleggio");
		return findAll;
	}

	@RequestMapping(method = RequestMethod.GET, value = "user/{badgeId}/noleggio/{idNoleggio}")
	public Noleggio get(@PathVariable(value = "idNoleggio", required = true) int idNoleggio,
			@PathVariable(value = "badgeId", required = true) int badgeId) {
		
		User findByBadgeId = userMapper.findByBadgeId(badgeId);
		if (findByBadgeId == null)
			throw new ResourceNotFoundException("L'utente non esiste"+badgeId+" non esiste");
		
		Noleggio noleggio = noleggioMapper.findByIdNoleggio(badgeId,idNoleggio);
		if (noleggio != null)
			return noleggio;
		else
			throw new ResourceNotFoundException("Noleggio non esistente");
	}

	@RequestMapping(method = RequestMethod.POST, value = "user/{badgeId}/noleggio")
	public Noleggio add(@RequestBody Noleggio noleggio,
			@PathVariable(value = "badgeId", required = true) int badgeId) {
		
		User findByBadgeId = userMapper.findByBadgeId(badgeId);
		if (findByBadgeId == null)
			throw new ResourceNotFoundException("L'utente non esiste"+badgeId+" non esiste");
		
		if (!noleggio.getDataPrelievo().equals(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH))) {
			throw new BadRequestException("La data di prelievo non corrisponde alla data di oggi!");
		}
				
		
		Noleggio foundNoleggio = noleggioMapper.findByIdNoleggio(noleggio.getIdNoleggio(),badgeId);
		if (foundNoleggio != null)
			throw new ResourceConflictException();

		boolean before = noleggio.getDataConsegna().before(noleggio.getDataPrelievo());
		if (before) {
			throw new BadRequestException("la data di consegna non può essere precedente la data di prelievo");
		}

		noleggioMapper.add(noleggio);
		return noleggio;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "user/{badgeId}/noleggio/{idNoleggio}")
	public Noleggio update(@RequestBody Noleggio noleggio,
			@PathVariable(value = "badgeId", required = true) int badgeId,
			@PathVariable(value = "idNoleggio", required = true) int idNoleggio) {

		User findByBadgeId = userMapper.findByBadgeId(badgeId);
		if (findByBadgeId == null)
			throw new ResourceNotFoundException("L'utente non esiste"+badgeId+" non esiste");
		
		Noleggio foundNoleggio = noleggioMapper.findByIdNoleggio(badgeId,idNoleggio);
		if (foundNoleggio == null)
			throw new NoContentException("Noleggio non esistente");

		noleggio.setIdUtente(badgeId);
		noleggio.setIdNoleggio(idNoleggio);
		noleggioMapper.update(noleggio);
		return noleggio;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "user/{badgeId}/noleggio/{idNoleggio}")
	public void delete(@PathVariable(value = "idNoleggio", required = true) int idNoleggio,
			@PathVariable(value = "badgeId", required = true) int badgeId) {
		
		User user = userMapper.findByBadgeId(badgeId);
		if (user == null)
			throw new ResourceNotFoundException("L'utente non esiste "+badgeId+" non esiste");
		
		Noleggio foundNoleggio = noleggioMapper.findByIdNoleggio(badgeId,idNoleggio);
		if (foundNoleggio == null)
			throw new NoContentException();
		noleggioMapper.delete(idNoleggio);
	}

}
