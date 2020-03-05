package excepcion;
//Clase para mostrar nuestras excepciones
public class Excepcion extends Exception{
    private int error = 0;
    //Declaramos estas variables para que al momento de lanzar el error sea mucho mas intuitivo
    public static final int loginIncorrecto = 1;
    public static final int noHayEmpleados = 2;
    public static final int empleadoYaExiste = 3;
    public static final int telefonoTipo = 4;
    public static final int telefonoLength = 5;
    public static final int empleadoNoExiste = 6;
    public static final int tipoIncorrecto = 31;
    /*public static final int noHayExpedientes = 7;
    public static final int apellidoExcede = 8;
    public static final int cpExcede = 9;
    public static final int mascotasExcede = 10;
    public static final int telefonoTipo = 11;
    public static final int telefonoExcede = 12;
    public static final int cpTipo = 13;
    public static final int ultimoAdmin = 14;
    public static final int matriculaExiste = 15;*/
    
    public Excepcion(int error) {
        super();
        this.error = error;
    }

    @Override
    public String getMessage() {
        switch(error){
            case 1:
                return "< El nombre o el password son incorrectos >";
            case 2:
                return "< No existe ningun empleado >";
            case 3:
                return "< Este empleado ya existe>";
            case 4:
                return "< Un telefono solo debe contener numeros >";
            case 5:
                return "< Un telefono tiene que tener 9 numeros >";
            case 6:
                return "< Este empleado no existe >";
            case 31:
                return "< Tipo incorrecto >";
            /*case 7:
                return "< No se ha creado ningun expediente >";
            case 8:
                return "< El apellido excede los caracteres permitidos >";
            case 9:
                return "< El codigo postal no puede contener mas de 6 numeros >";
            case 10:
                return "< El numero de mascotas tine que ser de 1 a 99 >";
            case 11:
                return "< Un telefono solo puede contener numeros >";
            case 12:
                return "< El telefono no puede tener menos de 9 o mas de 12 numeros >";
            case 13:
                return "< El codigo postal solo puede tener numeros >";
            case 14:
                return "< No puedes borrar el ultimo administrador >";
            case 15:
                return "< Esta matricula ya existe >";*/
        }
        return super.getMessage(); 
    }            
}
