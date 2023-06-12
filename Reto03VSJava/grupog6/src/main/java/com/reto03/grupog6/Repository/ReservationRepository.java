package com.reto03.grupog6.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.reto03.grupog6.CrudRepository.ReservationCrudRepository;
import com.reto03.grupog6.Entities.Car;
import com.reto03.grupog6.Entities.Client;
import com.reto03.grupog6.Entities.Reservation;

@Repository     // INTERACTURA CON EL CRUDREPOSITORY - SOLO TOMA LAS ACCIONES DE CRUD QUE NECESITA
public class ReservationRepository {
    
    @Autowired              // PERMITE CREAR IMPLEMENTACIONES E INSTANCIAS
    private ReservationCrudRepository reservationCrudRepository;

        /////////////////////////////////////////////////////////////////// MANEJADOR GET - READ

    public List<Reservation> getAll(){                             
        return (List<Reservation>) reservationCrudRepository.findAll(); // SE HACE UN CASPEO CON EL (List<Reservation>)
    }



            ///////////////////////////////////////////////////////// // MANEJADOR POST - CREATE
    public Reservation addReservation(Reservation reservation){                   

        if (reservation.getIdReservation() == null || reservation.getIdReservation() == 0)
            return reservationCrudRepository.save(reservation);
        else    
            return reservation;
    }



    private Reservation existReservation(Integer id){
        Optional<Reservation> objReservation = reservationCrudRepository.findById(id); // EJECUTA UNA FUNCION QUE VA AL REPOSITORIO PARA GARANTIZAR QUE NO VAYA A FALLAR
        Reservation objReservationRespuesta;                                      // OBJETO GUARDARA LA RESPUESTA DE LA CONSULTA A LA BD

        if (objReservation.isEmpty() == true)
            objReservationRespuesta = null;
        else
            objReservationRespuesta = objReservation.get();
        return objReservationRespuesta;
    }

    
                ////////////////////////////////////////////////// // MANEJADOR PUT - UPDATE
    public Reservation updReservation(Reservation reservation){             

        Reservation objReservation;
        Car car = new Car();
        Client client = new Client();

        objReservation = existReservation(reservation.getIdReservation());   // VALIDACION DE EXISTENCIA
        if (objReservation == null)
            return reservation;
        else{                                           // EVALUA SI CADA ELEMENTO DE LA TABLA CONTIENE VALORES
            if(reservation.getStartDate() != null)                // EL IF PERMITE SABER SI HAY VALORES LOS CUALES PUEDEN SER CONSULTADOS POR UN USUARIO AL MISMO TIEMPO
                objReservation.setStartDate(reservation.getStartDate());

            if(reservation.getDevolutionDate() != null)                // EL IF PERMITE SABER SI HAY VALORES LOS CUALES PUEDEN SER CONSULTADOS POR UN USUARIO AL MISMO TIEMPO
                objReservation.setDevolutionDate(reservation.getDevolutionDate());

            if(reservation.getStatus() != null)                // EL IF PERMITE SABER SI HAY VALORES LOS CUALES PUEDEN SER CONSULTADOS POR UN USUARIO AL MISMO TIEMPO
                objReservation.setStatus(reservation.getStatus());


            if(reservation.getCar().getIdCar() != null){
                car.setIdCar(reservation.getCar().getIdCar());
                objReservation.setCar(car);
            }

            if(reservation.getClient().getIdClient() != null){
                client.setIdClient(reservation.getClient().getIdClient());
                objReservation.setClient(client);
            }
                       

            return reservationCrudRepository.save(objReservation);
            
            ///////////////////////////////////////////////////////////////////////////////////************ */

        }
    }


                ////////////////////////////////////////////////// MANEJADOR DELETE

    public void delReservation(Integer id){

        Reservation objReservation;

        objReservation = existReservation(id);   // VALIDACION DE EXISTENCIA
        if (objReservation != null)
            reservationCrudRepository.deleteById(id);
    }

    public Reservation getReservation(Integer id){

        Reservation objReservation;

        objReservation = existReservation(id);
        if(objReservation != null)
            return objReservation;
        else
            return null;
    }

    //public List<Reservation> getReportReservation(Date duno, Date ddos) {
    //    return null;
    //}


 
     public List<Reservation> getReportReservation(Date dateOne, Date dateTwo){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }
 
    public Integer getReportStatusByQuery(String status){
        return reservationCrudRepository.CountByStatus(status);
    }
/*
    public List<Client> getReportTopClients(){
        List<Client> clients = new ArrayList<Client>();
        List<Object[]> topClients = reservationCrudRepository.OrderedByCompleted();
        Client clientTop;
        Integer idClient;

        for (Object[] client : topClients) {
            idClient = (Integer) client[0];
            clientTop = clientRepository.getClient(idClient);
            clients.add(clientTop);
        }
        return clients;
    }
  
*/


}

