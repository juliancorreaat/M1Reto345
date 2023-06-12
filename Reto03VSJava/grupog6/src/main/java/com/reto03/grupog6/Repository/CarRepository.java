package com.reto03.grupog6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.reto03.grupog6.CrudRepository.CarCrudRepository;
import com.reto03.grupog6.Entities.Car;
//import com.reto03.grupog6.Entities.Gama;

@Repository     // INTERACTURA CON EL CRUDREPOSITORY - SOLO TOMA LAS ACCIONES DE CRUD QUE NECESITA
public class CarRepository {
    
    @Autowired              // PERMITE CREAR IMPLEMENTACIONES E INSTANCIAS
    private CarCrudRepository carCrudRepository;

        /////////////////////////////////////////////////////////////////// MANEJADOR GET - READ

    public List<Car> getAll(){                             
        return (List<Car>) carCrudRepository.findAll(); // SE HACE UN CASPEO CON EL (List<Car>)
    }



            ///////////////////////////////////////////////////////// // MANEJADOR POST - CREATE
    public Car addCar(Car car){                   

        if (car.getIdCar() == null || car.getIdCar() == 0)
            return carCrudRepository.save(car);
        else    
            return car;
    }



    private Car existCar(Integer id){
        Optional<Car> objCar = carCrudRepository.findById(id); // EJECUTA UNA FUNCION QUE VA AL REPOSITORIO PARA GARANTIZAR QUE NO VAYA A FALLAR  Y GARANTIZA PODER EJECUTAR LA FUNCION SI EL DATO ES POSIBLEMENTE NULO
        Car objCarRespuesta;                                      // OBJETO GUARDARA LA RESPUESTA DE LA CONSULTA A LA BD
        //Car objCarReturn;

        if (objCar.isEmpty() == true)
            objCarRespuesta = null;
        else
            objCarRespuesta = objCar.get();
        return objCarRespuesta;
    }

    
                ////////////////////////////////////////////////// // MANEJADOR PUT - UPDATE
    public Car updCar(Car car){             

        Car objCar;
        //Gama gama = new Gama();

        objCar = existCar(car.getIdCar());   // VALIDACION DE EXISTENCIA
        if (objCar == null)
            return car;
        else{                                           // EVALUA SI CADA ELEMENTO DE LA TABLA CONTIENE VALORES
            if(car.getName() != null)                // EL IF PERMITE SABER SI HAY VALORES LOS CUALES PUEDEN SER CONSULTADOS POR UN USUARIO AL MISMO TIEMPO
                objCar.setName(car.getName());

            if(car.getBrand() != null)
                objCar.setBrand(car.getBrand());
            
            if(car.getYear() != null)
                objCar.setYear(car.getYear());

            if(car.getDescription() != null)
                objCar.setDescription(car.getDescription());
            
            //if(car.getGama().getIdGama() != null){
            //    gama.setIdGama(car.getGama().getIdGama());
            //    objCar.setGama(gama);

            //}

            return carCrudRepository.save(objCar);

            }
    }


                ////////////////////////////////////////////////// MANEJADOR DELETE

    public void delCar(Integer id){

        Car objCar;

        objCar = existCar(id);   // VALIDACION DE EXISTENCIA
        if (objCar != null)
            carCrudRepository.deleteById(id);
    }

    public Car getCar(Integer id){

        Car objCar;

        objCar = existCar(id);
        if(objCar != null)
            return objCar;
        else
            return null;
    }

}
