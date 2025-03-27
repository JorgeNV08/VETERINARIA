package com.mx.Mascotas.Controller;

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

import com.mx.Mascotas.Entity.Mascotas;
import com.mx.Mascotas.Service.MascotasServImpl;

@RestController
@RequestMapping("/Mascotas") //http://localhost:8203/Mascotas
public class MascotasWS {

	@Autowired
	MascotasServImpl service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Mascotas> mascotas = service.listar();
		if(mascotas.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(mascotas);
	}
	
	@GetMapping("/{idMascota}")
	public ResponseEntity<?> buscar(@PathVariable int idMascota){
		Mascotas mascota = service.buscar(idMascota);
		if(mascota!=null)
			return ResponseEntity.status(HttpStatus.OK).body(mascota);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Mascotas mascota){
		try {
			Mascotas nuevaMascota = service.guardar(mascota);
			return ResponseEntity.status(HttpStatus.OK).body(nuevaMascota);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping
	public ResponseEntity<?> editar(@RequestBody Mascotas mascota){
		try {
			Mascotas aux = service.buscar(mascota.getIdMascota());
			if(aux==null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
			
			service.editar(mascota); 
			return ResponseEntity.status(HttpStatus.OK).body(mascota);
			
		} catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}

	}
	
	
	@DeleteMapping("/{idMascota}")
	public ResponseEntity<?> eliminar(@PathVariable int idMascota){
		Mascotas aux = service.buscar(idMascota);
		if(aux!=null)
			if(service.eliminar(idMascota)!=null) {
				return ResponseEntity.status(HttpStatus.OK).body(aux);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
	
	
	
	
}
