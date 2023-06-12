package com.reto03.grupog6.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.reto03.grupog6.DTO.ClientsReport;
import com.reto03.grupog6.Services.ClientService;

@RestController
@RequestMapping("/api/Reservation")
public class ClientReportController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/report-clients")
    public ClientsReport getClientsReport(){
        return clientService.getClientsReport();
    }
    
}

