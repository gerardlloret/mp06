package modelo;

import enums.Tipos;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import excepcion.Excepcion;

//Ektorp no parsea los constructores por lo que tendremos que setear los campos a mano 
//Las propiedades no pueden ser nulas y no estaran en el json los campos vacios.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Incidencia {

    @JsonProperty("_id") private String id;
    @JsonProperty("_rev") private String rev;
    private String nombre;
    private Date fecha;
    private Tipos tipo;
    private Empleado origen;
    private Empleado destino;
    private String descripcion;



    public Incidencia() {       
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tipos getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) throws Excepcion{
        this.tipo = Tipos.getType(tipo);
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

    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Sobre escribimos el toString para que nos muestre los datos que nos interesen en vez de la posicion en memoria del objeto
    @Override
    public String toString() {
        return "Id = " + id + ", Nombre = " + nombre + ", Fecha = " + fecha + ", Tipo = " + tipo + ", Origen = " + origen.getUsername() + ", Destino = " + destino.getUsername() + ", Descripcion = " + descripcion;
    }
      
    
}
