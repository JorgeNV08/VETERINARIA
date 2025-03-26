package com.mx.Veterinaria.Service;

import java.util.List;

import com.mx.Veterinaria.Entity.Veterinaria;

public interface IVeterinariaServ {
	

	public List<Veterinaria> listar();

	public Veterinaria buscar(int idVeterinaria);
	
	public Veterinaria guardar(Veterinaria veterinaria);
	
	public Veterinaria editar(Veterinaria veterinaria);
	
	public Veterinaria eliminar (int idVeterinaria);
	
}
