package Objetos;

/**
 * Created by alejandrogs on 20/05/17.
 */

public class Usuario {

    String email;
    String name;
    String country;
    String city;
    int age;
    String adress;
    String phone;
    String ruta;

    public Usuario(){

    }

    public Usuario(String adress,int age,String city,String country,String email,String name,String phone,String ruta) {
        this.email = email;
        this.name = name;
        this.country = country;
        this.city = city;
        this.age = age;
        this.adress = adress;
        this.phone = phone;
        this.ruta = ruta;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getRuta() {return ruta;}

    public void setRuta(String ruta) { this.ruta = ruta;}
}
