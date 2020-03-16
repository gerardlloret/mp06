package modelo;

public class RankingTO implements Comparable<RankingTO>{
    private String nombre;
    private int numUrgente;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumUrgente() {
        return numUrgente;
    }

    public void setNumUrgente(int numUrgente) {
        this.numUrgente = this.numUrgente + numUrgente;
    }
    
    //Sobreescribimos el compareTo para que ordene los usuarios por numero de incidencias urgentes que hayan creado
    @Override
    public int compareTo(RankingTO t) {
        if(this.numUrgente > t.numUrgente){
            return -1;
        }
        if (this.numUrgente < t.numUrgente) {
            return 1;
        }
        return 0;
    }    

    //Sobre escribimos el toString para que nos muestre los datos que nos interesen en vez de la posicion en memoria del objeto
    @Override
    public String toString() {
        return "RankingTO{" + "nombre=" + nombre + ", numUrgente=" + numUrgente + '}';
    }
        
}