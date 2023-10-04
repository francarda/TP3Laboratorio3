package com.example.tp3laboratorio3;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp3laboratorio3.databinding.ActivityRegistroBinding;

import model.Usuario;

public class RegistroActivity extends AppCompatActivity {
private ActivityRegistroBinding binding;
private RegistroActivityViewModel vm;
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
            }
        });
        Bundle bundle= getIntent().getExtras();
        vm.obtenerDatos(bundle);


        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre= binding.etNombre.getText().toString();
                String apellido=binding.etApellido.getText().toString();
                String mail=binding.etMailRegistro.getText().toString();
                String pass=binding.etpassRegistro.getText().toString();
                String dni=binding.etDni.getText().toString();

                Usuario usuario= new Usuario(dni,apellido,nombre,mail,pass);
                vm.guardar(usuario);

            }
        });

    }
}