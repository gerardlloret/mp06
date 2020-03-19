package enums;

import excepcion.Excepcion;

public enum Tipos {
    URGENTE, NORMAL;
    
    //Transformamos los String recibidos a su tipo correspondiente
    public static Tipos getType(String tipo) throws Excepcion{
        switch(tipo.toUpperCase()) {
            case "URGENTE":
                return Tipos.URGENTE;
            case "NORMAL":
                return Tipos.NORMAL;
            default:
                throw new Excepcion(Excepcion.excepcionEnumIncidencia);
        }
    }
    
}
