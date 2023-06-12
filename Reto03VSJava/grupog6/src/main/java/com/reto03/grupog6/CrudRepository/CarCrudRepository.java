package com.reto03.grupog6.CrudRepository;

import org.springframework.data.repository.CrudRepository; // REALIZAR OPERACIONES DE CRUD SIN CODIGO - OPERACIONES TIPICAS DE ACCESO A BASE DE DATOS
                                                           // ES UNA INTERFAZ

import com.reto03.grupog6.Entities.Car;

public interface CarCrudRepository extends CrudRepository <Car,Integer>{
    
}
