package com.reto03.grupog6.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.reto03.grupog6.DTO.ClientsReport;
import com.reto03.grupog6.DTO.StatusReport;
import com.reto03.grupog6.Entities.Reservation;
import com.reto03.grupog6.Services.ReservationService;

            // EL CONTROLADOR ES EL ENCARGADO DE GESTIONAR (ACCESOS-SEGURIDAD-RECURSOS) LAS PETICIONES

@RestController
@RequestMapping("/api/Reservation")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll(){
        return reservationService.getAll();
    }

    @PostMapping("/save")       // REV
    public Reservation addReservation(@RequestBody Reservation reservation){
        return reservationService.addReservation(reservation);
    }

    @PutMapping("/update")              // Rev +-
    public Reservation updReservation(@RequestBody Reservation reservation){
        return reservationService.updReservation(reservation);
    }

    @DeleteMapping("/{id}")     // REV
    public void delReservation(@PathVariable Integer id){
        reservationService.delReservation(id);
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable Integer id){
        return reservationService.getReservation(id);   //here Rev
    }

    @GetMapping("/report-dates/{dateOne}/{dateTwo}")    //rev
    public List<Reservation> getReservationsReportDates(@PathVariable("dateOne") String dateOne,
    //public List<Reservation> getReportReservation(@PathVariable("dateOne") String dateOne,
        @PathVariable("dateTwo") String dateTwo){
        return reservationService.getReportReservation(dateOne,dateTwo);
    }

    @GetMapping("/report-status")
    public StatusReport getStatusReport() {
        return reservationService.getStatusReportQuery();
    }


    //@GetMapping("/report-clients")      // Rev
    //    //public ClientsReport getClientsReport(){
    //public List<ClientsReport> getClientsReport(){
    //    return reservationService.getClientsReport();
    //}

        //@GetMapping("/report-status")   // rev
    //public StatusReport getReportStatus(){
    //    return reservationService.getStatusReportQuery();
    //}
    
    //@GetMapping("/report-status-query")     // rev - esto con sql query
    //public StatusReport getStatusReportQuery(){
    //    return reservationService.getStatusReportQuery();
    //}

}
