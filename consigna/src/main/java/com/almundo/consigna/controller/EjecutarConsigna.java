/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.consigna.controller;

import com.almundo.consigna.dispatcher.Dispatcher;
import com.almundo.consigna.objetos.Empleado;
import com.almundo.consigna.enums.TipoEmpleadoEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de ejecutar la aplicacion
 * @author Gustavo 
 */
public class EjecutarConsigna {
    
    /**
     * Lista total de empleados del call center
     */
    private static List<Empleado> lstEmpleados;
    
    /**
     * Cantidades de cada uno de los tipos de empleados que se usaran para la prueba
     */
    private static Integer cantOperarios = 0;
    private static Integer cantSupervisores = 0;
    private static Integer cantDirectores = 0;
    
    private static Integer cantidadLlamadas = 0;
    
    /**
     * Metodo que ejecuta la aplicacion
     * @param args
     */
    public static void main(String[] args){
        System.out.println("*************************************************************************************************************");
        System.out.println("************************************* Bienvenido a CONSIGNA Call Center *************************************");
        System.out.println("*************************************************************************************************************");
        //Se solicitan los datos por consola
        cargarDatosConsola(args);
        
        //Se carga la lista de empleados segun lo solicitado
        lstEmpleados = new ArrayList<>();
        lstEmpleados.addAll(crearListaEmpleados(cantOperarios,      TipoEmpleadoEnum.OPERADOR,   0));
        lstEmpleados.addAll(crearListaEmpleados(cantSupervisores,   TipoEmpleadoEnum.SUPERVISOR, cantOperarios));
        lstEmpleados.addAll(crearListaEmpleados(cantDirectores,     TipoEmpleadoEnum.DIRECTOR,   (cantOperarios+cantSupervisores)));
        
        try {
            //Se ejecuta el Dispatcher
            Dispatcher.dispatchCall(lstEmpleados, cantidadLlamadas);
        } catch (InterruptedException ex) {
            Logger.getLogger(EjecutarConsigna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que captura los datos de cuandos empleados se desean para el ejercicio
     * @param args
     */
    private static void cargarDatosConsola(String[] args){
        //Si se reciben parametros de test se ejecuta sin pedir los datos por consola
        if (args != null && args.length > 0) {
            System.out.println("Para iniciar ingrese la cantidad de empleados tipo operadorio que desea que existan.");
            cantOperarios = Integer.parseInt(args[0]);
            System.out.println(cantOperarios);

            System.out.println("Ahora ingrese la cantidad de empleados tipo supervisor que desea que existan.");
            cantSupervisores = Integer.parseInt(args[1]);
            System.out.println(cantSupervisores);

            System.out.println("Ahora ingrese la cantidad de empleados tipo director que desea que existan.");
            cantDirectores = Integer.parseInt(args[2]);
            System.out.println(cantDirectores);

            System.out.println("Cuantas llamadas desea que se generen en total?");
            cantidadLlamadas = Integer.parseInt(args[3]);
            System.out.println(cantidadLlamadas);

            System.out.println("Ud ingreso: ");
            System.out.println("Operarios: " + cantOperarios);
            System.out.println("Supervisores: " + cantSupervisores);
            System.out.println("Directores: " + cantDirectores);
        }else{
            Boolean datosCompletos = Boolean.FALSE;
            while (!datosCompletos){
                System.out.println("Para iniciar ingrese la cantidad de empleados tipo operadorio que desea que existan.");
                Scanner entrada=new Scanner(System.in);
                cantOperarios = entrada.nextInt();

                System.out.println("Ahora ingrese la cantidad de empleados tipo supervisor que desea que existan.");
                cantSupervisores = entrada.nextInt();

                System.out.println("Ahora ingrese la cantidad de empleados tipo director que desea que existan.");
                cantDirectores = entrada.nextInt();

                System.out.println("Cuantas llamadas desea que se generen en total?");
                cantidadLlamadas = entrada.nextInt();

                System.out.println("Ud ingreso: ");
                System.out.println("Operarios: " + cantOperarios);
                System.out.println("Supervisores: " + cantSupervisores);
                System.out.println("Directores: " + cantDirectores);

                Boolean opcionValida = Boolean.FALSE;
                System.out.println("Desea continuar con estos valores (y/n)?");
                String aceptaDatos = entrada.next();

                while (!opcionValida) {
                    if (aceptaDatos.toUpperCase().equals("Y")) {
                        datosCompletos = Boolean.TRUE;
                        opcionValida = Boolean.TRUE;
                    }else if (aceptaDatos.toUpperCase().equals("N")){
                        datosCompletos = Boolean.FALSE;
                        opcionValida = Boolean.TRUE;
                    }
                    if (!opcionValida) {
                        System.out.println("Ingrese una opcion valida. Desea continuar con estos valores (y/n)?");
                        aceptaDatos = entrada.next();
                    }
                }
            }
        }
        
    }
    
    /**
     * Metodo encargado de crear una lista de empleados del tipo deseado
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
