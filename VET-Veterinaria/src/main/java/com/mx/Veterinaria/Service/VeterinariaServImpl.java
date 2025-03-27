package com.mx.Veterinaria.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mx.Veterinaria.Dao.IVeterinariaDao;
import com.mx.Veterinaria.Entity.Veterinaria;
import com.mx.Veterinaria.FeignClients.IClientesFeign;
import com.mx.Veterinaria.FeignClients.IMascotasFeign;
import com.mx.Veterinaria.FeignClients.IResponsablesFeign;
import com.mx.Veterinaria.Models.Clientes;
import com.mx.Veterinaria.Models.Mascotas;
import com.mx.Veterinaria.Models.Responsables;

@Service
public class VeterinariaServImpl implements IVeterinariaServ {
	
	//*************************INYECCIÓN DE DEPENDENCIAS
	@Autowired
	private IVeterinariaDao dao;
	
	/*@Autowired
	private IClientesFeign clientesFC;
	
	@Autowired
	private IMascotasFeign mascotasFC;
	
	@Autowired
	private IResponsablesFeign responsablesFC;*/
	
	
	//*************************SERVICIOS DE VETERINARIA
	@Override
	public List<Veterinaria> listar() {
		return dao.findAll(Sort.by(Direction.ASC,"idVeterinaria"));
	}

	@Override
	public Veterinaria buscar(int idVeterinaria) {
		return dao.findById(idVeterinaria).orElse(null);
	}

	@Override
	public Veterinaria guardar(Veterinaria veterinaria) {
		Veterinaria veterinariaExist= dao.findByNombreIgnoreCaseAndDireccionIgnoreCase(veterinaria.getNombre(), veterinaria.getDireccion());
		
		if(veterinariaExist!=null) {
			throw new RuntimeException("La veterinaria con el nombre '" +veterinaria.getNombre()+ "' en '"+veterinaria.getDireccion()+"' ya existe.");
		}
		return dao.save(veterinaria);
	}

	@Override
	public Veterinaria editar(Veterinaria veterinaria) {
		Veterinaria veterinariaExist= dao.findByNombreIgnoreCaseAndDireccionIgnoreCase(veterinaria.getNombre(), veterinaria.getDireccion());
		
		if(veterinariaExist!=null && veterinariaExist.getIdVeterinaria() != veterinaria.getIdVeterinaria())
			throw new RuntimeException("La veterinaria con el nombre '" +veterinaria.getNombre()+ "' en '"+veterinaria.getDireccion()+"' ya existe.");
		return dao.save(veterinaria);
	}

	@Override
	public Veterinaria eliminar(int idVeterinaria) {
		Veterinaria aux = this.buscar(idVeterinaria);
		if(aux!=null) {
			dao.deleteById(idVeterinaria);
			return aux;
		}
		return null;
	}
	
	/*//*************************SERVICIOS DE CLIENTES
	public List<Clientes> getClientes(Clientes cliente){
		return clientesFC.listarClientes();
	}
	
	public Clientes findCliente(int clienteId) {
		return clientesFC.buscarCliente(clienteId);
	}
	
	public Clientes saveCliente(Clientes cliente) {
		return clientesFC.guardarCliente(cliente);
	}
	
	public Clientes editCliente(Clientes cliente) {
		Clientes aux = this.findCliente(cliente.getIdCliente());
		if(aux!=null)
			return clientesFC.guardarCliente(cliente);
		return null;
	}
	
	public Clientes deleteCliente(int clienteId) {
		Clientes aux = this.findCliente(clienteId);
		if(aux!=null) {
			clientesFC.eliminarCliente(clienteId);
			return aux;
		}
		return null;
	}
	
	//*************************SERVICIOS DE MASCOTAS
	
	public List<Mascotas> getMascotas(Mascotas mascota){
		return mascotasFC.listarMascotas();
	}
	
	public Mascotas findMascota(int idMascota) {
		return mascotasFC.buscarMascota(idMascota);
	}
	
	public Mascotas saveMascota(Mascotas mascota) {
		return mascotasFC.guardarMascota(mascota);
	}
	
	public Mascotas editMascota(Mascotas mascota) {
		Mascotas aux = this.findMascota(mascota.getIdMascota());
		if(aux!=null)
			return mascotasFC.guardarMascota(mascota);
		return null;
	}
	
	public Mascotas deleteMascota(int idMascota) {
		Mascotas aux = this.findMascota(idMascota);
		if(aux!=null) {
			mascotasFC.eliminarMascota(idMascota);
			return aux;
		}
		return null;
	}
	
	
	//*************************SERVICIOS DE RESPONSSABLES
	
	public List<Responsables> getResponsables(Responsables responsable){
		return responsablesFC.listarResponsables();
	}
	
	public Responsables findResponsable(int idResponsable) {
		return responsablesFC.buscarResponsable(idResponsable);
	}
	
	public Responsables saveResponsable(Responsables responsable) {
		return responsablesFC.guardarResponsable(responsable);
	}
	
	public Responsables editResponsable(Responsables responsable) {
		Responsables aux = this.findResponsable(responsable.getIdResponsable());
		if(aux!=null)
			return responsablesFC.guardarResponsable(responsable);
		return null;
	}
	
	public Responsables deleteResponsable(int idResponsable) {
		Responsables aux = this.findResponsable(idResponsable);
		if(aux!=null) {
			responsablesFC.eliminarResponsable(idResponsable);
			return aux;
		}
		return null;
	}
	*/
}
