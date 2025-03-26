package com.mx.Veterinaria.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="VETERINARIAS")
@Data
public class Veterinaria {
	
	@Id
	private int idVeterinaria;
	private String nombre;
	private String direccion;
	private Long telefono;
	
	//comentario de prueba2
}
