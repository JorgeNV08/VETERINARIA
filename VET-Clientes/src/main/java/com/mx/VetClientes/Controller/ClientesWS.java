package com.mx.VetClientes.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.VetClientes.Entity.Clientes;
import com.mx.VetClientes.Service.ClientesServImpl;

@RestController
@RequestMapping("/Clientes")  //http://localhost:8200/Clientes
public class ClientesWS { 

	@Autowired
	ClientesServImpl service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Clientes> clientes = service.listar();
		
		if(clientes.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Clientes cliente){
		try {
			Clientes nuevoCliente = service.guardar(cliente);
			return ResponseEntity.status(HttpStatus.OK).body(nuevoCliente);
			
		} catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
		
		
	
	@PutMapping
	public ResponseEntity<?> editar(@RequestBody Clientes cliente){
		try {
			Clientes aux = service.buscar(cliente.getIdCliente());
			if(aux==null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
			service.editar(cliente);
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
			
		} catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
		
		
		
				
		
	}
	
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<?> eliminar(@PathVariable int idCliente){
		Clientes aux = service.buscar(idCliente);
		if(aux!=null)
			if(service.eliminar(idCliente)!=null) {
				return ResponseEntity.status(HttpStatus.OK).body(aux);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
				}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	@GetMapping("/{idCliente}")
	public ResponseEntity<?> buscar(@PathVariable int idCliente){
		Clientes cliente = service.buscar(idCliente);
		if(cliente!=null)
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
}
