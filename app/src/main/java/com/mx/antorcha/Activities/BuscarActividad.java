package com.mx.antorcha.Activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mx.antorcha.Adaptadores.AdaptadorBuscarActividadTabs;
import com.mx.antorcha.LibreriaTabsSliding.SlidingTabLayout;
import com.mx.antorcha.R;

public class BuscarActividad extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_actividad);

        //se carga la barra de android por el xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.buscar_actividad_toolbar);
        setSupportActionBar(toolbar);

        //se crea el adaptador para las tabs de la activity
        AdaptadorBuscarActividadTabs adaptadorBuscarActividadTabs = new AdaptadorBuscarActividadTabs(
                getSupportFragmentManager());

        //las páginas de las tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.buscar_actividad_pager);
        viewPager.setAdapter(adaptadorBuscarActividadTabs);

        //Sliding
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.buscar_actividad_tabs);
        slidingTabLayout.setDistributeEvenly(true);

        slidingTabLayout.setViewPager(viewPager);

    }



}
