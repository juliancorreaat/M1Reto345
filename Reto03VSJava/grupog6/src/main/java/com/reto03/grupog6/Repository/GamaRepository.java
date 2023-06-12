package com.reto03.grupog6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.reto03.grupog6.CrudRepository.GamaCrudRepository;
import com.reto03.grupog6.Entities.Gama;

@Repository     // INTERACTURA CON EL CRUDREPOSITORY - SOLO TOMA LAS ACCIONES DE CRUD QUE NECESITA
public class GamaRepository {
    
    @Autowired              // PERMITE CREAR IMPLEMENTACIONES E INSTANCIAS
    private GamaCrudRepository gamaCrudRepository;

        /////////////////////////////////////////////////////////////////// MANEJADOR GET - READ

    public List<Gama> getAll(){                             
        return (List<Gama>) gamaCrudRepository.findAll(); // SE HACE UN CASPEO CON EL (List<Gama>)
    }



            ///////////////////////////////////////////////////////// // MANEJADOR POST - CREATE
    public Gama addGama(Gama gama){                   

        if (gama.getIdGama() == null || gama.getIdGama() == 0)
            return gamaCrudRepository.save(gama);
        else    
            return gama;
    }



    private Gama existGama(Integer id){
        Optional<Gama> objGama = gamaCrudRepository.findById(id); // EJECUTA UNA FUNCION QUE VA AL REPOSITORIO PARA GARANTIZAR QUE NO VAYA A FALLAR
        Gama objGamaRespuesta;                                      // OBJETO GUARDARA LA RESPUESTA DE LA CONSULTA A LA BD
        
        
        if (objGama.isEmpty() == true)
            objGamaRespuesta = null;
        else
            objGamaRespuesta = objGama.get();
        return objGamaRespuesta;
    }

    
                ////////////////////////////////////////////////// // MANEJADOR PUT - UPDATE
    public Gama updGama(Gama gama){             

        Gama objGama;

        objGama = existGama(gama.getIdGama());   // VALIDACION DE EXISTENCIA
        if (objGama == null)
            return gama;
        else{                                           // EVALUA SI CADA ELEMENTO DE LA TABLA CONTIENE VALORES
            if(gama.getName() != null)                // EL IF PERMITE SABER SI HAY VALORES LOS CUALES PUEDEN SER CONSULTADOS POR UN USUARIO AL MISMO TIEMPO
                objGama.setName(gama.getName());

            if(gama.getDescription() != null)
                objGama.setDescription(gama.getDescription());
            
            return gamaCrudRepository.save(objGama);

            }
    }


                ////////////////////////////////////////////////// MANEJADOR DELETE

    public void delGama(Integer id){

        Gama objGama;

        objGama = existGama(id);   // VALIDACION DE EXISTENCIA
        if (objGama != null)
            gamaCrudRepository.deleteById(id);
    }

    public Gama getGama(Integer id){

        Gama objGama;

        objGama = existGama(id);
        if(objGama != null)
            return objGama;
        else
            return null;
    }

}

