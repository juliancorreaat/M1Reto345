package com.reto03.grupog6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import com.reto03.grupog6.CrudRepository.AdminCrudRepository;
import com.reto03.grupog6.Entities.Admin;

@Repository     // INTERACTURA CON EL CRUDREPOSITORY - SOLO TOMA LAS ACCIONES DE CRUD QUE NECESITA
public class AdminRepository {
    
    @Autowired              // PERMITE CREAR IMPLEMENTACIONES E INSTANCIAS
    private AdminCrudRepository adminCrudRepository;

        /////////////////////////////////////////////////////////////////// MANEJADOR GET - READ

    public List<Admin> getAll(){                             
        return (List<Admin>) adminCrudRepository.findAll(); // SE HACE UN CASPEO CON EL (List<Admin>)
    }



            ///////////////////////////////////////////////////////// // MANEJADOR POST - CREATE
    public Admin addAdmin(Admin admin){                   

        if (admin.getIdAdmin() == null || admin.getIdAdmin() == 0)
            return adminCrudRepository.save(admin);
        else    
            return admin;
    }



    private Admin existAdmin(Integer id){
        Optional<Admin> objAdmin = adminCrudRepository.findById(id); // EJECUTA UNA FUNCION QUE VA AL REPOSITORIO PARA GARANTIZAR QUE NO VAYA A FALLAR
        Admin objAdminRespuesta;                                      // OBJETO GUARDARA LA RESPUESTA DE LA CONSULTA A LA BD

        if (objAdmin.isEmpty() == true)
            objAdminRespuesta = null;
        else
            objAdminRespuesta = objAdmin.get();
        return objAdminRespuesta;
    }

    
                ////////////////////////////////////////////////// // MANEJADOR PUT - UPDATE
    public Admin updAdmin(Admin admin){             

        Admin objAdmin;
        
        objAdmin = existAdmin(admin.getIdAdmin());   // VALIDACION DE EXISTENCIA
        if (objAdmin == null)
            return admin;
        else{                                           // EVALUA SI CADA ELEMENTO DE LA TABLA CONTIENE VALORES
            if(admin.getName() != null)                // EL IF PERMITE SABER SI HAY VALORES LOS CUALES PUEDEN SER CONSULTADOS POR UN USUARIO AL MISMO TIEMPO
                objAdmin.setName(admin.getName());

            if(admin.getPassword() != null)
                objAdmin.setPassword(admin.getPassword());
            
            if(admin.getEmail() != null)
                objAdmin.setEmail(admin.getEmail());
            

            return adminCrudRepository.save(objAdmin);

            }
    }


                ////////////////////////////////////////////////// MANEJADOR DELETE

    public void delAdmin(Integer id){

        Admin objAdmin;

        objAdmin = existAdmin(id);   // VALIDACION DE EXISTENCIA
        if (objAdmin != null)
            adminCrudRepository.deleteById(id);
    }

    public Admin getAdmin(Integer id){

        Admin objAdmin;

        objAdmin = existAdmin(id);
        if(objAdmin != null)
            return objAdmin;
        else
            return null;
    }

}

