package com.reto03.grupog6.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.http.HttpStatus;   // JC
import com.reto03.grupog6.Entities.Admin;
import com.reto03.grupog6.Services.AdminService;

            // EL CONTROLADOR ES EL ENCARGADO DE GESTIONAR (ACCESOS-SEGURIDAD-RECURSOS) LAS PETICIONES

@RestController
@RequestMapping("/api/Admin")
public class AdminController {
    
    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public List<Admin> getAll(){
        return adminService.getAll();
    }

    @PostMapping("/save")
    //@ResponseStatus(HttpStatus.CREATED)     // JC
    public Admin addAdmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }

    @PutMapping("/update")
    //@ResponseStatus(HttpStatus.CREATED)      // JC
    public Admin updAdmin(@RequestBody Admin admin){
        return adminService.updAdmin(admin);
    }

    @DeleteMapping("/{id}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)      // JC
    public void delAdmin(@PathVariable Integer id){
        adminService.delAdmin(id);
    }

    @GetMapping("/{id}")
    public Admin getAdmin(@PathVariable Integer id) {
        return adminService.getAdmin(id);
    }

}

