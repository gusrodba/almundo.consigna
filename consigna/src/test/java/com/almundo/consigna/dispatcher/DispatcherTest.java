package com.almundo.consigna.dispatcher;

import com.almundo.consigna.enums.TipoEmpleadoEnum;
import com.almundo.consigna.objetos.Empleado;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assert;

/**
 * Clase Test para pruebas de la clase Dispatcher 
 * @author Gustavo
 */
public class DispatcherTest {
    
    /**
     * VARIABLES PARA EJECUCION DEL METODO testDispatchCall
     */
    private final Integer CANT_OPERARIOS = 2;
    private final Integer CANT_SUPERVISORES = 0;
    private final Integer CANT_DIRECTORES = 0;
    private final Integer CANT_LLAMADAS = 2;
    
    public DispatcherTest() {
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
     * Metodo que ejecuta la prueba del metodo dispatchCall de la clase DispatcherTest
     * Este motodo recibe una lista de empleados que se usara para atender las llamadas y una cantidad de llamadas que seran las q se crearan para simular el flujo de llamadas
     * de un call center.
     * La prueba consiste en enviar una cantidad de empleados y llamadas para ver si el metodo evalua los posibles flujos sin generar errores.
     * @throws java.lang.Exception
     */
    @Test
    public void testDispatchCall() throws Exception {
        System.out.println("*************************************************************************************************************");
        System.out.println("************************************* TEST  DispatcherTest-dispatchCall *************************************");
        System.out.println("*************************************************************************************************************");

        //Se crea la lista de empleados 
        List<Empleado> lstEmpleados = new ArrayList<>();
        lstEmpleados.addAll(crearListaEmpleados(CANT_OPERARIOS,      TipoEmpleadoEnum.OPERADOR,   0));
        lstEmpleados.addAll(crearListaEmpleados(CANT_SUPERVISORES,   TipoEmpleadoEnum.SUPERVISOR, CANT_OPERARIOS));
        lstEmpleados.addAll(crearListaEmpleados(CANT_DIRECTORES,     TipoEmpleadoEnum.DIRECTOR,   (CANT_OPERARIOS+CANT_SUPERVISORES)));
        
        System.out.println("CANT_OPERARIOS: " + CANT_OPERARIOS);
        System.out.println("CANT_SUPERVISORES: " + CANT_SUPERVISORES);        
        System.out.println("CANT_DIRECTORES: " + CANT_DIRECTORES);       
        System.out.println("CANT_LLAMADAS: " + CANT_LLAMADAS);

        //Se ejecuta el metodo a probar
        try {
            System.out.println("Se ejecuta: Dispatcher.dispatchCall(lstEmpleados, "+CANT_LLAMADAS+")");
            Dispatcher.dispatchCall(lstEmpleados, CANT_LLAMADAS);
            System.out.println("TEST EXITOSO");
            Assert.assertTrue(Boolean.TRUE);  
        } catch (InterruptedException e) {
            System.out.println("TEST FALLIDO");
            fail("No se pudo completar la ejecución de los hilos - " + e.getMessage());
        }
    }

    /**
     * Este metodo no recibe parametros y su funcion principal es aumentar en uno el indice de las llamadas que se encuentran en linea.
     * Es ejecutado cuando una llamada es asignada a un empleado.
     * Retorna un String con el numero de llamadas que se encuentran en linea despues de haberse ejecutado
     */
    @Test
    public void testIniciarLlamada() {
        System.out.println("*************************************************************************************************************");
        System.out.println("************************************ TEST  DispatcherTest-iniciarLlamada ************************************");
        System.out.println("*************************************************************************************************************");

        System.out.println("Se ejecuta: Dispatcher.iniciarLlamada()");
        String resultado = Dispatcher.iniciarLlamada().trim();
        
        System.out.println("Se evaluara el resultado");
        if (resultado == null || resultado.isEmpty()) {
            fail("El metodo no devolvio ningun valor cuando el resultado esperado era un número en una variable tipo String.");
        }else{
            try {
                Integer valor = Integer.parseInt(resultado);
                if (valor < 0) {
                    System.out.println("TEST FALLIDO");
                    fail("El metodo no devolvio un valor permitido pues el resultado esperado era un número mayor o igual a 0 en una variable tipo String.");
                }else{
                    System.out.println("TEST EXITOSO");
                    Assert.assertTrue(Boolean.TRUE);
                }
            } catch (NumberFormatException e) {
                    System.out.println("TEST FALLIDO");
                fail("El metodo no devolvio un valor númerico cuando el resultado esperado era un número en una variable tipo String. - " + e.getMessage());
            }
        }
    }

    /**
     * Este metodo no recibe parametros y su funcion principal es decrecer en uno el indice de las llamadas que se encuentran en linea.
     * Es ejecutado cuando una llamada es terminada de atender por el empleado (cuando se finaliza el metodo run del hilo)
     * Retorna un String con el numero de llamadas que se encuentran en linea despues de haberse ejecutado
     */

    @Test
    public void testColgarLlamada() {
        System.out.println("*************************************************************************************************************");
        System.out.println("************************************* TEST DispatcherTest-colgarLlamada *************************************");
        System.out.println("*************************************************************************************************************");

        System.out.println("Se ejecuta: Dispatcher.colgarLlamada()");        
        String resultado = Dispatcher.colgarLlamada().trim();
        
        System.out.println("Se evaluara el resultado");
        if (resultado == null || resultado.isEmpty()) {
            System.out.println("TEST FALLIDO");
            fail("El metodo no devolvio ningun valor cuando el resultado esperado era un número en una variable tipo String.");
        }else{
            try {
                Integer valor = Integer.parseInt(resultado);
                if (valor < 0) {
                    System.out.println("TEST FALLIDO");
                    fail("El metodo no devolvio un valor permitido pues el resultado esperado era un número mayor o igual a 0 en una variable tipo String.");
                }else{
                    System.out.println("TEST EXITOSO");
                    Assert.assertTrue(Boolean.TRUE);
                }
            } catch (NumberFormatException e) {
                System.out.println("TEST FALLIDO");
                fail("El metodo no devolvio un valor numerico cuando el resultado esperado era un número en una variable tipo String. - " + e.getMessage());
            }
        }
    }

    /**
     * Este metodo es el encargado de agregar un empleado que finalizo una llamada a la lista de empleados disponibles para que asi peuda ser asignado a una nueva llamada
     * Es ejecutado cuando una llamada es terminada de atender por el empleado (cuando se finaliza el metodo run del hilo)
     */
    @Test
    public void testAgregarEmpleadoDisponible() {
        System.out.println("*************************************************************************************************************");
        System.out.println("******************************* TEST DispatcherTest-agregarEmpleadoDisponible *******************************");
        System.out.println("*************************************************************************************************************");

        //Se ejecita la prueba para varios escenarios.
        //1. Para agregar un empleado tipo operario disponible
        //2. Para agregar un empleado tipo supervisor disponible
        //3. Para agregar un empleado tipo director disponible
        //4. Se envia un escenario donde el tipo de tipo de empleado va null
        //5. Se envia un escenario donde el tipo de tipo de empleado va vacio
        //6. Tambien se deja el randon del id en valor 0 para contemple dicho escenario
        //NOTA: el valor del parametro cantidad si puede ser 0 a superior sin limite
        Random r = new Random();
        Integer ejeTipo = 0;
        Boolean resultadoTest = Boolean.FALSE;
        String msnTest = "";
        while (ejeTipo < 5) {
            String tipoEmpleado = (ejeTipo == 0 ? TipoEmpleadoEnum.OPERADOR.tipo(): (ejeTipo == 1 ? TipoEmpleadoEnum.SUPERVISOR.tipo() : (ejeTipo == 2 ? TipoEmpleadoEnum.DIRECTOR.tipo() : (ejeTipo == 3 ? null : ""))));
            Long id = (new Long(r.nextInt(CANT_OPERARIOS-0) + 0));
            Integer cantidad = (r.nextInt(1000-0) + 0);
            Boolean respuesta = Dispatcher.agregarEmpleadoDisponible(id, tipoEmpleado, cantidad);
            System.out.println("Se ejecuta: Dispatcher.agregarEmpleadoDisponible("+id+","+tipoEmpleado+","+cantidad+") return " + respuesta);
            
            if ((ejeTipo > 2 && respuesta) || (ejeTipo <= 2 && id <= 0 && respuesta) || (ejeTipo > 2 && respuesta)) {
                System.out.println("VALIDACION FALLIDA");
                msnTest = "El proceso fue exitoso aunque se esperaba que fallara pues los datos enviados no cumplen con los requisietos necesarios.";
                resultadoTest = Boolean.FALSE;
                break;
            }else if((ejeTipo <= 2 && id > 0 && respuesta) || (ejeTipo <= 2 && id <= 0 && !respuesta) || (ejeTipo > 2 && !respuesta)){
                System.out.println("VALIDACION EXITOSA");
                resultadoTest = Boolean.TRUE;
            }else{
                System.out.println("VALIDACION FALLIDA");
                msnTest = "No se contemplo este escenario.";
                resultadoTest = Boolean.FALSE;
                break;
            }
            ejeTipo++;
        }
        
        //Solo se acepta el test cuando todos los escenarios recibieron el valor esperado
        if (resultadoTest) {                    
            System.out.println("TEST EXITOSO");
            Assert.assertTrue(Boolean.TRUE);
        }else{                
            System.out.println("TEST FALLIDO");
            fail(msnTest);
        }
    }
    
    /**
     * Se copia este metodo de la clase EjecutarConsigna, poues se usa para crear la lista de empleados bajo parametros 
     * NO HACE PARTE DEL TEST
     * @param cantidadEmpleados cantidad de empleados que se desean crear
     * @param tipo el tipo de empleados que se desean
     * @param consecutivo consecutivo de empleados
     * @return 
     */
    private static List<Empleado> crearListaEmpleados(Integer cantidadEmpleados, TipoEmpleadoEnum tipo, Integer consecutivo){
        List<Empleado> listEmpleados = new ArrayList<>();
        
        //Al verificar que la cantidad deseada sea mayor a 0, se crean tantos empleados del mismo tipo como los parametro ingresados.
        if (cantidadEmpleados > 0) {
            System.out.println("Se crean " + cantidadEmpleados + " empleados tipo " + tipo.descripcion());
            for (Integer i = 0; i < cantidadEmpleados; i++) {
                Empleado empleado = new Empleado((consecutivo.longValue()+1), (tipo.descripcion()+ " " + (((consecutivo+1) < 10) ? ("0"+(consecutivo+1)) : ((consecutivo+1)+""))), tipo, 0);
                listEmpleados.add(empleado);
                System.out.println("Empleado Id: " + empleado.getIdentificacion()+ " -> Nombre: " + empleado.getNombre());
                consecutivo++;
            }
        }
        return listEmpleados;
    }
}
