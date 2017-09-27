package com.nttdata.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.database.UserMapper;
import com.nttdata.exception.BadRequestException;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.Indirizzi;
import com.nttdata.model.Telefoni;
import com.nttdata.model.User;
import com.nttdata.utils.Utils;

@RestController
public class UserController {

	@Autowired
	private UserMapper userMapper;

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public List<User> find(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surname", required = false) String surname,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "minEta", required = false) Integer minEta,
			@RequestParam(value = "citta", required = false) String citta,
			@RequestParam(value = "numero", required = false) String numero) {

		Map<String, Object> params = new HashMap<>();

		User user = new User();
		user.setName(name);
		user.setSurname(surname);
		user.setEmail(email);

		params.put("user", user);

		Indirizzi ind = new Indirizzi();
		ind.setCitta(citta);

		params.put("indirizzo", ind);

		Telefoni telefono = new Telefoni();
		telefono.setNumero(numero);

		params.put("telefono", telefono);

		List<User> findAll = userMapper.findByParams(params);

		List<User> filtered = new ArrayList<>();

		for (User u : findAll) {
			u.setEta(Utils.getEta(u.getDataNascita()));
			if (minEta != null) {
				if (u.getEta() >= minEta) {
					filtered.add(u);
				}
			} else {
				filtered.add(u);
			}

		}

		if (filtered != null && filtered.isEmpty())
			throw new ResourceNotFoundException();

		return filtered;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/search")
	public List<User> find(@RequestParam(value = "query", required = false) String query){
		
		List<User> findAll = userMapper.findByQuery(query);
		
		for (User u : findAll) {
			u.setEta(Utils.getEta(u.getDataNascita()));
		}
		
		if (findAll != null && findAll.isEmpty())
			throw new ResourceNotFoundException();
		
		return findAll;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/{badgeId}")
	public User get(@PathVariable(value = "badgeId", required = true) int badgeId) {
		User user = userMapper.findByBadgeId(badgeId);

		if (user != null) {
			user.setEta(Utils.getEta(user.getDataNascita()));
			return user;
		}
		else
			throw new ResourceNotFoundException();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public User add(@RequestBody User user) {

		if (!validateUser(user)) {
			throw new BadRequestException();
		}

		User foundUser = userMapper.findByBadgeId(user.getBadgeId());
		if (foundUser != null)
			throw new ResourceConflictException();

		userMapper.add(user);
		return user;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/user/{badgeId}")
	public User update(@RequestBody User user, @PathVariable(value = "badgeId", required = true) int badgeId) {

		if (!validateUser(user)) {
			throw new BadRequestException();
		}

		User foundUser = userMapper.findByBadgeId(badgeId);
		if (foundUser == null)
			throw new NoContentException();

		user.setBadgeId(badgeId);
		userMapper.update(user);
		return user;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{badgeId}")
	public void delete(@PathVariable(value = "badgeId", required = true) int badgeId) {
		User foundUser = userMapper.findByBadgeId(badgeId);
		if (foundUser == null)
			throw new NoContentException();
		userMapper.delete(badgeId);
	}

	private boolean validateUser(User user) {

		if (StringUtils.isBlank(user.getName()))
			return false;
		if (StringUtils.isBlank(user.getSurname()))
			return false;
		if (StringUtils.isBlank(user.getEmail()))
			return false;
		return true;
	}

}
