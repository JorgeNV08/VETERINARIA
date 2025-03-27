package com.mx.Mascotas.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mx.Mascotas.Models.Clientes;


@FeignClient(
		name = "VET-Clientes",
		url = "http://localhost:8200",
		path="/Clientes"
		)

public interface IClientesFeign {
	
	//Controladores de microservicios
	@GetMapping
	public List<Clientes> listarClientes();
	
	@PostMapping
	public Clientes guardarCliente(@RequestBody Clientes cliente);
	
	@PutMapping
	public Clientes editarCliente(@RequestBody Clientes cliente);
	
	@DeleteMapping("/{clienteId}")
	public Clientes eliminarCliente(@PathVariable int clienteId);
	
	@GetMapping("/{clienteId}")
	public Clientes buscarCliente(@PathVariable int clienteId); 
	
	
}
