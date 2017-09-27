/**
 * 
 */
package com.nttdata.controller;

import static org.junit.Assert.assertTrue;

import java.text.MessageFormat;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.nttdata.model.User;

/**
 * @author LoeschTh
 *
 */
public class UserControllerTest {
	private static final String baseUrl = "http://localhost:8080";
	
	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

	@Test
	public void test() {
		String URL = baseUrl+"/user";
		
		RestTemplate restTemplate = new RestTemplate();
		
		User[] users = restTemplate.getForObject(URL, User[].class);
		
		assertTrue(users!=null);
		assertTrue(users.length > 0);
		
		logger.debug(MessageFormat.format("URL {0}: result {1}",URL,users));
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
		logger.debug(MessageFormat.format("URL {0}: result {1}",URL,user));
		
		User userGet = restTemplate.getForObject(URL+"/"+user.getBadgeId(), User.class);
		assertTrue(userGet!=null);
		
		assertTrue(userGet.equals(user));
		
	}
}
