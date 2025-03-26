package com.mx.Responsables.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "RESPONSABLES")
@Data
public class Responsables {

	@Id
	private int idResponsable;
	private String nombre;
	private Long contacto;
	private int veterinariaId;
}
