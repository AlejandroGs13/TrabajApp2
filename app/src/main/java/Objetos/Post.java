package Objetos;

/**
 * Created by alejandrogs on 20/05/17.
 */

public class Post {

    String titulo;
    String necesita;
    String numero;
    String email;
    String nombre;
    String ruta;

    public Post(String titulo, String necesita, String numero, String email, String nombre, String ruta) {
        this.titulo = titulo;
        this.necesita = necesita;
        this.numero = numero;
        this.email = email;
        this.nombre = nombre;
        this.ruta = ruta;
    }

    public Post() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNecesita() {
        return necesita;
    }

    public void setNecesita(String necesita) {
        this.necesita = necesita;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
