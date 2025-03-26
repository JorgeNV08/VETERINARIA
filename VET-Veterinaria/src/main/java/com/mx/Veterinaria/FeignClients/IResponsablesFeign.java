package com.mx.Veterinaria.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mx.Veterinaria.Models.Responsables;

@FeignClient(
		name = "VET-Responsables",
		url = "http://localhost:8202",
		path = "/Responsable"
		)


public interface IResponsablesFeign {

	//Declaramos los metodos de Responsables
	@GetMapping
	public List<Responsables> listarResponsables();
	
	@GetMapping("/{idResponsable}")
	public Responsables buscarResponsable(@PathVariable int idResponsable);
	
	@PostMapping
	public Responsables guardarResponsable(@RequestBody Responsables responsable);
	
	@PutMapping
	public Responsables editarResponsable(@RequestBody Responsables responsable);
	
	@DeleteMapping("/{idResponsable}")
	public Responsables eliminarResponsable(@PathVariable int idResponsable);
}
