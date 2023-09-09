package com.instantsystem.connector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * DataConnectorApplication: Main application class for the data connector.
 */
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class DataConnectorApplication {

	public static void main(String[] args) {
		// Run the Spring Boot application
		SpringApplication.run(DataConnectorApplication.class, args);
	}

}
