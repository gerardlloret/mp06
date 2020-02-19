package modelo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

//Las propiedades no pueden ser nulas y no estaran en el json los campos vacios.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Empleado {
    @JsonProperty("_username") private String username;
    @JsonProperty("_password") private String password;
    @JsonProperty("_nombreCompleto") private String nombreCompleto;
    @JsonProperty("_telefono") private String telefono;

    public Empleado(String username, String password, String nombreCompleto, String telefono) {
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
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

    @Override
    public String toString() {
        return "Empleado{" + "username=" + username + ", password=" + password + ", nombreCompleto=" + nombreCompleto + ", telefono=" + telefono + '}';
    }
    
    
}
