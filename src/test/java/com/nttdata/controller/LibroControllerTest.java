
package com.nttdata.controller;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;


import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.nttdata.model.Libro;



public class LibroControllerTest {
	
	private static final String baseUrl = "http://localhost:8080";
	
	@Test
	public void test() {
		String URL = baseUrl+"/libro";
		
		RestTemplate restTemplate = new RestTemplate();
		
		Libro[] libro = restTemplate.getForObject(URL, Libro[].class);
		
		assertTrue(libro!=null);
		assertTrue(libro.length > 0);
		
	}
	
	@Test
	public void testPost() {
		String URL = baseUrl+"/libro";
		
		RestTemplate restTemplate = new RestTemplate();
		
		Libro request = new Libro();
		request.setTitolo("Harry Potter");
		request.setGenere("Fantasy");
		request.setPrezzo(new BigDecimal("12.5"));
		request.setScaffale("3");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Libro> entity = new HttpEntity<Libro>(request ,headers);
		
		Libro libro = restTemplate.postForObject(URL, entity , Libro.class);
		
		assertTrue(libro!=null);
		assertTrue(libro.getIdLibro() != 0);
		
		Libro libroGet = restTemplate.getForObject(URL+"/"+libro.getIdLibro(), Libro.class);
		assertTrue(libroGet!=null);
		
		assertTrue(libroGet.equals(libro));
		
	}
	
	@Test
	public void testDelete() {
		String URL = baseUrl+"/libro";
		
		RestTemplate restTemplate = new RestTemplate();
		
		Libro request = new Libro();
		request.setTitolo("Harry Potter");
		request.setGenere("Fantasy");
		request.setPrezzo(new BigDecimal("12.5"));
		request.setScaffale("3");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Libro> entity = new HttpEntity<Libro>(request ,headers);
		
		Libro libro = restTemplate.postForObject(URL, entity , Libro.class);

		
		assertTrue(libro!=null);
		assertTrue(libro.getIdLibro() != 0);
		
		restTemplate.delete(URL+"/"+libro.getIdLibro());
		
		try {
			restTemplate.getForObject(URL+"/"+libro.getIdLibro(), Libro.class);
	    }
	    catch (final HttpClientErrorException e) {
	    	assertTrue(e.getStatusCode().equals(HttpStatus.NOT_FOUND));
	    }
		
		
		
	}
	
	@Test
	public void testPut() {
		String URL = baseUrl+"/libro";
		
		RestTemplate restTemplate = new RestTemplate();
		
		Libro request = new Libro();
		request.setTitolo("Harry Potter");
		request.setGenere("Fantasy");
		request.setPrezzo(new BigDecimal("12.5"));
		request.setScaffale("3");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Libro> entity = new HttpEntity<Libro>(request ,headers);
		
		Libro libro = restTemplate.postForObject(URL, entity , Libro.class);
		
		assertTrue(libro!=null);
		assertTrue(libro.getIdLibro() != 0);
		
		libro.setTitolo("Manny Potter");
		libro.setGenere("fantascienza");
		libro.setPrezzo(new BigDecimal("12.5"));
		libro.setScaffale("4");
		
		HttpHeaders headersPut = new HttpHeaders();
		headersPut.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Libro> entityPut = new HttpEntity<Libro>(libro ,headersPut);
		
		restTemplate.put(URL+"/"+libro.getIdLibro(),  entityPut , Libro.class);
		
		Libro usernew = restTemplate.getForObject(URL+"/"+libro.getIdLibro(), Libro.class);
		assertTrue(usernew!=null);
		
		assertTrue(usernew.equals(libro));
		
		
	}



}
