package com.instantsytem.parkingservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Parking API Service",
				version = "1.0.0",
				description = "This api manage parking data",
				contact = @Contact(
						name = "Kaabi Mohamed",
						email = "kaabi.med@gmail.com"
				),
				license = @License(
						name = "openSource"
				)
		)
)
public class ParkingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingServiceApplication.class, args);
	}

}
