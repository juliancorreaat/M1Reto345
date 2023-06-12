package com.reto03.grupog6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.reto03.grupog6.CrudRepository.ScoreCrudRepository;
import com.reto03.grupog6.Entities.Score;
//import com.reto03.grupog6.Entities.Gama;
import com.reto03.grupog6.Entities.Reservation;

@Repository     // INTERACTURA CON EL CRUDREPOSITORY - SOLO TOMA LAS ACCIONES DE CRUD QUE NECESITA
public class ScoreRepository {
    
    @Autowired              // PERMITE CREAR IMPLEMENTACIONES E INSTANCIAS
    private ScoreCrudRepository scoreCrudRepository;

        /////////////////////////////////////////////////////////////////// MANEJADOR GET - READ

    public List<Score> getAll(){                             
        return (List<Score>) scoreCrudRepository.findAll(); // SE HACE UN CASPEO CON EL (List<Score>)
    }



            ///////////////////////////////////////////////////////// // MANEJADOR POST - CREATE
    public Score addScore(Score score){                   

        if (score.getIdScore() == null || score.getIdScore() == 0)
            return scoreCrudRepository.save(score);
        else    
            return score;
    }



    private Score existScore(Integer id){
        Optional<Score> objScore = scoreCrudRepository.findById(id); // EJECUTA UNA FUNCION QUE VA AL REPOSITORIO PARA GARANTIZAR QUE NO VAYA A FALLAR
        Score objScoreRespuesta;                                      // OBJETO GUARDARA LA RESPUESTA DE LA CONSULTA A LA BD

        if (objScore.isEmpty() == true)
            objScoreRespuesta = null;
        else
            objScoreRespuesta = objScore.get();
        return objScoreRespuesta;
    }

    
                ////////////////////////////////////////////////// // MANEJADOR PUT - UPDATE
    public Score updScore(Score score){             

        Score objScore;
        Reservation reservation = new Reservation();

        objScore = existScore(score.getIdScore());   // VALIDACION DE EXISTENCIA
        if (objScore == null)
            return score;
        else{                                           // EVALUA SI CADA ELEMENTO DE LA TABLA CONTIENE VALORES
            if(score.getMessageText() != null)                // EL IF PERMITE SABER SI HAY VALORES LOS CUALES PUEDEN SER CONSULTADOS POR UN USUARIO AL MISMO TIEMPO
                objScore.setMessageText(score.getMessageText());

            if(score.getStars() != null)
                objScore.setStars(score.getStars());
            
            if(score.getReservation() != null)
                reservation.setIdReservation(score.getReservation().getIdReservation());
                objScore.setReservation(reservation);

            }

            return scoreCrudRepository.save(objScore);

            
    }


                ////////////////////////////////////////////////// MANEJADOR DELETE

    public void delScore(Integer id){

        Score objScore;

        objScore = existScore(id);   // VALIDACION DE EXISTENCIA
        if (objScore != null)
            scoreCrudRepository.deleteById(id);
    }

    public Score getScore(Integer id){

        Score objScore;

        objScore = existScore(id);
        if(objScore != null)
            return objScore;
        else
            return null;
    }

}
