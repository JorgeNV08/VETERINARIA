package com.mx.Veterinaria.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mx.Veterinaria.Models.Mascotas;

@FeignClient(
		name = "VET-Mascotas",
		url = "http://localhost:8203",
		path = "/Mascotas"
		)

public interface IMascotasFeign {

	//Declaración de los metodos
	@GetMapping
	public List<Mascotas> listarMascotas();
	
	@GetMapping("/{idMascota}")
	public Mascotas buscarMascota(@PathVariable int idMascota);
	
	@PostMapping
	public Mascotas guardarMascota(@RequestBody Mascotas mascota);
	
	@PutMapping
	public Mascotas editarMascota(@RequestBody Mascotas mascota);
	
	@DeleteMapping("/{idMascota}")
	public Mascotas eliminarMascota(@PathVariable int idMascota);
}
