package com.reto03.grupog6.CrudRepository;

import org.springframework.data.repository.CrudRepository; // REALIZAR OPERACIONES DE CRUD SIN CODIGO - OPERACIONES TIPICAS DE ACCESO A BASE DE DATOS

import com.reto03.grupog6.Entities.Gama;

public interface GamaCrudRepository extends CrudRepository <Gama,Integer>{
    
}