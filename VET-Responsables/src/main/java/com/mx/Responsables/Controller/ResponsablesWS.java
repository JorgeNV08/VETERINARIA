package com.mx.Responsables.Controller;

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

import com.mx.Responsables.Entity.Responsables;
import com.mx.Responsables.Service.ResponsablesServImp;

@RestController
@RequestMapping("/Responsable") //http://localhost:8202/Responsable
public class ResponsablesWS {

	@Autowired
	ResponsablesServImp service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Responsables> responsables = service.listar();
		if(responsables.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(responsables); 
	}
	
	@GetMapping("/{idResponsable}")
	public ResponseEntity<?> buscar(@PathVariable int idResponsable){
		Responsables responsable= service.buscar(idResponsable);
		if(responsable!=null)
			return ResponseEntity.status(HttpStatus.OK).body(responsable);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public ResponseEntity<?> guardar(@RequestBody Responsables responsable){
		System.out.println("El responsable es: " + responsable);
		try {
			Responsables nuevoResp = service.guardar(responsable);
			return ResponseEntity.status(HttpStatus.OK).body(nuevoResp);
		} catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
			
		
	}
	
	@PutMapping
	public ResponseEntity<?> editar(@RequestBody Responsables responsable){
		
		try {
			Responsables aux = service.buscar(responsable.getIdResponsable());
			if(aux==null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
			service.editar(responsable);
			return ResponseEntity.status(HttpStatus.OK).body(responsable);
				
		} catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{idResponsable}")
	public ResponseEntity<?> eliminar(@PathVariable int idResponsable){
		Responsables aux = service.buscar(idResponsable);
		if(aux!=null)
			if(service.eliminar(idResponsable)!=null) {
				return ResponseEntity.status(HttpStatus.OK).body(aux);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	
	
}
