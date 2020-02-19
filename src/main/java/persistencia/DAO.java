/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.net.MalformedURLException;  
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Empleado;
import org.ektorp.CouchDbConnector;  
import org.ektorp.CouchDbInstance;  
import org.ektorp.http.HttpClient;  
import org.ektorp.http.StdHttpClient;  
import org.ektorp.impl.StdCouchDbConnector;  
import org.ektorp.impl.StdCouchDbInstance;  
import org.ektorp.support.DesignDocument;  

public class DAO {
    
    //Iniciar sesion
    public DAO() {
        //192.168.21.105
        HttpClient httpClient;  
        try {
            httpClient = new StdHttpClient.Builder().url("http://192.168.21.105:5984").username("root").password("root").build();
            CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);  
            //--------------- Creating database----------------------------//  
            //CouchDbConnector db = new StdCouchDbConnector("javatpoint", dbInstance);             
            //db.createDatabaseIfNotExists();  
            CouchDbConnector db = dbInstance.createConnector("my_first_database", true);
            //--------------- Creating Document----------------------------//  
            Empleado e = new Empleado();
            e.setUsername("gerard");
            e.setPassword("pass");
            e.setNombreCompleto("glloret");
            e.setTelefono("1223");
            //DesignDocument dd = new DesignDocument("light");  
            
            db.create(e);
        } catch (MalformedURLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void crearBDD(){
        
    }
    
    
}
