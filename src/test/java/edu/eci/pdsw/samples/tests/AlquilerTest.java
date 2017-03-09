/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerItemsStub;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * 
 * Calculo Multa:
 * 
 * Frontera:
 * CF1: Multas a devoluciones hechas en la fecha exacta (multa 0).
 * 
 * Clases de equivalencia:
 * CE1: Multas hechas a devolciones realizadas en fechas posteriores
 * a la limite. (multa multa_diaria*dias_retraso)
 * 
 * CE2: No debe dejar alquilar una pelicula que ya esta alquilada por alguien más
 * CE3: No debe dejar alquilar un item que no existe
 * 
 * 
 * 
 */
public class AlquilerTest {

    public AlquilerTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void CF1Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        
        
        Item i1=new Item(sa.consultarTipoItem(1), 44, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");        
        sa.registrarCliente(new Cliente("Juan Perez",3842,"24234","calle 123","aa@gmail.com"));
        sa.registrarItem(i1);
        

                
        Item item=sa.consultarItem(44);
        
        sa.registrarAlquilerCliente(java.sql.Date.valueOf("2005-12-20"), 3842, item, 5);
        
        
        assertEquals("No se calcula correctamente la multa (0) "
                + "cuando la devolucion se realiza el dia limite."
                ,0,sa.consultarMultaAlquiler(44, java.sql.Date.valueOf("2005-12-25")));
                
    }
    

    @Test
    public void CE1Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        

        //System.out.println(sa.consultarClientes().get(0).getNombre());
        //System.out.println(sa.consultarClientes().get(0).getDocumento());
        
        Item i1=new Item(sa.consultarTipoItem(1), 55, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");        
        sa.registrarCliente(new Cliente("Juanita Perez",9843,"24234","calle 123","aa@gmail.com"));
        sa.registrarItem(i1);
                
        Item item=sa.consultarItem(55);
        
        sa.registrarAlquilerCliente(java.sql.Date.valueOf("2005-12-20"), 9843, item, 5);
        
        //prueba: 3 dias de retraso
        assertEquals("No se calcula correctamente la multa "
                + "cuando la devolucion se realiza varios dias despues del limite."
                ,sa.valorMultaRetrasoxDia()*3,sa.consultarMultaAlquiler(55, java.sql.Date.valueOf("2005-12-28")));
                
    }
    
    @Test
    public void CE2Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        

        
        Item i1=new Item(sa.consultarTipoItem(1), 66, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");        
        sa.registrarCliente(new Cliente("Juana Perez",9844,"24234","calle 123","aa@gmail.com"));
        sa.registrarCliente(new Cliente("Juan Perdomo",9855,"24235","calle 124","bb@gmail.com"));
        sa.registrarItem(i1);
        
                
        Item item=sa.consultarItem(66);
        
        sa.registrarAlquilerCliente(java.sql.Date.valueOf("2005-12-20"), 9844, item, 5);
        try{
            sa.registrarAlquilerCliente(java.sql.Date.valueOf("2005-12-20"), 9855, item, 10);
            fail("registra el item por segunda vez");
        }
        catch(ExcepcionServiciosAlquiler e){
            assertTrue("No registra por segunda vez",true);
        }
        
        
                
    }
    
    @Test
    public void CE3Test() throws ExcepcionServiciosAlquiler{
        ServiciosAlquiler sa=ServiciosAlquilerItemsStub.getInstance();
        
        Item i1=new Item(sa.consultarTipoItem(1), 67, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");
       
        sa.registrarCliente(new Cliente("Joan Pardo",9845,"24774","calle 123","aa@gmail.com"));
        

        
        try{
            sa.registrarAlquilerCliente(java.sql.Date.valueOf("2005-12-20"), 9845, i1, 10);
            fail("registra un item que no existe");
        }
        catch(ExcepcionServiciosAlquiler e){
            assertTrue("No puede registrar alquiler de un item que no existe",true);
        }
        
        
                
    }
    
    
    
    
}
