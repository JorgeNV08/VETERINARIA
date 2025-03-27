package com.mx.VetClientes.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.VetClientes.Entity.Clientes;

public interface IClientesDao extends JpaRepository<Clientes, Integer> {

	public Clientes findByIdCliente(int idCliente);
	
	public Clientes findByNombreIgnoreCaseAndDireccionIgnoreCase(String nombre, String direccion);
	
}
