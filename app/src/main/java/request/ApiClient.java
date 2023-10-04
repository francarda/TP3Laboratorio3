package request;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import model.Usuario;

public class ApiClient {

    public static void guardar(Context context, Usuario usuario){

        String dni=usuario.getDni();
        String apellido=  usuario.getApellido();
        String nombre= usuario.getNombre();
        String mail= usuario.getMail();
        String password=  usuario.getPassword();

        File carpeta= context.getFilesDir();
        File archivo= new File(carpeta, "usuario.dat");
        try{
            FileOutputStream fos= new FileOutputStream(archivo);
            BufferedOutputStream bos= new BufferedOutputStream(fos);
            DataOutputStream dos= new DataOutputStream(bos);
            dos.writeUTF(dni);
            dos.writeUTF(apellido);
            dos.writeUTF(nombre);
            dos.writeUTF(mail);
            dos.writeUTF(password);
            bos.flush();
            bos.close();



        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Error al guardar", Toast.LENGTH_LONG).show();
        } catch (IOException io) {
            Toast.makeText(context, "Error E/S", Toast.LENGTH_LONG).show();
        }


    }
    public static Usuario leer(Context context){
        Usuario usuario= new Usuario();

        File carpeta= context.getFilesDir();
        File archivo= new File(carpeta, "usuario.dat");
        try{
            FileInputStream fis= new FileInputStream(archivo);
            BufferedInputStream bis= new BufferedInputStream(fis);
            DataInputStream dis= new DataInputStream(bis);
            String dni= dis.readUTF();
            String apellido= dis.readUTF();
            String nombre= dis.readUTF();
            String mail= dis.readUTF();
            String password= dis.readUTF();
            usuario.setApellido(apellido);
            usuario.setDni(dni);
            usuario.setMail(mail);
            usuario.setPassword(password);
            usuario.setNombre(nombre);

        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Error al guardar", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(context, "Error E/S", Toast.LENGTH_LONG).show();
        }
        return usuario;
    }
    public static Usuario login(Context context, String mail, String password) {
        Usuario usuario = null;

        File carpeta = context.getFilesDir();
        File archivo = new File(carpeta, "usuario.dat");
        try {
            FileInputStream fis = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream dis = new DataInputStream(bis);
            String dni = dis.readUTF();
            String apellido = dis.readUTF();
            String nombre = dis.readUTF();
            String email = dis.readUTF();
            String epassword = dis.readUTF();

            if (mail.equals(email) && password.equals(epassword)) {

                usuario = new Usuario(dni, apellido, nombre, mail, password);
            }
            return usuario;
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Error al guardar", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(context, "Error E/S", Toast.LENGTH_LONG).show();
        }
        return usuario;

    }




}
