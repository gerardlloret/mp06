package vista;

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
        System.out.println("4. Crear incidencia");
        System.out.println("5. Ranking empleados");
        System.out.println("0. Salir");
    }
    
    //Metodo para mostrar el menu del Login
    private static void showMenuLogin(){
        System.out.println("1. Login");
        System.out.println("0. Salir");
    }
    
    //NO ESTA ACABADA
    public static boolean login(){
        return true;
    }
    
}