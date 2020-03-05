/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import enums.Type;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Incidencia {

    @JsonProperty("_id") private String identity;
    @JsonProperty("_rev") private String version;
    private String nombre;
    private Date fecha;
    private Type tipo;
    private Empleado origen;
    private Empleado destino;
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /*public Incidencia(Date fecha, Type tipo, Empleado origen, Empleado destino) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.origen = origen;
        this.destino = destino;
    }*/

    public Incidencia() {       
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

    @Override
    public String toString() {
        return "Incidencia{" + "identity=" + identity + ", version=" + version + ", fecha=" + fecha + ", tipo=" + tipo + ", origen=" + origen + ", destino=" + destino + '}';
    }
    
    
    
}
