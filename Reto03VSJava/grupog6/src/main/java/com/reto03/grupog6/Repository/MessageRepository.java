package com.reto03.grupog6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.reto03.grupog6.CrudRepository.MessageCrudRepository;
import com.reto03.grupog6.Entities.Car;
import com.reto03.grupog6.Entities.Client;
import com.reto03.grupog6.Entities.Message;

@Repository     // INTERACTURA CON EL CRUDREPOSITORY - SOLO TOMA LAS ACCIONES DE CRUD QUE NECESITA
public class MessageRepository {
    
    @Autowired              // PERMITE CREAR IMPLEMENTACIONES E INSTANCIAS
    private MessageCrudRepository messageCrudRepository;

        /////////////////////////////////////////////////////////////////// MANEJADOR GET - READ

    public List<Message> getAll(){                             
        return (List<Message>) messageCrudRepository.findAll(); // SE HACE UN CASPEO CON EL (List<Message>)
    }



            ///////////////////////////////////////////////////////// // MANEJADOR POST - CREATE
    public Message addMessage(Message message){                   

        if (message.getIdMessage() == null || message.getIdMessage() == 0)
            return messageCrudRepository.save(message);
        else    
            return message;
    }



    private Message existMessage(Integer id){
        Optional<Message> objMessage = messageCrudRepository.findById(id); // EJECUTA UNA FUNCION QUE VA AL REPOSITORIO PARA GARANTIZAR QUE NO VAYA A FALLAR
        Message objMessageRespuesta;                                      // OBJETO GUARDARA LA RESPUESTA DE LA CONSULTA A LA BD

        if (objMessage.isEmpty() == true)
            objMessageRespuesta = null;
        else
            objMessageRespuesta = objMessage.get();
        return objMessageRespuesta;
    }

    
                ////////////////////////////////////////////////// // MANEJADOR PUT - UPDATE
    public Message updMessage(Message message){             

        Message objMessage;
        Car car = new Car();
        Client client = new Client();

        objMessage = existMessage(message.getIdMessage());   // VALIDACION DE EXISTENCIA
        if (objMessage == null)
            return message;
        else{                                           // EVALUA SI CADA ELEMENTO DE LA TABLA CONTIENE VALORES
            if(message.getMessageText() != null)                // EL IF PERMITE SABER SI HAY VALORES LOS CUALES PUEDEN SER CONSULTADOS POR UN USUARIO AL MISMO TIEMPO
                objMessage.setMessageText(message.getMessageText());

            if(message.getClient().getIdClient() != null){
                client.setIdClient(message.getClient().getIdClient());
                objMessage.setClient(client);
            }

            if(message.getCar().getIdCar() != null){
                car.setIdCar(message.getCar().getIdCar());
                objMessage.setCar(car);
            }
            
            return messageCrudRepository.save(objMessage);
            
            ///////////////////////////////////////////////////////////******************** */

        }
    }

                ////////////////////////////////////////////////// MANEJADOR DELETE

    public void delMessage(Integer id){

        Message objMessage;

        objMessage = existMessage(id);   // VALIDACION DE EXISTENCIA
        if (objMessage != null)
            messageCrudRepository.deleteById(id);
    }

    public Message getMessage(Integer id){

        Message objMessage;

        objMessage = existMessage(id);
        if(objMessage != null)
            return objMessage;
        else
            return null;
    }

}
