package com.example.practico3fotoperfil.ui.registro;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.practico3fotoperfil.R;
import com.example.practico3fotoperfil.databinding.ActivityRegistroBinding;
import com.example.practico3fotoperfil.modelo.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private RegistroActivityViewModel mv;

    private ActivityRegistroBinding binding;

    private Usuario usuarioActual = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String correo = getIntent().getStringExtra("correo");

        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.ActualizarRegistrar(binding.etDni.getText().toString(),
                        binding.etApellido.getText().toString(),
                        binding.etNombre.getText().toString(),
                        binding.etMail.getText().toString(),
                        binding.etPass.getText().toString());
            }
        });


        mv.getBotonM().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.btGuardar.setText(s);
            }
        });

        mv.getTituloM().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.TVCartel.setText(s);
            }
        });

        mv.getUsuarioM().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etDni.setText(usuario.getDni());
                binding.etApellido.setText(usuario.getApellido());
                binding.etNombre.setText(usuario.getNombre());
                binding.etMail.setText(usuario.getCorreo());
                binding.etPass.setText(usuario.getContraseña());
                usuarioActual = usuario;
                mv.leerFotoArchivo(usuarioActual.getFoto());
            }
        });

        binding.btSacarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivityForResult es otra forma de iniciar una activity, pero esperando desde donde la llamé un resultado
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }

            }
        });


        mv.getFoto().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                binding.ivFoto.setImageBitmap(bitmap);
            }
        });





        /*-------------------*/
        mv.cargarSesion(correo);




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mv.respuetaDeCamara(requestCode, resultCode, data, 1,usuarioActual);
    }
}