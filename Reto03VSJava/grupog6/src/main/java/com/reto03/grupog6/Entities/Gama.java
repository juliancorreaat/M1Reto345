package com.reto03.grupog6.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity     // REPRESENTA LOS ELEMENTOS DE LA TABLA A CREAR
@Table(name = "gama")

public class Gama implements Serializable{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idGama;
    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "gama") // GENERA UNA RESTRICCION SI SE QUIERE BORRAR LA RAMA PRINCIPAL: EL Gama"PADRE"
    @JsonIgnoreProperties("gama")
    private List<Car> cars;

    //ESTO NO VA AQUI
    // @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "gama") // GENERA UNA RESTRICCION SI SE QUIERE BORRAR LA RAMA PRINCIPAL: EL Gama"PADRE"
    // @JsonIgnoreProperties("gama")
    // private List<Reservation> reservations;


    public Gama() {               // CONSTRUCTOR VACIO GENERADO CON ACCION DE CODIGO FUENTE
    }

    public Gama(Integer idGama, String name, String description) {
        this.idGama = idGama;
        this.name = name;
        this.description = description;
    }



    public Integer getIdGama() {
        return idGama;
    }



    public void setIdGama(Integer idGama) {
        this.idGama = idGama;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    
    

    
    

}
