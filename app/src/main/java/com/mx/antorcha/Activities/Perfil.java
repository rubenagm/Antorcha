package com.mx.antorcha.Activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mx.antorcha.Adaptadores.AdaptadorPerfilTabs;
import com.mx.antorcha.LibreriaTabsSliding.SlidingTabLayout;
import com.mx.antorcha.R;

public class Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.perfil_toolbar);
        setSupportActionBar(toolbar);

        AdaptadorPerfilTabs adaptadorPerfilTabs = new AdaptadorPerfilTabs(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.perfil_pager);
        viewPager.setAdapter(adaptadorPerfilTabs);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.perfil_tabs);
        slidingTabLayout.setDistributeEvenly(true);
    }
}
