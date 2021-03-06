package com.mx.antorcha.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Adaptadores.AdaptadorListaMetas;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosObtener;
import com.mx.antorcha.MenuDrawer.AdapterDrawer;
import com.mx.antorcha.Modelos.Meta;
import com.mx.antorcha.R;

import java.util.ArrayList;

public class Metas extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metas);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //se carga la barra de android por el xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.metas_toolbar);
        setSupportActionBar(toolbar);

        //Se agrega el drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        listView = (ListView) findViewById(R.id.lista_drawer);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
        listView.setAdapter(new AdapterDrawer(this, R.layout.drawer, arrayList, "Metas"));

        //Se carga el drawer
        ImageView imageViewDrawer = (ImageView) findViewById(R.id.metas_barra_drawer);
        AdaptadorSVG.mostrarImagen(imageViewDrawer, this, R.raw.icono_menu_drawer);

        imageViewDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        //Se carga el icono de agregar una nueva meta
        ImageView imageViewAgregarMeta = (ImageView) findViewById(R.id.metas_barra_agregar_meta);
        AdaptadorSVG.mostrarImagen(imageViewAgregarMeta, this, R.raw.icono_agregar);

        //On click del menu
        imageViewAgregarMeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Metas.this, NuevaMeta.class);
                startActivity(intent);
                finish();
            }
        });

        /**************
        //VARIABLES PARA PRUEBAS
        ArrayList<Meta> metas = new ArrayList<>();
        metas.add(new Meta());
        metas.add(new Meta());
        metas.add(new Meta());
        /**************/

        ConexionBaseDatosObtener conexionBaseDatosObtener = new ConexionBaseDatosObtener(this);
        ArrayList<Meta> metas = conexionBaseDatosObtener.obtenerMetas();

        //Se carga el adapter para listar las metas
        ListView listViewMetas = (ListView) findViewById(R.id.metas_lista_metas);
        AdaptadorListaMetas adaptadorListaMetas = new AdaptadorListaMetas(this, metas, getSupportFragmentManager());
        adaptadorListaMetas.setListView(listViewMetas);

        listViewMetas.setAdapter(adaptadorListaMetas);
    }
}
