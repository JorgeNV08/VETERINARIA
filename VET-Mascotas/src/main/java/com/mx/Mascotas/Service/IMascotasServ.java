package com.mx.Mascotas.Service;

import java.util.List;

import com.mx.Mascotas.Entity.Mascotas;

public interface IMascotasServ {

	public List<Mascotas> listar();
	
	public Mascotas buscar(int idMascota);
	
	public Mascotas guardar(Mascotas mascota);
	
	public Mascotas editar(Mascotas mascota);
	
	public Mascotas eliminar(int idMascota);
}
