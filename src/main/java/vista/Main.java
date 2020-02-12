
package vista;

import persistencia.DAO;

public class Main {
    
    private static DAO gestor;
    
    public static void main(String[] args) {
        gestor = new DAO();
            System.out.println("Establecida la sesión con couchDB");
        
    }
}
