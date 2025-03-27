package com.mx.VetClientes.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mx.VetClientes.Dao.IClientesDao;
import com.mx.VetClientes.Entity.Clientes;

@Service
public class ClientesServImpl implements IClientesService {

	@Autowired
	IClientesDao dao;
	
	@Override
	public Clientes guardar(Clientes cliente) {
		Clientes clienteExist = dao.findByNombreIgnoreCaseAndDireccionIgnoreCase(cliente.getNombre(), cliente.getDireccion());
		
		
		if (clienteExist != null) {
	        throw new RuntimeException("El cliente con el nombre '" + cliente.getNombre() +"' y la dirección '"+ cliente.getDireccion() + "' ya existe.");
	    }
		return dao.save(cliente);
	}

	@Override
	public Clientes editar(Clientes cliente) {
		Clientes clienteExist = dao.findByNombreIgnoreCaseAndDireccionIgnoreCase(cliente.getNombre(),cliente.getDireccion());
		
		if(clienteExist!=null && clienteExist.getIdCliente() != cliente.getIdCliente())
			 throw new RuntimeException("El cliente con el nombre '" + cliente.getNombre() +"' y la dirección '"+ cliente.getDireccion() + "' ya existe.");
		return dao.save(cliente);
	}

	@Override
	public Clientes eliminar(int clienteId) {
		Clientes aux = this.buscar(clienteId);
		if(aux!=null) {
			dao.deleteById(clienteId);
			return aux;
		}
		return null;
	}

	@Override
	public Clientes buscar(int clienteId) {
		return dao.findById(clienteId).orElse(null);
	}

	@Override
	public List<Clientes> listar() {
		return dao.findAll(Sort.by(Direction.ASC,"idCliente"));
	}

}
