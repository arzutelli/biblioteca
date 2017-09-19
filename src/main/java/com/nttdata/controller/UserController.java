package com.nttdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.database.UserMapper;
import com.nttdata.exception.NoContentException;
import com.nttdata.exception.ResourceConflictException;
import com.nttdata.exception.ResourceNotFoundException;
import com.nttdata.model.User;
import com.nttdata.utils.Utils;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method= RequestMethod.GET, value="/user")
    public List<User> find(
    		@RequestParam(value="name",required=false) String name,
    		@RequestParam(value="surname",required=false) String surname,
    		@RequestParam(value="email",required=false) String email    		
    		) {
    	
    	User params = new User();
    	params.setName(name);
    	params.setSurname(surname);
    	params.setEmail(email);
    	List<User> findAll = userMapper.findByParams(params);
    	
    	for(User u : findAll) {
    		u.setEta(Utils.getEta(u.getDataNascita()));
    	}
    	
    	
    	if(findAll != null && findAll.isEmpty())
    		throw new ResourceNotFoundException();
		return findAll;
    }
    
    @RequestMapping(method= RequestMethod.GET, value="/user/{badgeId}")
    public User get(@PathVariable(value="badgeId", required=true) int badgeId) {
        User user = userMapper.findByBadgeId(badgeId);
        if(user != null)
        	return user;
        else
        	throw new ResourceNotFoundException();
    }
    
    @RequestMapping(method= RequestMethod.POST, value="/user")
    public User add(@RequestBody User user) {
    	User foundUser = userMapper.findByBadgeId(user.getBadgeId());
    	if(foundUser != null)
    		throw new ResourceConflictException();
    	
    	userMapper.add(user);
		return user;
    }
    
    @RequestMapping(method= RequestMethod.PUT, value="/user/{badgeId}")
    public User update(@RequestBody User user,@PathVariable(value="badgeId", required=true) int badgeId) {
    	User foundUser = userMapper.findByBadgeId(badgeId);
    	if(foundUser == null)
    		throw new NoContentException();
    	
    	user.setBadgeId(badgeId);
    	userMapper.update(user);
    	return user;
    }
    
    @RequestMapping(method= RequestMethod.DELETE, value="/user/{badgeId}")
    public void delete(@PathVariable(value="badgeId", required=true) int badgeId) {
    	User foundUser = userMapper.findByBadgeId(badgeId);
    	if(foundUser == null)
    		throw new NoContentException();
    	userMapper.delete(badgeId);
    }
    
    
}
