package com.example.tp3laboratorio3;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import model.Usuario;
import request.ApiClient;

public class RegistroActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuarioM;
    private Context context;
    private MutableLiveData<Bitmap> foto= new MutableLiveData<>();

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context=application;

    }
    public LiveData<Bitmap> getFoto() {
        if (foto == null) {
            foto = new MutableLiveData<>();
        }
        return foto;
    }

    public LiveData<Usuario> getUsuarioM(){
        if(usuarioM==null){
            usuarioM=new MutableLiveData<>();
        }
        return usuarioM;
    }
    public void guardar(Usuario usuario){
        ApiClient.guardar(context, usuario);
        Intent intent= new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);

    }
    public void obtenerDatos(Bundle bundle){
        if(bundle!=null){
            Usuario usuario= (Usuario) bundle.getSerializable("model.Usuario");
            usuarioM.setValue(usuario);
        }
    }
    public void leerFotoArchivo(String archivo){

        File archivoF = new File(context.getFilesDir(), archivo);

        try {
            FileInputStream fis = new FileInputStream(archivoF);
            BufferedInputStream bis = new BufferedInputStream(fis);
            //   ObjectInputStream ois = new ObjectInputStream(bis);

            byte b[] ;
            b = new byte[bis.available()];
            bis.read(b);

            Bitmap bm =  BitmapFactory.decodeByteArray(b, 0, b.length);
            this.foto.setValue(bm);


            bis.close();
            fis.close();

        } catch (FileNotFoundException e) {
            Log.d("salida",e.toString());
        } catch (IOException e) {
            Log.d("salida",e.toString());
        }

    }
    public void respuetaDeCamara(int requestCode, int resultCode, @Nullable Intent data, int REQUEST_IMAGE_CAPTURE, Usuario usuarioActual){
        // Log.d("salida",requestCode+"");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Recupero los datos provenientes de la camara.
            Bundle extras = data.getExtras();
            //Casteo a bitmap lo obtenido de la camara.
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            //Rutina para optimizar la foto,
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
            foto.setValue(imageBitmap);


            //Rutina para convertir a un arreglo de byte los datos de la imagen
            byte [] b=baos.toByteArray();


            //Aquí podría ir la rutina para llamar al servicio que recibe los bytes.
            File archivo =new File(context.getFilesDir(),usuarioActual.getDni() +".png");
            usuarioActual.setFoto(usuarioActual.getDni()+".png");
            if(archivo.exists()){
                archivo.delete();
            }
            try {
                FileOutputStream fo=new FileOutputStream(archivo);
                BufferedOutputStream bo=new BufferedOutputStream(fo);
                // ObjectOutputStream OOS = new ObjectOutputStream(bo);
                bo.write(b);
                bo.flush();
                bo.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
    /*public void ActualizarRegistrar(String dni, String apellido, String nombre, String correo, String contraseña) {

        if(dni.isEmpty() || apellido.isEmpty() || nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty()){
            Toast.makeText(context, "Debe llenar todos los campos", Toast.LENGTH_LONG).show();
        }else{
            Usuario usuario = new Usuario(dni, apellido, nombre, correo, contraseña);


            if (this.correoF != null) {

                this.correoF = ApiClient.ActualizarUsuario(context, usuario).getCorreo();

                if(this.correoF != null) {
                    Toast.makeText(context, "Actualizado con exito", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Error al actualizar", Toast.LENGTH_LONG).show();
                }
                this.cargarSesion(correoF);

            } else {

                this.correoF = ApiClient.registrar(context, usuario).getCorreo();



                if (this.correoF != null) {
                    Toast.makeText(context, "Registrado con exito", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Error al registrar", Toast.LENGTH_LONG).show();
                }
                this.cargarSesion(correoF);


            }
        }


    }*/


}
