package com.almundo.consigna.dispatcher;

import com.almundo.consigna.enums.TipoEmpleadoEnum;
import com.almundo.consigna.objetos.Empleado;
import com.almundo.consigna.objetos.Llamada;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Clase encargada del manejo de las llamadas
 * @author Gustavo
 */
public class Dispatcher {
    
    /**
     * Lista de empleados 
     */
    private static HashMap<Long, Empleado> empleados;
    
    /**
     * Lista de empleados disponibles
     */
    private static HashMap<Long, String> empleadosDisponibles;
    
    /**
     * Lista de llamadas 
     */
    private static HashMap<Long, Llamada> llamadas;
    
    /**
     * Lista de llamadas sin atender
     */
    private static HashMap<Long, String> llamadasSinAtender;
    
    /**
     * Consecutivo de las llamadas
     */
    private static Long consecutivoLlamada = new Long(1);
    
    /**
     * Atributo que dice el maximo numero de llamadas que se atienden al tiempo 
     */
    private static final Integer maxLlamadasEnLinea = 10;
    
    /**
     * Atributo que dice la cantidadd de llamadas que se encuentran en linea
     */
    private static Integer canLlamadasEnLinea = 0;
    
    /**
     * Contador de las llamadas atendidas
     */
    private static Integer cantidadLlamadasAtendidas = 0;
    
    /**
     * Tiempo de ejecucion
     */
    private static Long tiempo;
    
    /**
     * Metodo que se ejecuta al ingreso de una llamada
     * @param lst
     * @param cantidadLlamadas
     * @throws java.lang.InterruptedException
     */
    public static void dispatchCall(List<Empleado> lst, Integer cantidadLlamadas) throws InterruptedException{
        tiempo = System.currentTimeMillis();
        System.out.println("TIEMPO INICIAL " + (System.currentTimeMillis() - tiempo) / 1000);
        
        cargarEmpleadosHashMap(lst);
        cargarLlamadas(cantidadLlamadas);
        
        //Mientras la cantidad de llamadas atendidas no sea igual a las llamadas que ingresaron se seguira ejecutando el proceso
        while (cantidadLlamadasAtendidas < cantidadLlamadas) {
            //Se recorren las llamadas que existen para verificar si pueden ser atendidas o deben ser reprogramadas
            for (Long key : llamadasSinAtender.keySet()) {
                Llamada llamada = llamadas.get(key);
                //Si la cantidad de llamadas en linea es mayor a la permitida se reprograman 
                if (canLlamadasEnLinea < maxLlamadasEnLinea) {
                    //Se carga el empleado q atendera la llamada
                    Empleado empleado = cargarEmpleadoDisponible();
                    //Si no se encuentra un empleado disponible se reprograma 
                    if (empleado != null ) {
                        empleado.procesarLlamada(llamada, tiempo);
                        empleado.start();
                        llamada.setAtendida(Boolean.TRUE);
                        llamada.setReprogramada(Boolean.FALSE);
                        llamadasSinAtender.remove(key);
                        cantidadLlamadasAtendidas++;
                        break;
                    }else if (!llamada.getReprogramada()) {
                        llamada.setReprogramada(Boolean.TRUE);
                        imprimirReprogramacion(llamada, tiempo);
                    }
                }else if (!llamada.getReprogramada()) {
                    llamada.setReprogramada(Boolean.TRUE);
                    imprimirReprogramacion(llamada, tiempo);
                }
                Thread.sleep(250);
            }
        }
        Thread.sleep(10000);
        imprimirDatosEmpleados();
        System.out.println("TIEMPO FINAL " + (System.currentTimeMillis() - tiempo) / 1000);
    }
    
    /**
     * Metodo que imprime en consola los datos de la reprogramacion de una llamada
     * @param llamada
     * @param tiempo 
     */
    private static void imprimirReprogramacion(Llamada llamada, Long tiempo){
        Long tiempoRep = (System.currentTimeMillis() - tiempo) / 1000;
        System.out.println( "REPROGRAMA ATENCION ||               ||" +
                            " LLAMADA: " + llamada.getId() + (llamada.getId() < 10 ? "  NUM: ": " NUM: ") + llamada.getTelefono() + 
                            " || TOTAL LLAMADAS EN LINEA : " + (canLlamadasEnLinea < 10 ? " "+canLlamadasEnLinea: canLlamadasEnLinea.toString()) + 
                            " || TIEMPO: " + (tiempoRep < 10 ? tiempoRep + "seg  || ": tiempoRep+"seg || "));
    }
    
    /**
     * Metodo que imprime en consola los datos de cuantas llamadas atendio cada empleado
     * @param llamada
     * @param tiempo 
     */
    private static void imprimirDatosEmpleados(){
        Integer cantOperario = 0, cantSupervisor = 0, cantDirector = 0;
        for (Long key : empleadosDisponibles.keySet()) {
            if(empleados.get(key).getTipo().tipo().equals(TipoEmpleadoEnum.OPERADOR.tipo())){
                cantOperario=cantOperario+empleados.get(key).getCantidadLlamadasAtendidas();
            }else if(empleados.get(key).getTipo().tipo().equals(TipoEmpleadoEnum.SUPERVISOR.tipo())){
                cantSupervisor=cantSupervisor+empleados.get(key).getCantidadLlamadasAtendidas();
            }else if(empleados.get(key).getTipo().tipo().equals(TipoEmpleadoEnum.DIRECTOR.tipo())){
                cantDirector=cantDirector+empleados.get(key).getCantidadLlamadasAtendidas();
            }
            System.out.println( "EMPLEADO " + empleados.get(key).getNombre() + " || TOTAL LLAMADAS ATENDIDAS : " + empleados.get(key).getCantidadLlamadasAtendidas());
        }
        System.out.println( "TOTAL EMPLEADOS " + TipoEmpleadoEnum.OPERADOR.descripcion()+ ": " + cantOperario);
        System.out.println( "TOTAL EMPLEADOS " + TipoEmpleadoEnum.SUPERVISOR.descripcion()+ ": " + cantSupervisor);
        System.out.println( "TOTAL EMPLEADOS " + TipoEmpleadoEnum.DIRECTOR.descripcion()+ ": " + cantDirector);
        System.out.println( "TOTAL LLAMADAS ATENDIDAS: " + (cantOperario+cantSupervisor+cantDirector));
    }
    
