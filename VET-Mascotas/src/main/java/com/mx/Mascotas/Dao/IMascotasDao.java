package com.mx.Mascotas.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Mascotas.Entity.Mascotas;

public interface IMascotasDao extends JpaRepository<Mascotas, Integer>{

}
