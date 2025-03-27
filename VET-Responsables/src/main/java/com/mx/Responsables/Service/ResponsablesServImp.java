package com.mx.Responsables.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mx.Responsables.Dao.IResponsablesDao;
import com.mx.Responsables.Entity.Responsables;

@Service
public class ResponsablesServImp implements IResponsablesServ{
	
	@Autowired
	IResponsablesDao dao;

	@Override
	public List<Responsables> listar() {
		return dao.findAll(Sort.by(Direction.ASC,"idResponsable"));
	}

	@Override
	public Responsables buscar(int idResponsable) {
		return dao.findById(idResponsable).orElse(null);
	}

	@Override
	public Responsables guardar(Responsables responsable) {
		Responsables responsableExist = dao.findByNombreIgnoreCaseAndVeterinariaId(responsable.getNombre(), responsable.getVeterinariaId());
	
		
		if(responsableExist!=null) {
			throw new RuntimeException("El responsable con el nombre '" + responsable.getNombre() +"' ya existe en la veterinaria '"+ responsable.getVeterinariaId());
		}
		return dao.save(responsable);
	}

	@Override
	public Responsables editar(Responsables responsable) {
		Responsables responsableExist = dao.findByNombreIgnoreCaseAndVeterinariaId(responsable.getNombre(), responsable.getVeterinariaId());

		if(responsableExist!=null && responsableExist.getIdResponsable()!=responsable.getIdResponsable()) {
			throw new RuntimeException("El responsable con el nombre '" + responsable.getNombre() +"' ya existe en la veterinaria '"+ responsable.getVeterinariaId());
		}
		return dao.save(responsable);
	}

	@Override
	public Responsables eliminar(int idResponsable) {
		Responsables aux = this.buscar(idResponsable);
		if(aux!=null) {
			dao.deleteById(idResponsable);
			return aux;
		}
		return null;
	}

}
