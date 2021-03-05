package com.example.evoluum1.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.evoluum1.models.Report;
import com.example.evoluum1.models.ReportRecord;
import com.example.evoluum1.services.CityService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/report")
public class ReportController {
	
	@Autowired
	CityService externalCityService;
	
	@ApiOperation("Returns the report in json format")
	@GetMapping(value ="/json", produces= "application/json")
	public ResponseEntity<?> reportAsJson() {
		Report report = externalCityService.getReport();
		if(report != null) {
			return new ResponseEntity<List<ReportRecord>>(report.getData(), HttpStatus.OK);
		}else {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.TEXT_PLAIN);					
			return new ResponseEntity<String>("Service unavaliable", headers, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	@ApiOperation("Returns the report in csv format")
	@GetMapping(value = "/csv", produces = "text/csv")	
	public @ResponseBody OutputStream reportAsCSV(HttpServletResponse response) throws IOException{
		Report report = externalCityService.getReport();
		if(report != null) {
			response.setHeader("Content-Disposition", "attachment;filename=report.csv"); 
			return report.getCSVStream();
		}else {			
			serviceUnavaliableOutputStream(response);
			return null;
		}		
	}
	
	private void serviceUnavaliableOutputStream(HttpServletResponse response) {
		final String message = "Service unavaliable";
		try {
			response.setHeader("Content-Type", "text/plain");
			response.setStatus(503); // HttpStatus.SERVICE_UNAVAILABLE
			response.getOutputStream().write(message.getBytes());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
