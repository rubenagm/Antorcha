package com.mx.antorcha.Activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosInsertar;
import com.mx.antorcha.Modelos.Meta;
import com.mx.antorcha.R;

public class NuevaMeta extends AppCompatActivity {
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_meta);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Se inicializan todas las variables qpara datos
        final EditText editTextNombre = (EditText) findViewById(R.id.nueva_meta_nombre_meta);
        final EditText editTextInicio = (EditText) findViewById(R.id.nueva_meta_inicio);
        final EditText editTextFin = (EditText) findViewById(R.id.nueva_meta_fin);


        //Se inicializa la activity
        activity = this;

        //se carga la barra de android por el xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.nueva_meta_toolbar);
        setSupportActionBar(toolbar);

        //click en regresar
        ImageView imageViewRegresar = (ImageView) findViewById(R.id.nueva_meta_barra_regresar);
        AdaptadorSVG.mostrarImagen(imageViewRegresar, this, R.raw.icono_regresar);

        imageViewRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView imageViewGuardarMeta = (ImageView) findViewById(R.id.nueva_meta_image_guardar);
        AdaptadorSVG.mostrarImagen(imageViewGuardarMeta, this,R.raw.boton_guardar_meta);

        imageViewGuardarMeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Meta meta = new Meta();
                meta.setNombre(editTextNombre.getText().toString());
                meta.setInicio(Double.parseDouble(editTextInicio.getText().toString()));
                meta.setFin(Double.parseDouble(editTextFin.getText().toString()));
                meta.setFechaInicio("2015-10-10");
                meta.setFechaFin("2015-10-10");
                meta.setTipoMedida("Kilogramos");

                ConexionBaseDatosInsertar conexionBaseDatosInsertar = new ConexionBaseDatosInsertar(activity);
                conexionBaseDatosInsertar.insertarMeta(meta);
            }
        });
    }
}
