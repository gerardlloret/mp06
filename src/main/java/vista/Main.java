package vista;

import enums.Tipos;
import static enums.Tipos.getType;
import excepcion.Excepcion;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Empleado;
import modelo.Evento;
import modelo.Incidencia;
import modelo.RankingTO;
import persistencia.DAO;

public class Main {

    private static DAO gestor;
    private static Empleado empleadoActual;

    public static void main(String[] args) {
        //try{
        gestor = new DAO();
        System.out.println("Establecida la sesión con couchDB");
        int opcion;
        int opcion2;
        do {
            //Enseñamos el menu y pedimos una opcion
            showMenuLogin();
            opcion = InputAsker.askInt("Escoge una opcion");
            try {
                switch (opcion) {
                    case 1:
                        if (login()) {
                            do {
                                showMenu();
                                opcion2 = InputAsker.askInt("Escoge una opcion");
                                try {
                                    switch (opcion2) {
                                        case 1:
                                            crearEmpleado();
                                            break;
                                        case 2:
                                            modificarEmpleado();
                                            break;
                                        case 3:
                                            if(eliminarEmpleado()){
                                                opcion2 = 0;
                                            }
                                            break;
                                        case 4:
                                            mostrarIncidenciaPorId();
                                            break;
                                        case 5:
                                            mostrarTodasLasIncidencias();
                                            break;
                                        case 6:
                                            crearIncidencia();
                                            break;
                                        case 7:
                                            mostrarIncidenciasPorUsuario();
                                            break;
                                        case 8:
                                            verUltimoInicio();
                                            break;
                                        case 9:
                                            verRanking();
                                            break;
                                        default:
                                            if (opcion2 != 0) {
                                                System.out.println("El numero " + opcion2 + " no es una opcion valida.");
                                            }
                                    }
                                } catch (Excepcion ex) {
                                    System.out.println(ex.getMessage());
                                }
                            } while (opcion2 != 0);
                        }
                        break;
                    default:
                        if (opcion != 0) {
                            System.out.println("El numero " + opcion + " no es una opcion valida.");
                        }
                }
            } catch (Excepcion ex) {
                System.out.println(ex.getMessage());
            }
        } while (opcion != 0);
        //https://helun.github.io/Ektorp/reference_documentation.html
        //https://www.programcreek.com/java-api-examples/?class=org.ektorp.CouchDbConnector&method=create
        //} catch () {}
    }

    //Metodo para mostrar el menu
    private static void showMenu() {
        System.out.println("1. Crear nuevo empleado");
        System.out.println("2. Modificar empleado");
        System.out.println("3. Borrar empleado");
        System.out.println("4. Ver incidencia por id");
        System.out.println("5. Ver todas las incidencias");
        System.out.println("6. Crear incidencia");
        System.out.println("7. Ver incidencia por usuario");
        System.out.println("8. Ver la fecha de inicio de sesion");
        System.out.println("9. Ver ranking de empleados");
        System.out.println("0. Salir");
    }

    //Metodo para mostrar el menu del Login
    private static void showMenuLogin() {
        System.out.println("1. Login");
        System.out.println("0. Salir");
    }

    //Metodo para mostrar los atributos de empleado
    private static void showMenuEmpleado() {
        System.out.println("1. Password");
        System.out.println("2. Nombre completo");
        System.out.println("3. telefono");
        System.out.println("0. Salir");
    }
    
    private static void showMenuOptenerIncidenciaByEmpleado(){
        System.out.println("Tipo de incidencia:");
        System.out.println("1. Mostrar incidencias enviadas");
        System.out.println("2. Mostrar incidencias recividas");
        System.out.println("0. Salir");
    }

    //Metodo para el login, throwea una excepcion si el usuario no existe
    public static boolean login() throws Excepcion {
        String nombre = InputAsker.askString("Introduce tu nombre");
        String password = InputAsker.askString("Introduce tu password");
        if (!gestor.loginEmpleado(nombre, password)) {
            throw new Excepcion(Excepcion.loginIncorrecto);
        }
        empleadoActual = gestor.getEmpleado(nombre);
        Date date = new Date();
        //empleadoActual.setDate(date);
        insertarEvento("LOGIN");
        gestor.updateEmpleado(empleadoActual);
        return true;
    }

