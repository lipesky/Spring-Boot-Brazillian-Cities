package com.example.evoluum1.configurers;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class Resilience4jConfigurer {
	
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> customize(){
		
		TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
				  .timeoutDuration(Duration.ofMillis(5500)) // max time trying to fetch external service
				  .build();
		
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
				  .failureRateThreshold(50) // 50 percent of failure to open the circuit
				  .waitDurationInOpenState(Duration.ofMillis(15000)) // wait for 15 seconds before try again
				  .slidingWindowType(SlidingWindowType.TIME_BASED)
				  //.slidingWindowType(SlidingWindowType.COUNT_BASED)
				  .slidingWindowSize(1) // last 10 seconds
				  .build();
		
		
		return factory -> factory.configureDefault(id -> 
				new Resilience4JConfigBuilder("fetchReport")
		          .timeLimiterConfig(timeLimiterConfig)
		          .circuitBreakerConfig(circuitBreakerConfig)
		          .build());
	}
}
