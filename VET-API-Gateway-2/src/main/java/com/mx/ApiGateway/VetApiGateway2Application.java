package com.mx.ApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VetApiGateway2Application {

	public static void main(String[] args) {
		SpringApplication.run(VetApiGateway2Application.class, args);
	}

	//Comentario de prueba
}
