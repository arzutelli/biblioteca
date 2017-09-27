/**
 * 
 */
package com.nttdata.controller;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.nttdata.model.User;

/**
 * @author LoeschTh
 *
 */
public class UserControllerTest {
	private static final String baseUrl = "http://localhost:8080";
	

	@Test
	public void test() {
		String URL = baseUrl+"/user";
		
		RestTemplate restTemplate = new RestTemplate();
		
		User[] users = restTemplate.getForObject(URL, User[].class);
		
		assertTrue(users!=null);
		assertTrue(users.length > 0);
		
	}

	
	@Test
	public void testPost() {
		String URL = baseUrl+"/user";
		
		RestTemplate restTemplate = new RestTemplate();
		
		User request = new User();
		request.setSurname("Thomas");
		request.setName("Loesch");
		request.setEmail("thomas.loesch@nttdata.com");
		request.setDataNascita(new Date());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<User> entity = new HttpEntity<User>(request ,headers);
		
		User user = restTemplate.postForObject(URL, entity , User.class);
		
		assertTrue(user!=null);
		assertTrue(user.getBadgeId() != 0);
		
		User userGet = restTemplate.getForObject(URL+"/"+user.getBadgeId(), User.class);
		assertTrue(userGet!=null);
		
		assertTrue(userGet.equals(user));
		
	}

	@Test
	public void testDelete() {
		String URL = baseUrl+"/user";
		
		RestTemplate restTemplate = new RestTemplate();
		
		User request = new User();
		request.setSurname("Thomas");
		request.setName("Loesch");
		request.setEmail("thomas.loesch@nttdata.com");
		request.setDataNascita(new Date());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<User> entity = new HttpEntity<User>(request ,headers);
		
		User user = restTemplate.postForObject(URL, entity , User.class);
		
		assertTrue(user!=null);
		assertTrue(user.getBadgeId() != 0);
		
		restTemplate.delete(URL+"/"+user.getBadgeId());
		
		try {
			restTemplate.getForObject(URL+"/"+user.getBadgeId(), User.class);
	    }
	    catch (final HttpClientErrorException e) {
	    	assertTrue(e.getStatusCode().equals(HttpStatus.NOT_FOUND));
	    }
		
		
		
	}
	
	
}
