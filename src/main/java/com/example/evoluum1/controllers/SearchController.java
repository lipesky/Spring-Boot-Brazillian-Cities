package com.example.evoluum1.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evoluum1.models.ReportRecord;
import com.example.evoluum1.services.CityService;
import com.example.evoluum1.services.ExternalCityService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/api/search")
public class SearchController {
	
		
	
	@Autowired
	CityService externalCityService;
	
	/**
	 * Returns the city id
	 */		
	@ApiOperation("Gets the city id")
	@GetMapping(value = "/getCityIdByName/{nome}", produces = "text/plain" )
	public ResponseEntity<?> getCityIdByName(@PathVariable(name = "nome") String nome) { 
		Optional<ReportRecord> rec = externalCityService.getCityIdByName(nome);
		if(rec == null) {
			return new ResponseEntity<String>("Service unavaliable", HttpStatus.SERVICE_UNAVAILABLE);
		}else if(rec.isPresent()) {
			return new ResponseEntity<String>(String.valueOf(rec.get().getIdCidade()), HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("City not found", HttpStatus.NOT_FOUND);
		}
	}
}
