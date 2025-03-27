package com.mx.Mascotas.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mx.Mascotas.Models.Veterinaria;

@FeignClient(
		name = "VET-Veterinaria",
		url="http://localhost:8201",
		path="/Veterinaria"
		)

public interface IVeterinariaFeign {

	@GetMapping
	public List<Veterinaria> listarVeterinaria();
	 
	@GetMapping("/{idVeterinaria}")
	public Veterinaria buscarVeterinaria(@PathVariable int idVeterinaria);

	@PostMapping
	public Veterinaria guardarVeterinaria(@RequestBody Veterinaria veterinaria);
	
	 @PutMapping
	 public Veterinaria editarVeterinaria(@RequestBody Veterinaria veterinaria);
	
	 @DeleteMapping("/{idVeterinaria}")
	 public Veterinaria eliminarVeterinaria(@PathVariable int idVeterinaria);
	 
}
