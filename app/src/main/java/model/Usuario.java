package model;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String dni,apellido,nombre,mail,password;
    private Bitmap imagen;
    private String foto;

    public Usuario(String dni, String apellido, String nombre, String mail, String password, Bitmap imagen) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.mail = mail;
        this.password = password;
        this.imagen= imagen;


    }
    public Usuario(String dni, String apellido, String nombre, String mail, String password) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.mail = mail;
        this.password = password;
        this.foto= dni+".png";



    }

    public Usuario() {
    }

    public String getDni() {
        return dni;
    }


    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }



    @NonNull
    @Override
    public String toString() {
        return "model.Usuario{" +
                "dni= " + dni + ", apellido= '"+ apellido + '\''+
                ", nombre= '" + nombre + '\''+
                ", mail= '"+ mail + '\''+
                ", password='" + password + '\''+
                '}';
    }
}
