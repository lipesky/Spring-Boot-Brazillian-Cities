package com.example.evoluum1.services;

import java.util.LinkedList;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.evoluum1.models.City;
import com.example.evoluum1.models.Report;
import com.example.evoluum1.models.ReportRecord;
import com.example.evoluum1.models.UF;

@Service
public class ExternalCityService implements CityService {
	
	private final Logger logger = Logger.getLogger(ExternalCityService.class.getName());
	
	private final String municipiosEndpoint = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/%s/municipios";
	private final String estadosEndpoint = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
	private RestTemplate restTemplate;
	private CircuitBreakerFactory<?,?> circuitBreakerFactory;

	/**
	 * @param report
	 */
	public ExternalCityService(RestTemplate restTemplate, CircuitBreakerFactory<?,?> circuitBreakerFactory) {
		this.restTemplate = restTemplate;
		this.circuitBreakerFactory = circuitBreakerFactory;
	}	
	
	private Report fetchData() {
		logger.info("repository fetching data");		
		
		String ufIds = this.getUFsIdsFormatted();
		City[] cities = this.getCities(ufIds);
		
		LinkedList<ReportRecord> reportData = new LinkedList<ReportRecord>();
		for(int i = 0; i < cities.length; i++) {			
			reportData.add(new ReportRecord(cities[i]));
		}
		
		return new Report(reportData);
	}
	
	
	private String getUFsIdsFormatted() throws RestClientException{
		logger.info("fetching UFs data");		
		
		String formattedIds = "";
		UF[] response = restTemplate.getForObject(estadosEndpoint, UF[].class);
			
		
		for(final UF uf: response) {
			formattedIds += uf.getId() +"|";
		}
		formattedIds = formattedIds.substring(0, formattedIds.length() -1);
		
		return formattedIds;
	}
	
	private City[] getCities(String ufIds) throws RestClientException{
		logger.info("fetching cities data for UF ids: "+ ufIds);
		
		City[] response = restTemplate.getForObject(String.format(municipiosEndpoint, ufIds), City[].class);
		return response;
	}
	
	@Cacheable(cacheNames= "search", key= "#name", unless = "#result == null")
	public Optional<ReportRecord> getCityIdByName(String name){
		// use a tree structure(suggest red-black tree, because has a good ballancing and memory usage) for binary search.
		logger.info("searching city id by name: "+ name);
		Report report = this.getReport();
		if(report != null) {
			return report.getData()
					.stream()
					.filter(_rec -> _rec.getNomeCidade().compareToIgnoreCase(name) == 0)
					.limit(1)
					.findFirst();
		}else {
			return null;
		}
	}
	

	/**
	 * @return fetch and returns the report
	 */
	public Report getReport() {
		Report report = circuitBreakerFactory.create("fetchReport").run(
				() -> fetchData(),
				throwable -> {
					logger.warning("error fetching data from external service. "+throwable.getMessage());
					return null;
				}
		);
		return report;
	}

	
}
