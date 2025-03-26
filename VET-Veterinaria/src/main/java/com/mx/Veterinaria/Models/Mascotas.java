package com.mx.Veterinaria.Models;

import lombok.Data;

@Data
public class Mascotas {

	private int idMascota;
	private String nombre;
	private String raza;
	private int edad;
	private String razonCita;
	private int clienteId;
	private int responsableId;
	private int veterinariaId;
	
}
