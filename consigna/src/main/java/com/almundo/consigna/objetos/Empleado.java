package com.almundo.consigna.objetos;

import com.almundo.consigna.dispatcher.Dispatcher;
import com.almundo.consigna.enums.TipoEmpleadoEnum;
import java.util.Random;

/**
 * Dto que contiene los datos de cada tipo de empleado
 * @author Gustavo
 */
public class Empleado extends Thread {
    
    /**
     * Id del empleado
     */
    private Long identificacion;
    
    /**
     * Nombre del empleado
     */
    private String nombre;
    
    /**
     * Tipo de empleado [O=Operador, S=Supervisor, D=Director]
     */
    private TipoEmpleadoEnum tipo;
    
    /**
     * Llamada que se encuentra atendiendo el empleado
     */
    private Llamada llamadaEnProceso;
    
    /**
     * Tiempo inicial de la llamada
     */
    private Long tiempo;
    
    /**
     * Contador de las llamadas q atiende el empleado
     */
    private Integer cantidadLlamadasAtendidas;
    
    /**
     * Metodo que ejecuta la llamada
     */
    @Override
    public void run() {
        if (llamadaEnProceso != null && tiempo != null) {
            Long tiempoIni = (System.currentTimeMillis() - this.tiempo) / 1000;
            System.out.println("INICIA     ATENCION || " + this.nombre + 
                                " || LLAMADA: " + this.llamadaEnProceso.getId() + (this.llamadaEnProceso.getId() < 10 ? "  NUM: ": " NUM: ") + this.llamadaEnProceso.getTelefono() + 
                                " || TOTAL LLAMADAS EN LINEA : " + Dispatcher.iniciarLlamada() + 
                                " || TIEMPO: " + (tiempoIni < 10 ? tiempoIni + "seg  || ": tiempoIni+"seg || "));

            this.llamadaEnProceso.setAtendida(Boolean.TRUE);
            this.llamadaEnProceso.setDuracion(ejecutarTiempoLlamada());
            this.cantidadLlamadasAtendidas++;

            Long tiempoFin = (System.currentTimeMillis() - this.tiempo) / 1000;
            System.out.println("FINALIZA   ATENCION || " + this.nombre + 
                                " || LLAMADA: " + this.llamadaEnProceso.getId() + (this.llamadaEnProceso.getId() < 10 ? "  NUM: ": " NUM: ") + this.llamadaEnProceso.getTelefono() + 
                                " || TOTAL LLAMADAS EN LINEA : " + Dispatcher.colgarLlamada()+ 
                                " || TIEMPO: " + (tiempoFin < 10 ? tiempoFin + "seg  || ": tiempoFin+"seg || ") + 
                                " DURACION: " + this.llamadaEnProceso.getDuracion() + " seg. " );
            this.llamadaEnProceso = null;
            this.tiempo = null;
            Dispatcher.agregarEmpleadoDisponible(identificacion, tipo.tipo(), this.cantidadLlamadasAtendidas);
        }
    }
    
    /**
     * Metodo que calcula y ejecuta el tiempo de la duracion de la llamada
     * @return 
     */
    private Integer ejecutarTiempoLlamada() {
        Integer duracion = 0;
        try {
            Random r = new Random();
            duracion = r.nextInt(10-5) + 5;
            Thread.sleep(duracion * 1000);
        } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
        }
        return duracion;
    }
    
    /**
     * Constructor de la clase
     * @param id
     * @param nombre
     * @param tipo 
     * @param cantidad 
     */
    public Empleado(Long id, String nombre, TipoEmpleadoEnum tipo, Integer cantidad) {
        this.identificacion = id;
        this.nombre = nombre;
        this.tipo = tipo;
        
        this.llamadaEnProceso = null;
        this.tiempo = null;
        this.cantidadLlamadasAtendidas = cantidad;
    }
    
    /**
     * Metodo que carga los datos de la llamada a ejecutar
     * @param llamada
     * @param tiempo 
     */
    public void procesarLlamada(Llamada llamada, Long tiempo) {
        this.llamadaEnProceso = llamada;
        this.tiempo = tiempo;
        
    }
    
    public Long getIdentificacion() {
        return identificacion;
    }

    public void setId(Long identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Llamada getLlamadaEnProceso() {
        return llamadaEnProceso;
    }

    public void setLlamadaEnProceso(Llamada llamadaEnProceso) {
        this.llamadaEnProceso = llamadaEnProceso;
    }

    public Long getTiempo() {
        return tiempo;
    }

    public void setTiempo(Long tiempo) {
        this.tiempo = tiempo;
    }

    public TipoEmpleadoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpleadoEnum tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidadLlamadasAtendidas() {
        return cantidadLlamadasAtendidas;
    }

    public void setCantidadLlamadasAtendidas(Integer cantidadLlamadasAtendidas) {
        this.cantidadLlamadasAtendidas = cantidadLlamadasAtendidas;
    }
    
}
