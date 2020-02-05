package excepcion;
//Clase para mostrar nuestras excepciones
public class Excepcion extends Exception{
    private int error = 0;
    //Declaramos estas variables para que al momento de lanzar el error sea mucho mas intuitivo
    public static final int nombreExcede = 1;
    public static final int dniExcede = 2;
    public static final int dniYaExiste = 3;
    public static final int matriculaExcede = 4;
    public static final int claveExcede = 5;
    public static final int loginIncorrecto = 6;
    public static final int noHayExpedientes = 7;
    public static final int apellidoExcede = 8;
    public static final int cpExcede = 9;
    public static final int mascotasExcede = 10;
    public static final int telefonoTipo = 11;
    public static final int telefonoExcede = 12;
    public static final int cpTipo = 13;
    public static final int ultimoAdmin = 14;
    public static final int matriculaExiste = 15;
    
    public Excepcion(int error) {
        super();
        this.error = error;
    }

    @Override
    public String getMessage() {
        switch(error){
            case 1:
                return "< El nombre excede los caracteres permitidos >";
            case 2:
                return "< El dni tiene que tener 8 numeros >";
            case 3:
                return "< Este dni ya se ha dado de alta>";
            case 4:
                return "< La matricula no puede exceder los 6 caracteres >";
            case 5:
                return "< La clave no puede exceder los 8 caracteres >";
            case 6:
                return "< dni o contraseña incorrecta >";
            case 7:
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
                return "< Esta matricula ya existe >";
        }
        return super.getMessage(); 
    }            
}
