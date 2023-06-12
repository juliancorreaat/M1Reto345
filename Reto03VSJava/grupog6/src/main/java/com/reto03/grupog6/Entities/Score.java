package com.reto03.grupog6.Entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "scores")

public class Score implements Serializable{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer idScore;
    private String messageText;
    private Integer stars;

    @OneToOne
    @JsonIgnoreProperties("score")
    @JoinColumn(name = "idReservation")
    private Reservation reservation;

    //relacion con score
    @OneToOne(cascade = {CascadeType.REMOVE},mappedBy="reservation")
    @JsonIgnoreProperties("reservation")
    private Score score;

    public Score(Integer idScore, String messageText, Integer stars, Reservation reservation, Score score) {
        this.idScore = idScore;
        this.messageText = messageText;
        this.stars = stars;
        this.reservation = reservation;
        this.score = score;
    }

    public Score() {
    }

    public Integer getIdScore() {
        return idScore;
    }

    public void setIdScore(Integer idScore) {
        this.idScore = idScore;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }


  
    
    

}
