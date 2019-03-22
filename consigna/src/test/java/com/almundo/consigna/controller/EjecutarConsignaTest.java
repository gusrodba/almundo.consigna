package com.almundo.consigna.controller;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.fail;

/**
 * Clase Test para pruebas de la clase EjecutarConsigna 
 * @author Gustavo
 */
public class EjecutarConsignaTest {
    
    private final String[] ESCENARIO_UNO = {"4", "2", "1", "10"};
    private final String[] ESCENARIO_DOS = {"7", "2", "1", "50"};
    
    public EjecutarConsignaTest() {
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
     * Metodo que ejecuta la prueba del main, con los valores de la variable ESCENARIO
     * PARAMETRO 1 = Cantidad de operarios
     * PARAMETRO 2 = Cantidad de supervisores
     * PARAMETRO 3 = Cantidad de directores
     * PARAMETRO 4 = Cantidad de llamadas
     * Test of main method, of class EjecutarConsigna.
     */
    @Test
    public void testMain() {
        System.out.println("*************************************************************************************************************");
        System.out.println("************************************** TEST  EjecutarConsignaTest-main **************************************");
        System.out.println("********************************************* ESCENARIO UNO *************************************************");
        System.out.println("Se ejecuta: EjecutarConsigna.main("+Arrays.toString(ESCENARIO_UNO)+")");
        try {
            EjecutarConsigna.main(ESCENARIO_UNO);
            System.out.println("TEST EXITOSO");
            Assert.assertTrue(true);
        } catch (Exception e) {
            System.out.println("TEST FALLIDO");
            fail("No se pudo completar la ejecución de los hilos - " + e.getMessage());
        }
        
        System.out.println("********************************************* ESCENARIO DOS *************************************************");
        System.out.println("Se ejecuta: EjecutarConsigna.main("+Arrays.toString(ESCENARIO_DOS)+")");
        try {
            EjecutarConsigna.main(ESCENARIO_DOS);
            System.out.println("TEST EXITOSO");
            Assert.assertTrue(true);
        } catch (Exception e) {
            System.out.println("TEST FALLIDO");
            fail("No se pudo completar la ejecución de los hilos - " + e.getMessage());
        }
    }
}
