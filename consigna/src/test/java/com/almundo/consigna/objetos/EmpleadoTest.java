package com.almundo.consigna.objetos;

import com.almundo.consigna.enums.TipoEmpleadoEnum;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase Test para pruebas de la clase Empleado 
 * @author Gustavo
 */
public class EmpleadoTest {
    
    public EmpleadoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Metodo que ejecuta las prueba del metodo run de la clase empleado.
     * Se ejecutan dos escenarios
     * 1. Empleado sin llamada ni tiempo de ejecución
     * 2. Empleado con llamada y tiempo de ejecución
     */
    @Test
    public void testRun() {
        System.out.println("*************************************************************************************************************");
        System.out.println("******************************************* TEST EmpleadoTest-run *******************************************");
        System.out.println("*************************************************************************************************************");
        Boolean resultadoTestUno = Boolean.FALSE, resultadoTestDos = Boolean.FALSE;
        
        //ESCENARIO 1
        Empleado escenarioUno = new Empleado(new Long(1), "OPERARIO 1", TipoEmpleadoEnum.OPERADOR, 0);
        try {
            System.out.println("Escenario 1: Empleado sin llamada ni tiempo de ejecución");
            System.out.println("Empleado: Id: " + escenarioUno.getIdentificacion() + ", Nombre: " + escenarioUno.getNombre() + ", Tipo: " + escenarioUno.getTipo().tipo() + ", Cantidad Llamadas Atendidas: " + escenarioUno.getCantidadLlamadasAtendidas() );
            System.out.println("Se ejecuta: Empleado.start()");
            escenarioUno.start();
            System.out.println("VALIDACION EXITOSA");
            resultadoTestUno = Boolean.TRUE;
        } catch (Exception e) {
            System.out.println("VALIDACION FALLIDA");
            fail("Se presentaron errores ejecuntando el Hilo. - " + e.getMessage());
        }
        
        //ESCENARIO 2
        Empleado escenarioDos = new Empleado(new Long(2), "SUPERVISOR 2", TipoEmpleadoEnum.SUPERVISOR, 1);
        try {
            System.out.println("Escenario 2: Empleado con llamada y tiempo de ejecución");
            System.out.println("Empleado: Id: " + escenarioDos.getIdentificacion() + ", Nombre: " + escenarioDos.getNombre() + ", Tipo: " + escenarioDos.getTipo().tipo() + ", Catntidad Llamadas Atendidas: " + escenarioDos.getCantidadLlamadasAtendidas() );
            Random r = new Random();
            Long tiempo = (new Long(r.nextInt(100-0) + 0));
            Long idLlamada = (new Long(r.nextInt(100-0) + 0));
            Llamada llamada = new Llamada(idLlamada, "+57 (321) 226 54 71");
            escenarioDos.procesarLlamada(llamada, tiempo);
            System.out.println("Llamada: Id: " + llamada.getId() + ", Telefono:" + llamada.getTelefono());
            System.out.println("Tiempo: " + tiempo);
            System.out.println("Se ejecuta: Empleado.start()");
            escenarioDos.start();
            System.out.println("VALIDACION EXITOSA");
            resultadoTestDos = Boolean.TRUE;
        } catch (Exception e) {
            System.out.println("VALIDACION FALLIDA");
            fail("Se presentaron errores ejecuntando el Hilo. - " + e.getMessage());
        }
        
        if (resultadoTestUno && resultadoTestDos) {                    
            System.out.println("TEST EXITOSO");
            Assert.assertTrue(Boolean.TRUE);
        }
    }

    /**
     * Este metodo solo asigna la llamada y el tiempo al objeto empleado
     * Se ejecuta cuando se ha encontrado un empleado disponible y se quiere asignar la llamada
     */
    @Test
    public void testProcesarLlamada() {
        System.out.println("*************************************************************************************************************");
        System.out.println("************************************* TEST EmpleadoTest-procesarLlamada *************************************");
        System.out.println("*************************************************************************************************************");
        
        Boolean resultadoTestUno = Boolean.FALSE, resultadoTestDos = Boolean.FALSE;
        
        //ESCENARIO 1
        Empleado escenarioUno = new Empleado(new Long(1), "OPERARIO 1", TipoEmpleadoEnum.OPERADOR, 0);
        try {
            System.out.println("Escenario 1: Se asignan valores nulos");
            System.out.println("Se ejecuta: Empleado.procesarLlamada(null, null)");
            escenarioUno.procesarLlamada(null, null);
            System.out.println("VALIDACION EXITOSA");
            resultadoTestUno = Boolean.TRUE;
        } catch (Exception e) {
            System.out.println("VALIDACION FALLIDA");
            fail("Se presentaron errores. - " + e.getMessage());
        }
        
        //ESCENARIO 2
        Empleado escenarioDos = new Empleado(new Long(2), "SUPERVISOR 2", TipoEmpleadoEnum.SUPERVISOR, 1);
        try {
            Random r = new Random();
            Long tiempo = (new Long(r.nextInt(100-0) + 0));
            Long idLlamada = (new Long(r.nextInt(100-0) + 0));
            Llamada llamada = new Llamada(idLlamada, "+57 (321) 226 54 71");
            System.out.println("Escenario 2: Se asignan valores cargados");
            System.out.println("Se ejecuta: Empleado.procesarLlamada(llamada, " + tiempo + ")");
            escenarioDos.procesarLlamada(llamada, tiempo);
            System.out.println("VALIDACION EXITOSA");
            resultadoTestDos = Boolean.TRUE;
        } catch (Exception e) {
            System.out.println("VALIDACION FALLIDA");
            fail("Se presentaron errores. - " + e.getMessage());
        }
        
        if (resultadoTestUno && resultadoTestDos) {                    
            System.out.println("TEST EXITOSO");
            Assert.assertTrue(Boolean.TRUE);
        }
    }
}
