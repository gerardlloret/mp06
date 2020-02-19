
package vista;

import persistencia.DAO;

public class Main {
    
    private static DAO gestor;
    
    public static void main(String[] args) {
        gestor = new DAO();
        System.out.println("Establecida la sesión con couchDB");
        //https://helun.github.io/Ektorp/reference_documentation.html
        //https://www.programcreek.com/java-api-examples/?class=org.ektorp.CouchDbConnector&method=create
    }
}
