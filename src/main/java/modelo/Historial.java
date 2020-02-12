
package modelo;

import enums.Event;
import java.util.Date;


public class Historial {
    
    private Empleado empleado;
    private Date fechaEvento;
    private Event evento;

    /**
     * Get the value of evento
     *
     * @return the value of evento
     */
    public Event getEvento() {
        return evento;
    }

    /**
     * Set the value of evento
     *
     * @param evento new value of evento
     */
    public void setEvento(Event evento) {
        this.evento = evento;
    }

    /**
     * Get the value of fechaEvento
     *
     * @return the value of fechaEvento
     */
    public Date getFechaEvento() {
        return fechaEvento;
    }

    /**
     * Set the value of fechaEvento
     *
     * @param fechaEvento new value of fechaEvento
     */
    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }
    
    /**
     * Get the value of empleado
     *
     * @return the value of empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * Set the value of empleado
     *
     * @param empleado new value of empleado
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

}
