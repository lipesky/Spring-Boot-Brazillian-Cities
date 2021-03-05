package com.example.evoluum1.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.example.evoluum1.models.Report;
import com.example.evoluum1.models.ReportRecord;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExternalCityServiceTests {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CircuitBreakerFactory<?, ?> circuitBreakerFactory;
	
	private TestableExternalCityService testableExternalCityService;
	
	private Report createReport() {
		List<ReportRecord> list = new ArrayList<ReportRecord>();
		
		list.add(new ReportRecord(11, "RO", "Norte", "Alta Floresta D'Oeste" , "Leste Rondoniense", 1));
		list.add(new ReportRecord(11, "RO", "Norte", "Ariquemes", "Leste Rondoniense", 2));
		list.add(new ReportRecord(11, "RO", "Norte", "Cabixi", "Leste Rondoniense", 3));
		list.add(new ReportRecord(11, "RO", "Norte", "Cacoal", "Leste Rondoniense", 4));
		list.add(new ReportRecord(11, "RO", "Norte", "Cerejeiras" , "Leste Rondoniense", 5));
		list.add(new ReportRecord(29, "BA", "Nordeste", "Sebasti�o Laranjeiras", "Centro Sul Baiano", 6));
		list.add(new ReportRecord(29, "BA", "Nordeste", "Senhor do Bonfim", "Centro Norte Baiano", 7));
		list.add(new ReportRecord(29, "BA", "Nordeste", "Serra do Ramalho", "Vale S�o-Franciscano da Bahia", 8));
		list.add(new ReportRecord(31, "MG", "Sudeste", "Vieiras", "Zona da Mata", 9));
		list.add(new ReportRecord(31, "MG", "Sudeste", "Mathias Lobato", "Vale do Rio Doce", 10));
		list.add(new ReportRecord(31, "MG", "Sudeste", "Virgem da Lapa", "Jequitinhonha", 11));
		list.add(new ReportRecord(31, "MG", "Sudeste", "Virg�nia", "Sul/Sudoeste de Minas" ,12));
		list.add(new ReportRecord(31, "MG", "Sudeste", "Virgin�polis", "Vale do Rio Doce", 13));
		list.add(new ReportRecord(31, "MG", "Sudeste", "Virgol�ndia", "Vale do Rio Doce", 14));
		
		return new Report(list);
	}
	
	@Before
	public void init() {
		
		testableExternalCityService = new TestableExternalCityService(restTemplate, 
				circuitBreakerFactory, 
				createReport());
	}
	
	@Test
	public void testGetCityIdByName() {
		assert(
			testableExternalCityService.getCityIdByName("Alta Floresta D'Oeste").isPresent() && 
			testableExternalCityService.getCityIdByName("Alta Floresta D'Oeste").get().getIdCidade() == 1
			);
		assert(
			testableExternalCityService.getCityIdByName("vieIraS").isPresent() && 
			testableExternalCityService.getCityIdByName("vieIraS").get().getIdCidade() == 9
			);
		assert(
			testableExternalCityService.getCityIdByName("virgol�ndia").isPresent() && 
			testableExternalCityService.getCityIdByName("virgol�ndia").get().getIdCidade() == 14
			);
		assert(
			!testableExternalCityService.getCityIdByName("notExistingCityName").isPresent() 
			);
	}
		
	
	private static class TestableExternalCityService extends ExternalCityService{

		private Report report;
		
		public TestableExternalCityService(RestTemplate restTemplate,
				CircuitBreakerFactory<?, ?> circuitBreakerFactory, Report report) {
			super(restTemplate, circuitBreakerFactory);
			this.report = report;
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public Report getReport() {
			return this.report;
		}
		
	}
	
}
