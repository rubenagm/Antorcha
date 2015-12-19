package com.mx.antorcha.Adaptadores;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mx.antorcha.Fragment.FragmentPerfilDeportes;
import com.mx.antorcha.Fragment.FragmentPerfilPerfil;

/**
 * Created by Ruben on 09/12/2015.
 */
public class AdaptadorPerfilTabs extends FragmentStatePagerAdapter {

    private Activity activity;

    public AdaptadorPerfilTabs(FragmentManager fm, Activity activity) {
        super(fm);
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0 :
                FragmentPerfilPerfil fragmentPerfilPerfil = new FragmentPerfilPerfil();
                fragmentPerfilPerfil.setActivity(activity);
                return fragmentPerfilPerfil;
            case 1 :
                FragmentPerfilDeportes fragmentPerfilDeportes = new FragmentPerfilDeportes();
                fragmentPerfilDeportes.setActivity(activity);
                return fragmentPerfilDeportes;
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
                return "Personal";
            case 1:
                return "Deportes";
            default:
                return "Tab";
        }
    }
}
