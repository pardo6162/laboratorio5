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
    String nnombre=null;
    long nidentificacion=0 ;
    String ntelefono=null;
    String ndireccion=null;
    String nemail=null;
    boolean error=false;
    public AlquilerItemsBean() {
        
    }
    public String getNnombre(){
        return  nnombre;
    }
    public void setNnombre(String nombre){
        nnombre=nombre;
    }
    public long getNidentificacion(){
        return  nidentificacion;
    }
    public void setNidentificacion(long id){
        nidentificacion=id;
    }
    public String getNtelefono(){
        return  ntelefono;
    }
    public void setNtelefono(String tel){
        ntelefono=tel;
    }
    public String getNdireccion(){
        return  ndireccion;
    }
    public void setNdireccion(String dir){
        ndireccion=dir;
    }
    public String getNemail(){
        return  nemail;
    }
    public void setNemail(String mail){
        nemail=mail;
    }
    public List<Cliente> getClientes() {
        List<Cliente> clientes= new ArrayList<Cliente>();
        try{
            clientes = sp.consultarClientes();
        }catch(Exception e){
           error=true; 
        }
        return  clientes;
    }
    public void registrarCliente(){
        try{
            sp.registrarCliente(new Cliente(nnombre,nidentificacion,ntelefono,ndireccion,nemail));
            String nnombre=null;
            nidentificacion=0 ;
            ntelefono=null;
            ndireccion=null;
            nemail=null;
        }catch(Exception e){
            error=true;
        }
    }

}
