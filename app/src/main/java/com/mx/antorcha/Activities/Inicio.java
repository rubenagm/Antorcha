package com.mx.antorcha.Activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.R;

public class Inicio extends AppCompatActivity {
    private ImageView imageViewRegistrarse;
    private ImageView imageViewIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Se inicializan los objetos del layout
        imageViewRegistrarse = (ImageView) findViewById(R.id.inicio_image_registrarse);
        imageViewIniciarSesion = (ImageView) findViewById(R.id.inicio_image_iniciar_sesion);

        //Cuando se presiona iniciar sesion
        imageViewIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, Principal.class);
                startActivity(intent);
            }
        });

        //Cuando se presiona registrar
        imageViewRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, Login.class);
                startActivity(intent);
            }
        });

        AdaptadorSVG.mostrarImagen(imageViewIniciarSesion, this, R.raw.iniciar_sesion);
        AdaptadorSVG.mostrarImagen(imageViewRegistrarse, this, R.raw.registrarse);
    }
}
