
package enums;

import excepcion.Excepcion;

public enum Eventos {
    LOGIN, URGENTE, CONSULTA;
    
    //Transformamos los String recibidos a su evento correspondiente
    public static Eventos getEvento(String evento) throws Excepcion{
        switch(evento.toUpperCase()) {
            case "LOGIN":
                return Eventos.LOGIN;
            case "URGENTE":
                return Eventos.URGENTE;
            case "CONSULTA":
                return Eventos.CONSULTA;
            default:
                throw new Excepcion(Excepcion.excepcionEnumEvento);
        }
    }
}
