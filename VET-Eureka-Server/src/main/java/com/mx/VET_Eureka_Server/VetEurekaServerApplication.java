package com.mx.VET_Eureka_Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class VetEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetEurekaServerApplication.class, args);
	}

}
