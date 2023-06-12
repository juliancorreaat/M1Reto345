package com.reto03.grupog6.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.redis.ClientResourcesBuilderCustomizer;
import org.springframework.stereotype.Service;

import com.reto03.grupog6.DTO.ClientsReport;
//import com.reto03.grupog6.DTO.ClientsReport;
import com.reto03.grupog6.Entities.Client;
import com.reto03.grupog6.Repository.ClientRepository;

@Service
public class ClientService {    // Es una clase que se encarga de las funcionalidades del negocio
    
    @Autowired
    private ClientRepository clientRepository;

    public Client addClient(Client client){
        Boolean bGrabar = true;


    if(client.getName() == null)                
        bGrabar = false; 

    if(client.getEmail() == null)
        bGrabar = false;

    if(client.getPassword() == null)
        bGrabar = false;

    if(client.getAge() != null)
        bGrabar = false;


    if(bGrabar == true)
        return clientRepository.addClient(client);

    else
        return client;
    }

    public List<Client> getAll(){
        return (List<Client>) clientRepository.getAll();
    }

    public Client updClient(Client client){
        return clientRepository.updClient(client);
    }

    public Client getClient(Integer id){
        return clientRepository.getClient(id);
    }

    public void delClient(Integer id){
        clientRepository.delClient(id);
    }

    public ClientsReport getClientsReport(){
        Integer totalReservas = clientRepository.CountReservations("completed");
        List<Client> listaTotal = new ArrayList<Client>();
        List<Client> lista = new ArrayList<Client>();
        
        listaTotal = getAll(); 

        for (Client client : listaTotal) {
             
        }


        ClientsReport clientsReport = new ClientsReport(totalReservas, lista);
        return clientsReport;
    }


}
