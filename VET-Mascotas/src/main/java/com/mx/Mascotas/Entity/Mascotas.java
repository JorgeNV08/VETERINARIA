package com.mx.Mascotas.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "MASCOTAS")
@Data
public class Mascotas {
	
	@Id
	private int idMascota;
	private String nombre;
	private String raza;
	private int edad;
	private String razonCita;
	private int clienteId;
	private int responsableId;
	private int veterinariaId;
	
}
