
package persistencia;

import enums.Eventos;
import enums.Tipos;
import excepcion.Excepcion;
import interfaz.DAOInterface;
import java.net.MalformedURLException;  
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Empleado;
import modelo.Evento;
import modelo.Incidencia;
import modelo.RankingTO;
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
    
    CouchDbConnector dbEmpleado;
    CouchDbConnector dbIncidencia;
    CouchDbConnector dbEvento;
    
    //Iniciar sesion
    public DAO() {
        //192.168.21.105
        HttpClient httpClient;
        try {
            httpClient = new StdHttpClient.Builder().url("http://localhost:5984").username("root").password("root").build();
            CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);  
            //--------------- Creating database----------------------------//  
            //CouchDbConnector db = new StdCouchDbConnector("javatpoint", dbInstance);             
            //db.createDatabaseIfNotExists();  
            dbEmpleado = dbInstance.createConnector("Empleados", true);
            dbIncidencia = dbInstance.createConnector("Incidencias", true);
            dbEvento = dbInstance.createConnector("Eventos", true);
            //--------------- Creating Document----------------------------//  
            /*Empleado e = new Empleado();
            e.setUsername("gerard");
            e.setPassword("pass");
            e.setNombreCompleto("glloret");
            e.setTelefono("1223456789");
            e.setId("gerard");        
            dbEmpleado.create(e);*/  
        } catch (MalformedURLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }  
    
    //Metodo que recibe un empleado y lo inserta en la base de datos
    @Override
    public void insertEmpleado(Empleado e) {
        dbEmpleado.create(e);
    }
    
    //Metodo que comprueba si las credenciales al logearse son correctas
    @Override
    public boolean loginEmpleado(String user, String pass) {
        try{
            Empleado empleado = dbEmpleado.get(Empleado.class, user);
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
            Empleado empleado = dbEmpleado.get(Empleado.class, user);
            return empleado;
        }catch(org.ektorp.DocumentNotFoundException e){
           throw new Excepcion(Excepcion.empleadoNoExiste);
        }
    }
    
    //Metodo que devuelve todos los empleados
    public List<Empleado> getAllEmpleados() {
        ViewQuery q = new ViewQuery().allDocs().includeDocs(true);
        List<Empleado> empleados = dbEmpleado.queryView(q, Empleado.class);
        return empleados;
    }   
    
    //Metodo que pasado un nombre, comprueba si un empleado existe
    public boolean empleadoExiste(String user) {
        try{
            Empleado empleado = dbEmpleado.get(Empleado.class, user);
        } catch(org.ektorp.DocumentNotFoundException e){
            return false;
        }    
        return true;
    }
    
    //Metodo que recibe un empleado existente y lo actualiza
    @Override
    public void updateEmpleado(Empleado e) {
        dbEmpleado.update(e);
    }
    

    //Metodo que recibe un empleado existente y lo elimina
    @Override
    public void removeEmpleado(Empleado e) {
        dbEmpleado.delete(e);
    }

    //Metodo que retorna una incidencia a partir de un id
    @Override
    public Incidencia getIncidenciaById(int id) {
        String idString = Integer.toString(id);
        Incidencia incidencia = dbIncidencia.get(Incidencia.class, idString);
        return incidencia;
    }
    
    //Metodo que devuelve todas las incidencias
    @Override
    public List<Incidencia> selectAllIncidencias() {
        ViewQuery q = new ViewQuery().allDocs().includeDocs(true);
        List<Incidencia> incidencias = dbIncidencia.queryView(q, Incidencia.class);
        return incidencias;
    }
    
    //Metodo para mostrar todas las incidencias.
    public List<Incidencia> getAllIncidencia() {
        ViewQuery q = new ViewQuery().allDocs().includeDocs(true);
        List<Incidencia> incidencias = dbIncidencia.queryView(q, Incidencia.class);
        return incidencias;
    }   

    //Metodo que recibe una incidencia y la inserta en la base de datos
    @Override
    public void insertIncidencia(Incidencia i) {
        dbIncidencia.create(i);
    }
    
    //Metodo que pasado un id, comprueba si una incidencia existe
    public boolean incidenciaExiste(String id) {
        try{
            Incidencia incidencia = dbIncidencia.get(Incidencia.class, id);
        } catch(org.ektorp.DocumentNotFoundException e){
            return false;
        }    
        return true;
    }

    //Metodo que pasado un empleado devuelve todas las incidencias con ese empleado como destinatario
    @Override
    public List<Incidencia> getIncidenciaByDestino(Empleado e) {
        List<Incidencia> incidenciasByDestino = new ArrayList<>();
        for(Incidencia i : selectAllIncidencias()){
            if(i.getDestino().getUsername().equals(e.getUsername())){
                incidenciasByDestino.add(i);
            }
        }
        return incidenciasByDestino;
    }

    //Metodo que pasado un empleado devuelve todas las incidencias con ese empleado como origen
    @Override
    public List<Incidencia> getIncidenciaByOrigen(Empleado e) {
        List<Incidencia> incidenciasByOrigen = new ArrayList<>();
        for(Incidencia i : selectAllIncidencias()){
            if(i.getOrigen().getUsername().equals(e.getUsername())){
                incidenciasByOrigen.add(i);
            }
        }
        return incidenciasByOrigen;
    }

    //Metodo que pasado un evento lo inserta en la base de datos
    @Override
    public void insertarEvento(Evento e) {
        dbEvento.create(e);
    }

    //Metodo que pasado un empleado devuelve el ultimo evento de inicio de sesion
    @Override
    public Evento getUltimoInicioSesion(Empleado e) {
        ViewQuery q = new ViewQuery().allDocs().includeDocs(true);
        List<Evento> eventos = dbEvento.queryView(q, Evento.class);
        Evento seleccionado = new Evento();
        for(Evento ev : eventos){
            if((ev.getEmpleado().getUsername().equalsIgnoreCase(e.getUsername())) && (ev.getEvento().equals(Eventos.LOGIN)) ){
                if(seleccionado.getFechaEvento()==null){
                    seleccionado = ev;
                }
                if(ev.getFechaEvento().after(seleccionado.getFechaEvento())){
                    seleccionado = ev;
                }
            }
        }
        return seleccionado;
    }
    
    //Metodo que devuelve una lista con el nombre de los empleados y el numero de incidencias urgentes que han creado
    //Ordenados de forma ascendente
    @Override
    public List<RankingTO> getRankingEmpleados(){
        boolean esta = false;
        List<RankingTO> rankingTO = new ArrayList<>();
        for(Incidencia i : selectAllIncidencias()){
            if(i.getTipo().toString().equals("URGENTE")){
                System.out.println("NOMBRE = = = = " + i.getOrigen().getUsername());
                RankingTO usuario = new RankingTO();
                usuario.setNombre(i.getOrigen().getUsername());
                for(RankingTO r : rankingTO){
                    if(r.getNombre().equalsIgnoreCase(usuario.getNombre())){
                        r.setNumUrgente(1);
                        esta = true;
                    }
                }
                if(!esta){
                    usuario.setNumUrgente(1);
                    rankingTO.add(usuario);
                }
                esta = false;
            }
        }
        Collections.sort(rankingTO);
        return rankingTO;
    }
    
}
