package com.spring.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceApplication {
	
	private static Logger logger = LoggerFactory.getLogger(EurekaServiceApplication.class);

	public static void main(String[] args) {
		logger.debug("Start of main(-) method..");
		SpringApplication.run(EurekaServiceApplication.class, args);
		logger.debug("End of main(-) method..");
	}

}
