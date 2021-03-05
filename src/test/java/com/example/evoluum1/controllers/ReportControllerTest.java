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
public class ReportControllerTest {
	
	private final String endpointPrefix = "/api/report";

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testReportAsJson() throws Exception{
		MvcResult result = mockMvc
				.perform(get(endpointPrefix+"/json")
				.accept(MediaType.ALL))
				.andReturn();
		if(result.getResponse().getStatus() == 200) {
			assert(result.getResponse().getContentType().toUpperCase().startsWith("APPLICATION/JSON"));
		}else if(result.getResponse().getStatus() == 503) {
			assert(result.getResponse().getContentType().toUpperCase().startsWith("TEXT/PLAIN"));
			assert(result.getResponse().getContentAsString().equals("Service unavaliable"));
		}else {
			throw new Exception("unexpected behaviour");
		}
	}
	
	@Test
	public void testReportAsCSV() throws Exception{
		MvcResult result = mockMvc
								.perform(get(endpointPrefix+"/csv")
								.accept(MediaType.ALL))		
								.andReturn();
		
		if(result.getResponse().getStatus() == 200) {
			
			assert(result.getResponse().getContentType().toUpperCase().startsWith("TEXT/CSV"));
			
			String[] resposta = result.getResponse().getContentAsString().split("\n");
			int index = 0;
			if(resposta[index] == "sep=," || resposta[index].split(",").length == 6) {
				index++;
			}
			for(final String line : resposta) {
				if(line.split(",").length != 6) {
					throw new Exception("line doesn't match. line: "+line);
				}
			}
			
		}else if(result.getResponse().getStatus() == 503) {
			assert(result.getResponse().getContentType().toUpperCase().startsWith("TEXT/PLAIN"));
			assert(result.getResponse().getContentAsString().equals("Service unavaliable"));
			
		}else {
			
			throw new Exception("unexpected behaviour");
		}
		
		
	}
}
