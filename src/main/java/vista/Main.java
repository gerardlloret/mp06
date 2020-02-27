package vista;

import excepcion.Excepcion;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;
import persistencia.DAO;

public class Main {
    
    private static DAO gestor;
    private static Empleado empleadoAcual;
    
    public static void main(String[] args) {
        //try{
            gestor = new DAO();
            System.out.println("Establecida la sesión con couchDB");
            int opcion;
            int opcion2;
            do{
                //Enseñamos el menu y pedimos una opcion
                showMenuLogin();
                opcion = InputAsker.askInt("Escoge una opcion");
                try{
                    switch(opcion){
                        case 1:
                            if(login()){                                                        
                                do{                                  
                                    showMenu();
                                    opcion2 = InputAsker.askInt("Escoge una opcion");
                                    try{
                                        switch(opcion2){
                                            case 1:
                                                crearEmpleado();
                                                break;
                                            case 2:
                                                modificarEmpleado();
                                                break;
                                            case 3:
                                                break;
                                            case 4:
                                                break;
                                            case 5:
                                                break;
                                            case 6:                                        
                                                break;
                                            case 7:
                                                break;
                                            case 8:
                                                break;
                                            case 9:
                                                break;
                                            case 10:
                                                break;
                                            default:
                                                if(opcion2!=0){
                                                    System.out.println("El numero " + opcion2 + " no es una opcion valida.");
                                                }
                                        }
                                    }catch (Excepcion ex) {
                                        System.out.println(ex.getMessage());
                                    }     
                                }while (opcion2 != 0);
                            }
                            break;
                        default:
                            if(opcion!=0){
                                System.out.println("El numero " + opcion + " no es una opcion valida.");
                            }
                    }     
                }catch (Excepcion ex) {
                        System.out.println(ex.getMessage());
                }            
            }while (opcion != 0);
            //https://helun.github.io/Ektorp/reference_documentation.html
            //https://www.programcreek.com/java-api-examples/?class=org.ektorp.CouchDbConnector&method=create
        //} catch () {}
    }
    
    //Metodo para mostrar el menu
    private static void showMenu(){
        System.out.println("1. Crear nuevo empleado");
        System.out.println("2. Modificar empleado");
        System.out.println("3. Borrar empleado");
        System.out.println("4. Ver incidencia por id");
        System.out.println("5. Ver todas las incidencias");
        System.out.println("6. Crear incidencia");
        System.out.println("7. Ver incidencia por usuario");
        System.out.println("8. Insertar evento");
        System.out.println("9. Ver la fecha de inicio de sesion");
        System.out.println("10. Ver ranking de empleados");
        System.out.println("0. Salir");
    }
    
    //Metodo para mostrar el menu del Login
    private static void showMenuLogin(){
        System.out.println("1. Login");
        System.out.println("0. Salir");
    }
    //Metodo para mostrar los atributos de empleado
    private static void showMenuEmpleado(){
        System.out.println("1. Username");
        System.out.println("2. Password");
        System.out.println("3. Nombre completo");
        System.out.println("4. telefono");
    }
    //Metodo para el login, throwea una excepcion si el usuario no existe
    public static boolean login() throws Excepcion{
        String nombre = InputAsker.askString("Introduce tu nombre");
        String password = InputAsker.askString("Introduce tu password");
        if(!gestor.loginEmpleado(nombre, password)){
            throw new Excepcion(Excepcion.loginIncorrecto);
        }
        empleadoAcual = gestor.getEmpleado(nombre);
        return true;
    }
    //Metodo que muestra todos los empleados, pide que se el usuario elija uno y lo devuelve
    public static Empleado obtenerEmpleado() throws Excepcion{
        List<Empleado> empleados = gestor.getAllEmpleados();
        if(empleados.isEmpty()){
            throw new Excepcion(Excepcion.noHayEmpleados);
        }
        System.out.println("Estos son los empleados disponibles:");
        int num = 1;
        for (Empleado e : empleados){
            System.out.println(num + " " + e);
            num++;
        }
        int posicion = InputAsker.askInt("Introduze el num del empleado:",1, empleados.size());
        Empleado seleccionado = empleados.get(posicion-1);
        return seleccionado;
    }    
    //Usamos el codigo ASCII para comprobar si la cadena pasada incluye caracteres diferentes a numeros
    public static boolean noEsUnNumero(String num){
        for(int i=0; i<num.length(); i++){
            if(num.charAt(i)<48 || num.charAt(i)>57){
                return true;
            }
        }
        return false;
    }
    //Metodo que pide y valida los datos introducidos por el usuario y crea un empleado
    public static void crearEmpleado() throws Excepcion{
        String username = InputAsker.askString("Introduce tu nombre");
        if(gestor.empleadoExiste(username)){
            throw new Excepcion(Excepcion.empleadoYaExiste);
        }
        String password = InputAsker.askString("Introduce tu password");
        String nombreCompleto = InputAsker.askString("Introduce tu nombre completo");
        String telefono = InputAsker.askString("Introduce tu telefono");        
        if(noEsUnNumero(telefono)){
            throw new Excepcion(Excepcion.telefonoTipo);
        }
        if(telefono.length()!=9){
            throw new Excepcion(Excepcion.telefonoLength);
        }
        Empleado e = new Empleado();
        e.setUsername(username);
        e.setPassword(password);
        e.setNombreCompleto(nombreCompleto);
        e.setTelefono(telefono);
        e.setId(username);
        gestor.insertEmpleado(e);
        System.out.println("Se ha creado el empleado " + username);
    }
    //NO TERMINADO
    public static void modificarEmpleado() throws Excepcion{
        Empleado empleado = obtenerEmpleado();
        showMenuEmpleado();
        int opcion = InputAsker.askInt("¿Que atributo quieres modificar?");
        do{
            switch(opcion){
                case 1:
                    
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }while (opcion != 0);
    }
    
        
}