    /**
     * Metodo encargado de cargar los empleados en los HashMap
     * @param lst 
     */
    private static void cargarEmpleadosHashMap(List<Empleado> lst){
        empleadosDisponibles = new HashMap<>();
        empleados = new HashMap<>();
        for (Empleado itera : lst) {
            empleados.put(itera.getIdentificacion(), itera);
            empleadosDisponibles.put(itera.getIdentificacion(), itera.getTipo().tipo());
        }
    }
    
    /**
     * Metodo que agrega una llamda en linea
     * @return 
     */
    public static String iniciarLlamada(){
        canLlamadasEnLinea++;
        return (canLlamadasEnLinea < 10 ? " "+canLlamadasEnLinea: canLlamadasEnLinea.toString());
    }
    
    /**
     * Metodo que quita una llamda en linea
     * @return 
     */
    public static String colgarLlamada(){
        canLlamadasEnLinea--;
        return (canLlamadasEnLinea < 10 ? " "+canLlamadasEnLinea: canLlamadasEnLinea.toString());
    }
    
    /**
     * Metodo encargado de cargar el empleado disponible teniendo en cuenta  el tipo
     * @return 
     */
    private static Empleado cargarEmpleadoDisponible(){
        Empleado empleado = null;
        Boolean empleadoEncontrado = Boolean.FALSE;
        //Se evaluan los empleados disponibles
        Integer ejeTipo = 0;
        while (!empleadoEncontrado && ejeTipo < 3) {
            String tipoEmpleado = (ejeTipo == 0 ? TipoEmpleadoEnum.OPERADOR.tipo() : (ejeTipo == 1 ? TipoEmpleadoEnum.SUPERVISOR.tipo() : (ejeTipo == 2 ? TipoEmpleadoEnum.DIRECTOR.tipo() : "")));
            for (Long key : empleadosDisponibles.keySet()) {
                //Se verifica si el empleado disponnible tiene una cantidad inferior al mayor numero de atenciones 
                if (empleadosDisponibles.get(key).equals(tipoEmpleado)) {
                    //Si se encuentra un empleado con estas caracteristicas se crea un objeto Empleado nuevo para ejecutar el HILO
                    Empleado empleadoTemp = empleados.get(key);
                    empleado = new Empleado(empleadoTemp.getIdentificacion(), empleadoTemp.getNombre(), empleadoTemp.getTipo(), empleadoTemp.getCantidadLlamadasAtendidas());
                    empleadosDisponibles.remove(empleado.getIdentificacion());
                    empleadoEncontrado = Boolean.TRUE;
                    break;
                }
            }
            ejeTipo++;
        }
        return empleado;
    }
    
    /**
     * Metodo q agrega un empleado a la lista de disponibilidad
     * @param id id del empleado que se desea agregar a la lista de disponibilidad
     * @param tipo tipo de empleado (O= Operario, S=Supervisor, D=Director)
     * @param cantidad cantidad de llamadas constestadas por el empleado
     * @return  Proceso Exitoso
     */
    public static Boolean agregarEmpleadoDisponible(Long id, String tipo, Integer cantidad){
        if (id != null && id > 0 && tipo != null && !tipo.trim().isEmpty() && cantidad != null) {
            if (tipo.contains(TipoEmpleadoEnum.OPERADOR.tipo()) || tipo.contains(TipoEmpleadoEnum.SUPERVISOR.tipo()) || tipo.contains(TipoEmpleadoEnum.DIRECTOR.tipo())) {
                empleadosDisponibles.put(id, tipo);
                Empleado temp = empleados.get(id);
                if (temp != null) {
                    temp.setCantidadLlamadasAtendidas(cantidad);
                    empleados.replace(id, temp);
                    return Boolean.TRUE;
                }else{
                    return Boolean.FALSE;
                }
            }else{
                return Boolean.FALSE;
            }
        }else{
            return Boolean.FALSE;
        }
    }
    
    /**
     * Metodo que simula el ingreso de llamadas al call center
     * @param cantidadLlamadas 
     */
    private static void cargarLlamadas(Integer cantidadLlamadas){
        llamadasSinAtender = new HashMap<>();
        llamadas = new HashMap<>();
        for (int i = 0; i < cantidadLlamadas; i++) {
            Llamada llamada = crearLlamada();
            llamadas.put(llamada.getId(), llamada);
            llamadasSinAtender.put(llamada.getId(), llamada.getTelefono());
        }
    }
    
    /**
     * Metodo encargado de crear una llamada
     * @return 
     */
    private static Llamada crearLlamada(){
        Random r = new Random();
        //Se crea la llamada con un numero de telefono aleatorio
        Llamada llamada = new Llamada(consecutivoLlamada, "+57 (" + (r.nextInt(350-300) + 300) + ") "+ (r.nextInt(750-211) + 211) + " " +  (r.nextInt(99-10) + 10) + " " +(r.nextInt(99-10) + 10)  );
        consecutivoLlamada++;
        return llamada;
    }
}
