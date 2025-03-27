package com.mx.Responsables;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class VetResponsablesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetResponsablesApplication.class, args);
	}

}
