package com.almundo.consigna.objetos;

import java.util.Date;

/**
 * Dto que contiene los datos de cada llamada que ingresa al call center
 * @author Gustavo
 */
public class Llamada {
    
    /**
     * Id de la llamada
     */
    private Long id;
    
    /**
     * Fecha en la cual ingresa la llamada
     */
    private Date fecha;
    
    /**
     * Duracion de la llamada
     */
    private Integer duracion;
    
    /**
     * Telefono desde el cual ingresa la llamada
     */
    private String telefono;
    
    /**
     * Flag que define si la llamada fue atendida exitosamente
     */
    private Boolean atendida;

    /**
     * Empleado que atiende la llamada
     */
    private Empleado empleado;
    
    /**
     * Flag q define si la llamada fue reprogramada
     */
    private Boolean reprogramada;

    /**
     * Constructor de la clase
     * @param id identificador de la llamada
     * @param telefono telefono desde el cual ingresa la llamada
     */
    public Llamada(Long id, String telefono) {
        this.id = id;
        this.fecha = new Date();
        this.telefono = telefono;
        
        this.empleado = null;
        this.duracion = 0;
        this.atendida = Boolean.FALSE;
        this.reprogramada = Boolean.FALSE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getAtendida() {
        return atendida;
    }

    public void setAtendida(Boolean atendida) {
        this.atendida = atendida;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Boolean getReprogramada() {
        return reprogramada;
    }

    public void setReprogramada(Boolean reprogramada) {
        this.reprogramada = reprogramada;
    }
    
}
