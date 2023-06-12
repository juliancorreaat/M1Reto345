package com.reto03.grupog6.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.redis.MessageResourcesBuilderCustomizer;
import org.springframework.stereotype.Service;

import com.reto03.grupog6.Entities.Message;
import com.reto03.grupog6.Repository.MessageRepository;

@Service
public class MessageService {    // Es una clase que se enmessagega de las funcionalidades del negocio
    
    @Autowired
    private MessageRepository messageRepository;

    public Message addMessage(Message message){     //
        Boolean bGrabar = true;

    if(message.getMessageText() == null)    //            
        bGrabar = false; 

    if(message.getCar().getIdCar() == null)     //
        bGrabar = false;
    

    if (message.getClient().getIdClient() == null)  //
        bGrabar = false;

    
    if(bGrabar == true)                                     //
        return messageRepository.addMessage(message);

    else
        return message;
    }

    public List<Message> getAll(){
        return (List<Message>) messageRepository.getAll();
    }

    public Message updMessage(Message message){
        return messageRepository.updMessage(message);
    }

    public Message getMessage(Integer id){
        return messageRepository.getMessage(id);
    }

    public void delMessage(Integer id){
        messageRepository.delMessage(id);
    }


}
