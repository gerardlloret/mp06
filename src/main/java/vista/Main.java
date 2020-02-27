package vista;

import excepcion.Excepcion;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import modelo.Empleado;
import modelo.Incidencia;
import persistencia.DAO;

public class Main {
    
    private static DAO gestor;
    
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
                                            crearIncidencia();
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
    
    private static void showMenuEmpleado(){
        System.out.println("1. Username");
        System.out.println("2. Password");
        System.out.println("3. Nombre completo");
        System.out.println("4. telefono");
    }
    
    
    //NO ESTA ACABADA FALTA EL THROW
    public static boolean login() throws Excepcion{
        String nombre = InputAsker.askString("Introduce tu nombre");
        String password = InputAsker.askString("Introduce tu password");
        if(gestor.loginEmpleado(nombre, password)){
            return true;
        }
        return false;
    }
    
    public static Empleado obtenerEmpleado() throws Excepcion{
        List<Empleado> empleados = gestor.getAllEmpleados();
        if(empleados.isEmpty()){
            throw new Excepcion(Excepcion.noHayEmpleados);
        }
        System.out.println("Estos son las empleados disponibles:");
        int num = 1;
        for (Empleado e : empleados){
            System.out.println(num + " " + e);
            num++;
        }
        int posicion = InputAsker.askInt("Introduze el num del empleado:",1, empleados.size());
        Empleado seleccionado = empleados.get(posicion-1);
        return seleccionado;
    }
    
    public static void crearEmpleado() throws Excepcion{
        String username = InputAsker.askString("Introduce tu nombre");
        String password = InputAsker.askString("Introduce tu nombre");
        
    
    }
    
    
    public static void modificarEmpleado() throws Excepcion{
        Empleado empleado = obtenerEmpleado();
        
        
    }
    
    public static void crearIncidencia(){

        Date fecha = new Date();
        String tipo = InputAsker.askString("Tipo: ");
        
        
        Incidencia incidencia = new Incidencia();
        incidencia.setFecha(fecha);

        
      /*@JsonProperty("_tipo") private Type tipo;
        @JsonProperty("_origen") private Empleado origen;
        @JsonProperty("_destino") private Empleado destino;*/
    }
        
}