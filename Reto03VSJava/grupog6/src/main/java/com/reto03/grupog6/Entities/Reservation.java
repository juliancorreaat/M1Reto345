package com.reto03.grupog6.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity     // REPRESENTA LOS ELEMENTOS DE LA TABLA A CREAR
@Table(name = "Reservaciones")

public class Reservation implements Serializable{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idReservation;
    private Date startDate;
    private Date devolutionDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "idCar")
    @JsonIgnoreProperties({"reservations","message"})
    //@JsonIgnoreProperties({"reservations"})
    private Car car; 

    @ManyToOne
    @JoinColumn(name = "idClient")
    @JsonIgnoreProperties({"messages","reservations"})
    private Client client; 

    //relacion con score
    @OneToOne(cascade={CascadeType.REMOVE}, mappedBy = "reservation")
    @JsonIgnoreProperties("reservation")
    @JoinColumn(name = "idScore")
    private Score score;


    public Reservation() {
    }


    public Reservation(Integer idReservation, Date startDate, Date devolutionDate, String status, Client client,
            Car car, Score score) {
        this.idReservation = idReservation;
        this.startDate = startDate;
        this.devolutionDate = devolutionDate;
        this.status = status;
        this.client = client;
        this.car = car;
        this.score = score;
    }



    public Integer getIdReservation() {
        return idReservation;
    }


    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }


    public Date getStartDate() {
        return startDate;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getDevolutionDate() {
        return devolutionDate;
    }


    public void setDevolutionDate(Date devolutionDate) {
        this.devolutionDate = devolutionDate;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public Client getClient() {
        return client;
    }


    public void setClient(Client client) {
        this.client = client;
    }


    public Car getCar() {
        return car;
    }


    public void setCar(Car car) {
        this.car = car;
    }


    public Score getScore() {
        return score;
    }


    public void setScore(Score score) {
        this.score = score;
    }

    
    
    
}
