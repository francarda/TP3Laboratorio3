package com.example.tp3laboratorio3;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import model.Usuario;
import request.ApiClient;

public class RegistroActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuarioM;
    private Context context;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context=application;

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

}
