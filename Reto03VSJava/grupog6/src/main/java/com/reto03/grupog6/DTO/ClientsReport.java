package com.reto03.grupog6.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.reto03.grupog6.Entities.Client;

public class ClientsReport implements Serializable {
    private Integer total;
    private List<Client> client = new ArrayList<Client>();

    public ClientsReport() {
    }

    public ClientsReport(Integer total, List<Client> client) {
        this.total = total;
            this.client = client;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }
 
    
}
