package com.reto03.grupog6.Services;

import java.text.SimpleDateFormat;    //
import java.util.ArrayList;           //
import java.util.Date;                //
import java.util.List;

//import org.apache.tomcat.jni.Status;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.redis.ReservationResourcesBuilderCustomizer; //
import org.springframework.stereotype.Service;

//import com.reto03.grupog6.DTO.ClientsReport;
import com.reto03.grupog6.DTO.StatusReport;
//import com.reto03.grupog6.Entities.Client;
import com.reto03.grupog6.Entities.Reservation;
import com.reto03.grupog6.Repository.ReservationRepository;

@Service
public class ReservationService {    // Es una clase que se enreservationga de las funcionalidades del negocio
    
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation addReservation(Reservation reservation){
        Boolean bGrabar = true;

    if(reservation.getStatus() == null)                
        reservation.setStatus("created");

    if(reservation.getStartDate() == null)   //             
        bGrabar = false;

    if(reservation.getDevolutionDate() == null) //
        bGrabar = false;

        
    if (reservation.getCar().getIdCar() == null)    //
            bGrabar = false;
    
    if (reservation.getClient().getIdClient() == null)  //
            bGrabar = false;   

    
    if(bGrabar == true)
        return reservationRepository.addReservation(reservation);

    else
        return reservation;
    }

    public List<Reservation> getAll(){
        return (List<Reservation>) reservationRepository.getAll();
    }

    public Reservation updReservation(Reservation reservation){
        return reservationRepository.updReservation(reservation);
    }

    public Reservation getReservation(Integer id){
        return reservationRepository.getReservation(id);
    }

    public void delReservation(Integer id){
        reservationRepository.delReservation(id);
    }

    public List<Reservation> getReportReservation(String dateOne, String dateTwo){
        SimpleDateFormat convertidor = new SimpleDateFormat("yyyy-MM-dd");
        Date duno = new Date();
        Date ddos = new Date();
        try {
            duno = convertidor.parse(dateOne);
            ddos = convertidor.parse(dateTwo);
            
        } catch (Exception e) {
            e.printStackTrace();    // VOLCADO DE LA PILA: VERIFICA EL FALLO EN EL MOMENTO DE EJECUCION
        }

        if (duno.before(ddos)){
            return reservationRepository.getReportReservation(duno, ddos);
        }
        else
            return new ArrayList<>();

    }

    
    public StatusReport getStatusReport() {
        List<Reservation> listReservations = reservationRepository.getAll();
        Integer completas = 0;
        Integer canceladas = 0;
        StatusReport statusReport = new StatusReport();

        for (Reservation reservation : listReservations) {
            if (reservation.getStatus().equalsIgnoreCase("completed"))
                completas++;
            if (reservation.getStatus().equalsIgnoreCase("cancelled"))
                canceladas++;
        }
        statusReport.setCancelled(canceladas);
        statusReport.setCompleted(completas);
        return statusReport;
    }
    
    
    
    public StatusReport getStatusReportQuery() {     //
        
        StatusReport statusReport = new StatusReport();
        Integer completas  = reservationRepository.getReportStatusByQuery("completed");
        Integer canceladas = reservationRepository.getReportStatusByQuery("cancelled");
        
        statusReport.setCancelled(canceladas);
        statusReport.setCompleted(completas);
        return statusReport;
    }

 
    //public List<Reservation> getReportReservation(String dateOne, String dateTwo) {   // Habilitar ??
    //    return null;
    //}
    

    //public List<ClientsReport> getClientsReport() {
    //    return null;
    //}

/* 
    public ClientsReport getClientsReport(){
        Integer totalReservas = getAll().size();
        ClientService clientService = new ClientService();
        List<Client> lista = new ArrayList<Client>();
        
        lista = clientService.getAll(); 

        ClientsReport clientsReport = new ClientsReport(totalReservas, lista);
        return clientsReport;
    }
*/


    

}

