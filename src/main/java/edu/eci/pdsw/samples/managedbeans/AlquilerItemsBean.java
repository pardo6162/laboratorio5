/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2106913
 */
@ManagedBean(name = "AlquilerItems")
@SessionScoped
public class AlquilerItemsBean implements Serializable {

    ServiciosAlquiler sp = ServiciosAlquiler.getInstance();
    
    public AlquilerItemsBean() {
        
    }
    public List<Cliente> getClientes() {
        List<Cliente> clientes= new ArrayList<Cliente>();
        try{
            clientes = sp.consultarClientes();
        }catch(Exception e){
            
        }
        return  clientes;
    }
    public void setClientes(Cliente nCLient){
        
    }

}
