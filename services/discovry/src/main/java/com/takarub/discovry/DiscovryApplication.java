package com.takarub.discovry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscovryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscovryApplication.class, args);
	}

}
