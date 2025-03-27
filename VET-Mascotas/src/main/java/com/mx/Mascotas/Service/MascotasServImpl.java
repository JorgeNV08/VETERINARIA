package com.mx.Mascotas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mx.Mascotas.Dao.IMascotasDao;
import com.mx.Mascotas.Entity.Mascotas;
import com.mx.Mascotas.FeignClients.IClientesFeign;
import com.mx.Mascotas.FeignClients.IResponsablesFeign;
import com.mx.Mascotas.FeignClients.IVeterinariaFeign;
import com.mx.Mascotas.Models.Clientes;
import com.mx.Mascotas.Models.Responsables;
import com.mx.Mascotas.Models.Veterinaria;

@Service
public class MascotasServImpl implements IMascotasServ{

	@Autowired
	IMascotasDao dao;
	
	@Autowired
	IClientesFeign clientesFC;
	
	@Autowired
	IVeterinariaFeign veterinariaFC;
	
	@Autowired
	IResponsablesFeign responsablesFC;
	
	@Override
	public List<Mascotas> listar() {
		return dao.findAll(Sort.by(Direction.ASC,"idMascota"));
	}

	@Override
	public Mascotas buscar(int idMascota) {
		return dao.findById(idMascota).orElse(null);
	}

	@Override
	public Mascotas guardar(Mascotas mascota) {
		Mascotas mascotaExist= dao.findByNombreIgnoreCaseAndRazaIgnoreCaseAndClienteId(mascota.getNombre(), mascota.getRaza(), mascota.getClienteId());
		if(mascotaExist!=null) 
			throw new RuntimeException("La mascota '"+mascota.getNombre()+"' de raza '"+mascota.getRaza()+"' del cliente '"+mascota.getClienteId()+"' ya existe.");
		
		Clientes cliente = clientesFC.buscarCliente(mascota.getClienteId());
		Responsables responsable = responsablesFC.buscarResponsable(mascota.getResponsableId());
		Veterinaria veterinaria = veterinariaFC.buscarVeterinaria(mascota.getVeterinariaId());
		
		if(cliente == null)
			throw new RuntimeException("No existe el cliente con id'" + mascota.getClienteId() + "' de la mascota");
		
		if(responsable == null)
			throw new RuntimeException("No existe el responsable con id'" + mascota.getResponsableId() + "' de la mascota");
		
		if(veterinaria == null)
			throw new RuntimeException("No existe la veterinaria con id'" + mascota.getVeterinariaId() + "' de la mascota");
		
		
		return dao.save(mascota);
	}

	@Override
	public Mascotas editar(Mascotas mascota) {
		//Mascotas aux = this.buscar(mascota.getIdMascota());
		Mascotas mascotaExist= dao.findByNombreIgnoreCaseAndRazaIgnoreCaseAndClienteId(mascota.getNombre(), mascota.getRaza(), mascota.getClienteId());
		if(mascotaExist!=null && mascotaExist.getIdMascota()!=mascota.getIdMascota()) // Si la mascota ya existe y no es la misma
			throw new RuntimeException("La mascota '"+mascota.getNombre()+"' de raza '"+
					mascota.getRaza()+"' del cliente '"+mascota.getClienteId()+"' ya existe.");
		return dao.save(mascota);
	}

	@Override
	public Mascotas eliminar(int idMascota) {
		Mascotas aux = this.buscar(idMascota);
		if(aux!=null) {
			dao.deleteById(idMascota);
			return aux;
		}
		return null;
	}

}
