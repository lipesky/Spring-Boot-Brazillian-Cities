package com.example.evoluum1.configurers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.evoluum1.interceptors.LoggerInterceptor;

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

	private final Logger log = Logger.getLogger(InterceptorConfigurer.class.getName());
	
	@Autowired
	LoggerInterceptor loggerInterceptor;
  
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("adding logger interceptor to registry");
		registry.addInterceptor(loggerInterceptor);
	}
	
	
}
