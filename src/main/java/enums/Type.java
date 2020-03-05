package enums;

import excepcion.Excepcion;

public enum Type {
    URGENTE, NORMAL;
    
       public static Type getType(String tipo) throws Excepcion{
        switch(tipo.toUpperCase()) {
            case "URGENTE":
                return Type.URGENTE;
            case "NORMAL":
                return Type.NORMAL;

            default:
                //COMPROBAR
                throw new Excepcion(Excepcion.tipoIncorrecto);
        }
    }

}
