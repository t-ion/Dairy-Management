package com.dairy.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProductserviceApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductserviceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
		LOGGER.debug("Started Application");
	}

}
