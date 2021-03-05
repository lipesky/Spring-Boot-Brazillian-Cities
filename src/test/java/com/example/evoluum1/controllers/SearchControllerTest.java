package com.example.evoluum1.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SearchControllerTest {
	
	private final String endpointPrefix = "/api/search";
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetCityIdByName() throws Exception{
		testCorrectCity();
		testWrongCity();
	}
	
	private void testCorrectCity() throws Exception{

		
		MvcResult result = mockMvc
				.perform(get(endpointPrefix+"/getCityIdByName/Manaus")
				.accept(MediaType.ALL))
				.andReturn();
		
		
		if(result.getResponse().getStatus() == 200) {
			assert(result.getResponse().getContentType().toUpperCase().startsWith("TEXT/PLAIN"));
			Integer.parseInt(result.getResponse().getContentAsString());
		}else if(result.getResponse().getStatus() == 503) {
			assert(result.getResponse().getContentType().toUpperCase().startsWith("TEXT/PLAIN"));
			assert(result.getResponse().getContentAsString().equals("Service unavaliable"));
		}else {
			throw new Exception("unexpected behaviour");
		}
	}
	
	private void testWrongCity() throws Exception{
		MvcResult result = mockMvc
				.perform(get(endpointPrefix+"/getCityIdByName/notExistingCityName")
				.accept(MediaType.ALL))
				.andReturn();
		
		if(result.getResponse().getStatus() == 404) {
			assert(result.getResponse().getContentType().toUpperCase().startsWith("TEXT/PLAIN"));
			assert(result.getResponse().getContentAsString().equals("City not found"));
		}else if(result.getResponse().getStatus() == 503) {
			assert(result.getResponse().getContentType().toUpperCase().startsWith("TEXT/PLAIN"));
			assert(result.getResponse().getContentAsString().equals("Service unavaliable"));
		}else {
			throw new Exception("unexpected behaviour");
		}
	}
}
