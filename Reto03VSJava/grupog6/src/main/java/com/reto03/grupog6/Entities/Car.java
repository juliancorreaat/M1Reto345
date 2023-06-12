package com.reto03.grupog6.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity     // REPRESENTA LOS ELEMENTOS DE LA TABLA A CREAR
@Table(name = "cars")

//public class Car implements Serializable{
    public class Car {

    @Id                                                     // Id es la 
    @GeneratedValue (strategy = GenerationType.IDENTITY)    // llave primaria (primary Key)
    private Integer idCar;                                  // Integer: ya que se requieren valores nulos (no usar int)
    private String name;                                    // Se especifican los atributos
    private String brand;
    private Integer year;
    private String description;


    @ManyToOne
    @JoinColumn(name="idGama")
    @JsonIgnoreProperties("cars")
    private Gama gama;              // LAS ENTIDADES CON ESTA LINEA DE CODIGO, ES NECESARIO INDICAR LAS FUNCIONES EN EL REPOSITORIO EN LA PARTE DEL UPDATE


    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "car") // GENERA UNA RESTRICCION SI SE QUIERE BORRAR LA RAMA PRINCIPAL: EL CLIENT"PADRE"
    @JsonIgnoreProperties({"car", "client" })
    private List<Message> messages;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "car") 
    @JsonIgnoreProperties({"car", "messages" })
    private List<Reservation> reversations;



    public Car() {               // CONSTRUCTOR VACIO GENERADO CON ACCION DE CODIGO FUENTE
    }


    //public Car(Integer idCar, String name, String brand, Integer year, String description, Gama gama, List<Message> messages, List<Reservation> reservations) {
        public Car(Integer idCar, String name, String brand, Integer year, String description) {
        this.idCar = idCar;
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.description = description;
        //this.gama = gama;
        //this.messages = messages;
        //this.reservations = reservations;
    }


    public Integer getIdCar() {
        return idCar;
    }


    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getBrand() {
        return brand;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }


    public Gama getGama() {
        return gama;
    }


    public void setGama(Gama gama) {
        this.gama = gama;
    }


    public List<Message> getMessages() {
        return messages;
    }


    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }


    public List<Reservation> getReversations() {
        return reversations;
    }


    public void setReversations(List<Reservation> reversations) {
        this.reversations = reversations;
    }


    public Integer getYear() {
        return year;
    }


    public void setYear(Integer year) {
        this.year = year;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    

}

