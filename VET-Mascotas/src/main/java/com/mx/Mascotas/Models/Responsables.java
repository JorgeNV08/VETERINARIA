package com.mx.Mascotas.Models;

import lombok.Data;

@Data
public class Responsables {

	private int idResponsable;
	private String nombre;
	private Long contacto;
	private int veterinariaId;
}
