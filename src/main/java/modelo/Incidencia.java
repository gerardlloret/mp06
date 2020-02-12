/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import enums.Type;
import java.util.Date;


public class Incidencia {

    private Date fecha;
    private Type tipo;
    private Empleado origen;
    private Empleado destino;

    public Incidencia(Date fecha, Type tipo, Empleado origen, Empleado destino) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.origen = origen;
        this.destino = destino;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }

    public Empleado getOrigen() {
        return origen;
    }

    public void setOrigen(Empleado origen) {
        this.origen = origen;
    }

    public Empleado getDestino() {
        return destino;
    }

    public void setDestino(Empleado destino) {
        this.destino = destino;
    }
    
    
    
}
