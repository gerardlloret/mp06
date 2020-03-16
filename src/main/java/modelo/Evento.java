
package modelo;

import enums.Eventos;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import excepcion.Excepcion;

//Ektorp no parsea los constructores por lo que tendremos que setear los campos a mano 
//Las propiedades no pueden ser nulas y no estaran en el json los campos vacios.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Evento {
    
    @JsonProperty("_id") private String identity;
    @JsonProperty("_rev") private String version;
    private Empleado empleado;
    private Date fechaEvento;
    private Eventos evento;

    public Eventos getEvento() {
        return evento;
    }

    public void setEvento(String evento) throws Excepcion{
        this.evento = Eventos.getEvento(evento);
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }
    
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

}
