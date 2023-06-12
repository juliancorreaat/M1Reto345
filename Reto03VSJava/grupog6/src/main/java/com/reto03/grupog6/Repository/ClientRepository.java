package com.reto03.grupog6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.reto03.grupog6.CrudRepository.ClientCrudRepository;
import com.reto03.grupog6.Entities.Client;

@Repository     // INTERACTURA CON EL CRUDREPOSITORY - SOLO TOMA LAS ACCIONES DE CRUD QUE NECESITA
public class ClientRepository {
    
    @Autowired              // PERMITE CREAR IMPLEMENTACIONES E INSTANCIAS
    private ClientCrudRepository clientCrudRepository;

        /////////////////////////////////////////////////////////////////// MANEJADOR GET - READ

    public List<Client> getAll(){                             
        return (List<Client>) clientCrudRepository.findAll(); // SE HACE UN CASPEO CON EL (List<Client>)
    }



            ///////////////////////////////////////////////////////// // MANEJADOR POST - CREATE
    public Client addClient(Client client){                   

        if (client.getIdClient() == null || client.getIdClient() == 0)
            return clientCrudRepository.save(client);
        else    
            return client;
    }



    private Client existClient(Integer id){
        Optional<Client> objClient = clientCrudRepository.findById(id); // EJECUTA UNA FUNCION QUE VA AL REPOSITORIO PARA GARANTIZAR QUE NO VAYA A FALLAR
        Client objClientRespuesta;                                      // OBJETO GUARDARA LA RESPUESTA DE LA CONSULTA A LA BD

        if (objClient.isEmpty() == true)
            objClientRespuesta = null;
        else
            objClientRespuesta = objClient.get();
        return objClientRespuesta;
    }

    
                ////////////////////////////////////////////////// // MANEJADOR PUT - UPDATE
    public Client updClient(Client client){             

        Client objClient;

        objClient = existClient(client.getIdClient());   // VALIDACION DE EXISTENCIA
        if (objClient == null)
            return client;
        else{                                           // EVALUA SI CADA ELEMENTO DE LA TABLA CONTIENE VALORES
            if(client.getName() != null)                // EL IF PERMITE SABER SI HAY VALORES LOS CUALES PUEDEN SER CONSULTADOS POR UN USUARIO AL MISMO TIEMPO
                objClient.setName(client.getName());

            if(client.getEmail() != null)
                objClient.setEmail(client.getEmail());
            
            if(client.getPassword() != null)
                objClient.setPassword(client.getPassword());

            if(client.getAge() != null)
                objClient.setAge(client.getAge());

            return clientCrudRepository.save(objClient);

            }
    }


                ////////////////////////////////////////////////// MANEJADOR DELETE

    public void delClient(Integer id){

        Client objClient;

        objClient = existClient(id);   // VALIDACION DE EXISTENCIA
        if (objClient != null)
            clientCrudRepository.deleteById(id);
    }

    public Client getClient(Integer id){

        Client objClient;

        objClient = existClient(id);
        if(objClient != null)
            return objClient;
        else
            return null;
    }

    public Integer CountReservations(String status){
        return clientCrudRepository.CountReservations(status);
    }

}
