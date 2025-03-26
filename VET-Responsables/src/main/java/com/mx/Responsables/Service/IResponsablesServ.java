package com.mx.Responsables.Service;

import java.util.List;

import com.mx.Responsables.Entity.Responsables;

public interface IResponsablesServ {
	
	public List<Responsables> listar();
	
	public Responsables buscar(int idResponsable);
	
	public Responsables guardar(Responsables responsable);
	
	public Responsables editar(Responsables responsable);
	
	public Responsables eliminar(int idResponsable);

}
