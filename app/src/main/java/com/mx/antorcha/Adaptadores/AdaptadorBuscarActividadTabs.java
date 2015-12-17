package com.mx.antorcha.Adaptadores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mx.antorcha.Fragment.FragmentBuscarEspacio;
import com.mx.antorcha.Fragment.FragmentBuscarEventos;

/**
 * Created by Ruben on 15/12/2015.
 */
public class AdaptadorBuscarActividadTabs extends FragmentStatePagerAdapter {

    public AdaptadorBuscarActividadTabs(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0 :

                return new FragmentBuscarEspacio();
            case 1 :

                return new FragmentBuscarEventos();
            default:

                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Espacios deportivos";
            case 1:
                return "Eventos";
            default:
                return "Tab";
        }
    }
}
