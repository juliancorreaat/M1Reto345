package com.reto03.grupog6.CrudRepository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository; // REALIZAR OPERACIONES DE CRUD SIN CODIGO - OPERACIONES TIPICAS DE ACCESO A BASE DE DATOS

import com.reto03.grupog6.Entities.Reservation;

public interface ReservationCrudRepository extends CrudRepository <Reservation,Integer>{
    
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);

    @Query(value="Select count(*) from Reservaciones where status = ?", nativeQuery=true)
    public Integer CountByStatus(String status);
}
