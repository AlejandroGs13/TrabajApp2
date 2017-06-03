package Objetos;

/**
 * Created by alejandrogs on 23/05/17.
 */

public class Trabajo {

    String correo;
    String nombre;
    String numero;
    String ciudad;
    String pais;
    String ruta;
    String skills;
    String descripcion;

    public Trabajo() {
    }

    public Trabajo(String correo, String nombre, String numero, String ciudad, String pais, String skills,String ruta,String descripcion) {
        this.correo = correo;
        this.nombre = nombre;
        this.numero = numero;
        this.ciudad = ciudad;
        this.pais = pais;
        this.ruta = ruta;
        this.skills = skills;
        this.descripcion  = descripcion;
    }

    public String getRuta() {
        return ruta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
