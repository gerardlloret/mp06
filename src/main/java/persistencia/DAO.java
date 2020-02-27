/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import enums.Evento;
import excepcion.Excepcion;
import interfaz.DAOInterface;
import java.net.MalformedURLException;  
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Empleado;
import modelo.Incidencia;
import org.ektorp.CouchDbConnector;  
import org.ektorp.CouchDbInstance;  
import org.ektorp.http.HttpClient;  
import org.ektorp.http.StdHttpClient;  
import org.ektorp.impl.StdCouchDbConnector;  
import org.ektorp.impl.StdCouchDbInstance;  
import org.ektorp.support.DesignDocument;  
import org.ektorp.ViewQuery;
import org.ektorp.*;
import org.ektorp.support.*;


public class DAO implements DAOInterface {
    
    CouchDbConnector db;
    
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
            db = dbInstance.createConnector("my_first_database", true);
            //--------------- Creating Document----------------------------//  
            /*Empleado e = new Empleado();
            e.setUsername("gerard");
            e.setPassword("pass");
            e.setNombreCompleto("glloret");
            e.setTelefono("1223");
            e.setId("gerard");            
            db.create(e);*/
        } catch (MalformedURLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }  
    //Metodo que recibe un empleado y lo inserta en la base de datos
    @Override
    public void insertEmpleado(Empleado e) {
        db.create(e);
    }
    //Metodo que comprueba si las credenciales al logearse son correctas
    @Override
    public boolean loginEmpleado(String user, String pass) {
        try{
            Empleado empleado = db.get(Empleado.class, user);
            if(empleado.getUsername().equalsIgnoreCase(user) && empleado.getPassword().equalsIgnoreCase(pass)){
                return true;
            }        
        } catch(org.ektorp.DocumentNotFoundException e){
            return false;
        }
        return false;
    }
    //Metodo que pasado el nombre de un empleado, comprueba si este existe
    public Empleado getEmpleado(String user) throws Excepcion{
        try{
            Empleado empleado = db.get(Empleado.class, user);
            return empleado;
        }catch(org.ektorp.DocumentNotFoundException e){
           throw new Excepcion(Excepcion.empleadoNoExiste);
        }
    }
    //FALTA COMPROBAR QUE PASA SI NO HAY NINGUN EMPLEADO
    public List<Empleado> getAllEmpleados() {
        ViewQuery q = new ViewQuery().allDocs().includeDocs(true);
        List<Empleado> empleados = db.queryView(q, Empleado.class);
        return empleados;
    }   
    //Metodo que pasado un nombre, comprueba si un empleado existe
    public boolean empleadoExiste(String user) {
        try{
            Empleado empleado = db.get(Empleado.class, user);
        } catch(org.ektorp.DocumentNotFoundException e){
            return false;
        }    
        return true;
    }
    //FALTA TESTEAR SI FUNCIONA
    //Metodo que recibe un empleado existente y lo actualiza
    @Override
    public void updateEmpleado(Empleado e) {
        db.update(e);
    }

    @Override
    public void removeEmpleado(Empleado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Incidencia getIncidenciaById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Incidencia> selectAllIncidencias() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertIncidencia(Incidencia i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Incidencia> getIncidenciaByDestino(Empleado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Incidencia> getIncidenciaByOrigen(Empleado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarEvento(Evento e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Evento getUltimoInicioSesion(Empleado e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
