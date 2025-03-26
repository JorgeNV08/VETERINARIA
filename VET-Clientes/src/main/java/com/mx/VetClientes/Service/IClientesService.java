package com.mx.VetClientes.Service;

import java.util.List;

import com.mx.VetClientes.Entity.Clientes;

public interface IClientesService {
	
	public Clientes guardar(Clientes cliente);
	
	public Clientes editar(Clientes cliente);
	
	public Clientes eliminar(int clienteId);
	
	public Clientes buscar(int clienteId);
	
	public List<Clientes> listar();
	

}
