package vista;

import excepcion.Excepcion;
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
                                opcion2 = InputAsker.askInt("Escoge una opcion");
                                showMenu();
                                do{
                                    switch(opcion2){
                                        case 1:

                                            break;
                                        case 2:
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
    
    //NO ESTA ACABADA
    public static boolean login() throws Excepcion{
        String nombre = InputAsker.askString("Introduce tu nombre");
        String password = InputAsker.askString("Introduce tu password");
        if(gestor.loginEmpleado(nombre, password)){
            return true;
        }
        return false;
    }
        
}