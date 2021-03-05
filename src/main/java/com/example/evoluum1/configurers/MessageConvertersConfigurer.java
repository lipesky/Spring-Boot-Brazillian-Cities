package com.example.evoluum1.configurers;

import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.evoluum1.converters.CsvConverter;

@Configuration
public class MessageConvertersConfigurer implements WebMvcConfigurer {
		
	private final MediaType CsvType = MediaType.valueOf("text/csv");
	private final Logger logger = Logger.getLogger(MessageConvertersConfigurer.class.getName());

	@Autowired
	private CsvConverter<?> csvconverter;
  
	@Bean 
	public CsvConverter<OutputStream> createCsvConverter() {
		logger.info("Creating CSV converter bean for OutputStream");
		return new CsvConverter<>(CsvType, OutputStream.class); 
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
	 	logger.info("Adding csv converter");
		converters.add(csvconverter);
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		logger.info("Adding text/csv media type and setting it as default before json");
		configurer.mediaType(CsvType.getSubtype(), CsvType);
		configurer.defaultContentType(CsvType, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN);
	}
	
}
