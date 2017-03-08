/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerItemsStub;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * 
 */
public class ClientesTest {
    //CE1 DEBE REGISTRAR UN CLIENTE SIN PARAMETROS,TIPO -> NORMAL, RESULTADO SE CREA EL CLIENTE SIN NINGUNA DE LAS CARACTERISTICAS
    //CE2 SE DEBE REGISTRAR UN CLIENTE SIN INFORMACION DE QUE ESTA BETADO NI INFORMACION DE CUANTO A RENTADO, TIPO -> NORMAL , RESULTADO SE CREA EL CLIENTE SIN INFORMCAION DE BETADO Y SIN INFORMACION DE PELICULAS RENTADAS
    //CE3 SE REGISTRA UN CLIENTE CON TODA LA INFORMACION,TIPO -> NORMAL,RESULTADO EL CLIENTE TIENE TODA LA INFORMACION
    public ClientesTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void CE2(){
        String nombre="Juan Camilo Mantilla";
        long documento=1014287297;
        String telefono="2235923";
        String direccion="KRA 70 D # 66 78";
        String email="juan.mantilla@hotmail.com";
        Cliente nuevoC=new Cliente(nombre,documento,telefono,direccion,email);
        ServiciosAlquilerItemsStub sais=new ServiciosAlquilerItemsStub();
        try{
            sais.registrarCliente(nuevoC);
            Cliente copiaC=sais.consultarCliente(documento);
            assertEquals("no existe el nombre",nuevoC.getDocumento(),copiaC.getDocumento());
            assertEquals("no existe el nombre",nuevoC.getDireccion(),copiaC.getDireccion());
            assertEquals("no existe el nombre",nuevoC.getEmail(),copiaC.getEmail());
            assertEquals("no existe el nombre",nuevoC.getTelefono(),copiaC.getTelefono());
            
           
        }catch(Exception e){}
            
        
    }
    
    
    
}
