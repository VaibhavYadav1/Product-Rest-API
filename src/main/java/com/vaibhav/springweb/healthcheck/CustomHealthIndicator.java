package com.vaibhav.springweb.healthcheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		
		boolean flage = false ;
		
		if(flage) {
			return Health.down().withDetail("Enter Key", 123).build();
		}
		return Health.up().build();
	}

}
