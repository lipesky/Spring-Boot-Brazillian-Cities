package com.example.evoluum1.configurers;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurer {
	
	private final Logger logger = Logger.getLogger(SwaggerConfigurer.class.getName());
	
	private ApiInfo createApiInfo() {
		return new ApiInfoBuilder()
				.title("Evolum App")
				.description("Aplicação desafio da evoluum, que consulta o ibge, monta relatórios e faz uma consulta")
				.version("1.0.0")
				.build();
	}
	
	@Bean
	public Docket configureDocket() {
		logger.info("configuring new Docket");
		///swagger-ui/index.html
		///v3/api-docs
		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(createApiInfo())
					.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any())
					.build();
	}
	
	
}
