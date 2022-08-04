package com.humber.flightmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
//@EntityScan(basePackages = {"com.humber.flightmanagement.model"})
@ComponentScan
public class FlightmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightmanagementApplication.class, args);
	}

}