    //Metodo que muestra todos los empleados, pide que se el usuario elija uno y lo devuelve
    public static Empleado obtenerEmpleado() throws Excepcion {
        List<Empleado> empleados = gestor.getAllEmpleados();
        if (empleados.isEmpty()) {
            throw new Excepcion(Excepcion.noHayEmpleados);
        }
        System.out.println("Estos son los empleados disponibles:");
        int num = 1;
        for (Empleado e : empleados) {
            System.out.println(num + " " + e);
            num++;
        }
        int posicion = InputAsker.askInt("Introduze el num del empleado:", 1, empleados.size());
        Empleado seleccionado = empleados.get(posicion - 1);
        return seleccionado;
    }
    
    /*
    //Metodo que muestra todos las incidencias, pide que se el usuario elija una y la devuelve
    public static Incidencia obtenerIncidencia() throws Excepcion {
        List<Incidencia> incidencias = gestor.selectAllIncidencias();
        if (incidencias.isEmpty()) {
            throw new Excepcion(Excepcion.noHayIncidencias);
        }
        System.out.println("Estos son las incidencias disponibles:");
        int num = 1;
        for (Incidencia i : incidencias) {
            System.out.println(num + " " + i);
            num++;
        }
        int posicion = InputAsker.askInt("Introduze el num de la incidencia:", 1, incidencias.size());
        Incidencia seleccionado = incidencias.get(posicion - 1);
        return seleccionado;
    }*/

