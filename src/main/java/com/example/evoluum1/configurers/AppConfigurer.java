package com.example.evoluum1.configurers;

import java.util.logging.Logger;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableCaching
public class AppConfigurer {
	
	private final Logger logger = Logger.getLogger(AppConfigurer.class.getName());
	
	@Bean
	public RestTemplate restTemplate() {
		logger.info("Creating RestTemplate Bean");
	    return new RestTemplate();
	}
	
}
