package com.almundo.consigna.enums;

/**
 * ENUM con los tipos de empleado
 * @author Gustavo
 */
public enum TipoEmpleadoEnum {
    
    OPERADOR(  "O", "OPERARIO  "),
    SUPERVISOR("S", "SUPERVISOR"),
    DIRECTOR(  "D", "DIRECTOR  ");
    
    private final String tipo;
    private final String descripcion;
    
    TipoEmpleadoEnum(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String tipo() {
        return tipo;
    }
    
    public String descripcion() {
        return descripcion;
    }
}
