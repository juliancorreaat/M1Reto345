package com.reto03.grupog6.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.redis.ScoreResourcesBuilderCustomizer;
import org.springframework.stereotype.Service;

import com.reto03.grupog6.Entities.Score;
import com.reto03.grupog6.Repository.ScoreRepository;

@Service
public class ScoreService {    // Es una clase que se enscorega de las funcionalidades del negocio
    
    @Autowired
    private ScoreRepository scoreRepository;

    public Score addScore(Score score){
        Boolean bGrabar = true;

    if(score.getMessageText() == null)                
        bGrabar = false; 

    if(score.getStars() == null)
        bGrabar = false;
    
    
    if(score.getReservation().getIdReservation() == null)
        bGrabar = false;


    if(bGrabar == true)
        return scoreRepository.addScore(score);

    else
        return score;
    }

    public List<Score> getAll(){
        return (List<Score>) scoreRepository.getAll();
    }

    public Score updScore(Score score){
        return scoreRepository.updScore(score);
    }

    public Score getScore(Integer id){
        return scoreRepository.getScore(id);
    }

    public void delScore(Integer id){
        scoreRepository.delScore(id);
    }


}

