package com.example.tp3laboratorio3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp3laboratorio3.databinding.ActivityRegistroBinding;

import model.Usuario;

public class RegistroActivity extends AppCompatActivity {
private ActivityRegistroBinding binding;
private RegistroActivityViewModel vm;
private Usuario usuarioActual = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        vm.getUsuarioM().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etApellido.setText(usuario.getApellido());
                binding.etDni.setText(usuario.getDni());
                binding.etMailRegistro.setText(usuario.getMail());
                binding.etNombre.setText(usuario.getNombre());
                binding.etpassRegistro.setText(usuario.getPassword());
                usuarioActual=usuario;
                vm.leerFotoArchivo(usuario.getFoto());
            }

        });
        Bundle bundle= getIntent().getExtras();
        vm.obtenerDatos(bundle);
        binding.btTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        });
        vm.getFoto().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                binding.imageView.setImageBitmap(bitmap);
            }
        });



        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre= binding.etNombre.getText().toString();
                String apellido=binding.etApellido.getText().toString();
                String mail=binding.etMailRegistro.getText().toString();
                String pass=binding.etpassRegistro.getText().toString();
                String dni=binding.etDni.getText().toString();
                //Bitmap imagen= binding.imageView

                Usuario usuario= new Usuario(dni,apellido,nombre,mail,pass);
                vm.guardar(usuario);

            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        vm.respuetaDeCamara(requestCode, resultCode, data, 1,usuarioActual);
    }
}