    //Usamos el codigo ASCII para comprobar si la cadena pasada incluye caracteres diferentes a numeros
    public static boolean noEsUnNumero(String num) {
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) < 48 || num.charAt(i) > 57) {
                return true;
            }
        }
        return false;
    }

    //Metodo que pide y valida los datos introducidos por el usuario y crea un empleado
    public static void crearEmpleado() throws Excepcion {
        String username = InputAsker.askString("Introduce tu nombre");
        if (gestor.empleadoExiste(username)) {
            throw new Excepcion(Excepcion.empleadoYaExiste);
        }
        String password = InputAsker.askString("Introduce tu password");
        String nombreCompleto = InputAsker.askString("Introduce tu nombre completo");
        String telefono = InputAsker.askString("Introduce tu telefono");
        if (noEsUnNumero(telefono)) {
            throw new Excepcion(Excepcion.telefonoTipo);
        }
        if (telefono.length() != 9) {
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

    //Metodo que te deja escojer un atributo de un empleado para modificarlo
    public static void modificarEmpleado() throws Excepcion {
        Empleado empleado = obtenerEmpleado();       
        int opcion = 0;
        do {
            showMenuEmpleado(); 
            opcion = InputAsker.askInt("¿Que atributo quieres modificar?");           
            switch (opcion) {
                case 1:
                    modificarPassword(empleado);
                    break;
                case 2:
                    modificarNombreCompleto(empleado);
                    break;
                case 3:
                    modificarTelefono(empleado);
                    break;
            }
        } while (opcion != 0);
    }
    
    //Metodo que pasado un empleado le modifica la contraseña
    public static void modificarPassword(Empleado empleado) {
        String password = InputAsker.askString("Introduce la nueva contraseña");
        empleado.setPassword(password);
        gestor.updateEmpleado(empleado);
        System.out.println("Se ha modificado la contraseña");
    }
    
    //Metodo que pasado un empleado le modifica el nombre completo
    public static void modificarNombreCompleto(Empleado empleado) {
        String nombreCompleto = InputAsker.askString("Introduce un nuevo nombre completo");
        empleado.setNombreCompleto(nombreCompleto);
        gestor.updateEmpleado(empleado);
        System.out.println("Se ha modificado el nombre completo");
    }
    
    //Metodo que pasado un empleado le modifica el telefono
    public static void modificarTelefono(Empleado empleado) throws Excepcion{
        String telefono = InputAsker.askString("Introduce un nuevo telefono");
        if (noEsUnNumero(telefono)) {
            throw new Excepcion(Excepcion.telefonoTipo);
        }
        if (telefono.length() != 9) {
            throw new Excepcion(Excepcion.telefonoLength);
        }
        empleado.setTelefono(telefono);     
        gestor.updateEmpleado(empleado);
        System.out.println("Se ha modificado el telefono");
    }
    
    public static boolean eliminarEmpleado() throws Excepcion{
        List<Empleado> empleados = gestor.getAllEmpleados();
        if (empleados.size()==1) {
            throw new Excepcion(Excepcion.ultimoEmpleado);
        }
        Empleado empleado = obtenerEmpleado();       
        String opcion = InputAsker.askString("Estas seguro que quieres eliminar al empleado " + empleado.getUsername() + " (si/no)");
        if(opcion.equalsIgnoreCase("si")){
            gestor.removeEmpleado(empleado);
        }
        if(empleado.getUsername().equalsIgnoreCase(empleadoActual.getUsername())){
            return true;
        }
        System.out.println("Se ha eliminado el empleado " + empleado.getUsername());
        return false;
    }
    
    //Metodo que muestra una incidencia a partir de su id
    public static void mostrarIncidenciaPorId() throws Excepcion{
        int id = InputAsker.askInt("Introduce el id de lqa incidencia que quieres ver:");
        if(!gestor.incidenciaExiste(Integer.toString(id))){
            throw new Excepcion(Excepcion.incidenciaNoExiste);
        }
        Incidencia incidencia = gestor.getIncidenciaById(id);
        System.out.println(incidencia);
    }
    
    //Metodo para mostrar todas las incidencias
    public static void mostrarTodasLasIncidencias() throws Excepcion{
        List<Incidencia> incidencias = gestor.selectAllIncidencias();
        if(incidencias.isEmpty()){
           throw new Excepcion(Excepcion.noHayIncidencias); 
        }
        for (Incidencia i : incidencias){
            System.out.println(i);
        }
    }
    
    
    //PREGUNTAR EN ALGUN MOMENTO SI PUEDES ENVIARTE UNA INCIDENCIA A TI MISMO Y SI ES CORRECTO LOS SETS
    //Metodo que permite crear una incidencia.
    public static void crearIncidencia() throws Excepcion {      
        Incidencia incidencia = new Incidencia();
        Date fecha = new Date();
        incidencia.setId(Integer.toString(obtenerIdDisponible()));
        incidencia.setFecha(fecha);       
        incidencia.setNombre(InputAsker.askString("Introduce el nombre de la incidencia"));               
        incidencia.setTipo(InputAsker.askString("Introduce el tipo de incidencia (NORMAL/URGENTE)"));
        if(incidencia.getTipo().equals(Tipos.URGENTE)){
            insertarEvento("URGENTE");
        }
        incidencia.setDescripcion(InputAsker.askString("Introduce una descripcion"));
        System.out.println("Selecciona el empleado al que se le enviara la incidencia");
        Empleado empleado = obtenerEmpleado();
        incidencia.setOrigen(empleadoActual);
        incidencia.setDestino(empleado);    
        gestor.insertIncidencia(incidencia);
        System.out.println("Incidencia creada correctamente");
    }
    
    //Metodo para asignar una id disponible
    public static int obtenerIdDisponible(){
        int id = 1;
        while(gestor.incidenciaExiste(Integer.toString(id))){
            id++;
        }
        return id;
    }
    
    public static void mostrarIncidenciasPorUsuario() throws Excepcion{
        List<Incidencia> incidencias = new ArrayList<>();
        Empleado empleado = obtenerEmpleado();
        showMenuOptenerIncidenciaByEmpleado();
        int opcion = InputAsker.askInt("Introduze una opcion:", 1, 2);
        switch(opcion){
            case 1:
                incidencias = gestor.getIncidenciaByOrigen(empleado);
                break;
            case 2:
                incidencias = gestor.getIncidenciaByDestino(empleado);
                insertarEvento("CONSULTA");
                break;
            default:
                if (opcion != 0) {
                    System.out.println("El numero " + opcion + " no es una opcion valida.");
                }
        }
        if (incidencias.isEmpty()){
            throw new Excepcion(Excepcion.noHayIncidencias);
        }
        int num = 1;
        for(Incidencia i : incidencias){
            System.out.println(num + " " + i.toString());
            num++;
        }        
    }
    
    public static void insertarEvento(String e) throws Excepcion{
        Date date = new Date();
        Evento evento = new Evento();
        evento.setEmpleado(empleadoActual);
        evento.setEvento(e);
        evento.setFechaEvento(date);
        gestor.insertarEvento(evento);
    }
    
    public static void verUltimoInicio() throws Excepcion{
        Empleado empleado = obtenerEmpleado();
        Evento evento = gestor.getUltimoInicioSesion(empleado);
        if(evento.getFechaEvento()==null){
            throw new Excepcion(Excepcion.noHaIniciadoSesion);
        }
        String date = devolverFechaFormato();
        System.out.println("El empleado " + empleado.getUsername() + " ha iniciado sesion por ultima vez en: " + date);
    }
    
    public static String devolverFechaFormato(){
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return date;
    }
    
    public static void verRanking(){
        List<RankingTO> rankingTO = gestor.getRankingEmpleados();
        for(RankingTO r : rankingTO){
            System.out.println(r);
        }
    }
    

}