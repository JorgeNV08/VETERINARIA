package com.mx.Veterinaria.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.mx.Veterinaria.Entity.Veterinaria;
import com.mx.Veterinaria.Models.Clientes;
import com.mx.Veterinaria.Models.Mascotas;
import com.mx.Veterinaria.Models.Responsables;
import com.mx.Veterinaria.Service.VeterinariaServImpl;

@RestController
@RequestMapping("/Veterinaria") //http://localhost:8201/Veterinaria
public class VeterinariaWS {

	@Autowired
	VeterinariaServImpl service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Veterinaria> veterinarias = service.listar();
		if(veterinarias.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //NOT CONTENT
		return ResponseEntity.status(HttpStatus.OK).body(veterinarias);
	}
	
	@GetMapping("/{idVeterinaria}")
	public ResponseEntity<?> buscar(@PathVariable int idVeterinaria){
		Veterinaria veterinaria = service.buscar(idVeterinaria);
		if(veterinaria!=null)
			return ResponseEntity.status(HttpStatus.OK).body(veterinaria);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Veterinaria veterinaria){
		try {
			Veterinaria nuevaVet = service.guardar(veterinaria);
			return ResponseEntity.status(HttpStatus.OK).body(nuevaVet);
		} catch(RuntimeException e) { 
		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping
	public ResponseEntity<?> editar(@RequestBody Veterinaria veterinaria){
		try {
			Veterinaria aux = service.buscar(veterinaria.getIdVeterinaria());
			if(aux==null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
			service.editar(veterinaria);
			return ResponseEntity.status(HttpStatus.OK).body(veterinaria);
			
		} catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{idVeterinaria}")
	public ResponseEntity<?> eliminar(@PathVariable int idVeterinaria){
		Veterinaria aux = service.buscar(idVeterinaria);
		if(aux!=null)
			if(service.eliminar(idVeterinaria)!=null) {
				return ResponseEntity.status(HttpStatus.OK).body(aux);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	/*// ------------------------------------------------------ C L I E N T E S 
	@GetMapping("/Clientes") //http://localhost:8201/Veterinaria/Clientes
	public ResponseEntity<?> listarClientes(){
		List<Clientes> clientes = service.getClientes(null);
		if(clientes.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(clientes);
	}
	
	@GetMapping("/Clientes/{clienteId}")
	public ResponseEntity<?> buscarCliente(@PathVariable int clienteId){
		Clientes cliente = service.findCliente(clienteId);
		if(cliente!=null)
			return ResponseEntity.status(HttpStatus.OK).body(cliente);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PostMapping("/Clientes")
	public ResponseEntity<?> guardarCliente(@RequestBody Clientes cliente){
		Clientes nuevoCliente = service.saveCliente(cliente);
		if(nuevoCliente!=null)
			return ResponseEntity.status(HttpStatus.OK).body(nuevoCliente);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping("/Clientes")
	public ResponseEntity<?> editarCliente(@RequestBody Clientes cliente){
		Clientes aux = service.findCliente(cliente.getIdCliente());
		if(aux!=null)
			if(service.editCliente(cliente)!=null) {
				return ResponseEntity.status(HttpStatus.OK).body(cliente);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	@DeleteMapping("/Clientes/{clienteId}")
	public ResponseEntity<?> eliminarCliente(@PathVariable int clienteId){
		Clientes aux = service.findCliente(clienteId);
		if(aux!=null)
			if(service.deleteCliente(clienteId)!=null) {
				return ResponseEntity.status(HttpStatus.OK).body(aux);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	
	// ------------------------------------------------------ M A S C O T A S 
	@GetMapping("/Mascotas") //http://localhost:8201/Veterinaria/Mascotas
	public ResponseEntity<?> listarMascotas(){
		List<Mascotas> mascotas = service.getMascotas(null);
		if(mascotas.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(mascotas);
	}
	
	@GetMapping("/Mascotas/{idMascota}")
	public ResponseEntity<?> buscarMascota(@PathVariable int idMascota){
		Mascotas mascota = service.findMascota(idMascota);
		if(mascota!=null)
			return ResponseEntity.status(HttpStatus.OK).body(mascota);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PostMapping("/Mascotas")
	public ResponseEntity<?> guardarMascota(@RequestBody Mascotas mascota){
		Mascotas aux = service.saveMascota(mascota);
		if(aux!=null)
			return ResponseEntity.status(HttpStatus.OK).body(aux);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping("/Mascotas")
	public ResponseEntity<?> editarMascota(@RequestBody Mascotas mascota){
		Mascotas aux = service.findMascota(mascota.getIdMascota());
		if(aux!=null)
			if(service.editMascota(mascota)!=null) {
				return ResponseEntity.status(HttpStatus.OK).body(mascota);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	@DeleteMapping("/Mascotas/{idMascota}")
	public ResponseEntity<?> eliminarMascota(@PathVariable int idMascota){
		Mascotas aux = service.findMascota(idMascota);
		if(aux!=null)
			if(service.deleteMascota(idMascota)!=null) {
				return ResponseEntity.status(HttpStatus.OK).body(aux);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	
	
	// ------------------------------------------------------ R E S P O N S A B L E S
	@GetMapping("/Responsable") //http://localhost:8201/Veterinaria/Responsables
	public ResponseEntity<?> listarResponsables(){
		List<Responsables> responsables = service.getResponsables(null);
		if(responsables.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(responsables);
	}
	
	@GetMapping("/Responsable/{idResponsable}")
	public ResponseEntity<?> buscarResponsable(@PathVariable int idResponsable){
		Responsables responsable = service.findResponsable(idResponsable);
		if(responsable!=null)
				return ResponseEntity.status(HttpStatus.OK).body(responsable);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PostMapping("/Responsable")
	public ResponseEntity<?> guardarResponsable(@RequestBody Responsables responsable){
		Responsables aux = service.saveResponsable(responsable);
		if(aux!=null)
			return ResponseEntity.status(HttpStatus.OK).body(aux);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping("/Responsable")
	public ResponseEntity<?> editarResponsable(@RequestBody Responsables responsable){
		Responsables aux = service.findResponsable(responsable.getIdResponsable());
		if(aux!=null)
			if(service.editResponsable(responsable)!=null) {
				return ResponseEntity.status(HttpStatus.OK).body(responsable);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	@DeleteMapping("/Responsable/{idResponsable}")
	public ResponseEntity<?> eliminarResponsable(@PathVariable int idResponsable){
		Responsables aux = service.findResponsable(idResponsable);
		if(aux!=null)
			if(service.deleteResponsable(idResponsable)!=null) {
				return ResponseEntity.status(HttpStatus.OK).body(aux);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	
	*/
	
	
	
	
	
	
	
	
}
