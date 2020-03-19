package modelo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

//Ektorp no parsea los constructores por lo que tendremos que setear los campos a mano 
//Las propiedades no pueden ser nulas y no estaran en el json los campos vacios.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Empleado {
    @JsonProperty("_id") private String id;
    @JsonProperty("_rev") private String rev;
    private String username;
    private String password;
    private String nombreCompleto;
    private String telefono;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    //Sobre escribimos el toString para que nos muestre los datos que nos interesen en vez de la posicion en memoria del objeto
    @Override
    public String toString() {
        return "Username = " + username + ", Password = " + password + ", Nombre Completo = " + nombreCompleto + ", Telefono = " + telefono;
    }
    
    
}
