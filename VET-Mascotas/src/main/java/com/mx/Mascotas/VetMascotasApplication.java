package com.mx.Mascotas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class VetMascotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetMascotasApplication.class, args);
	}

}
