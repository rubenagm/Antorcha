package com.mx.antorcha.Adaptadores;

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

    public AdaptadorPerfilTabs(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0 :

                return new FragmentPerfilPerfil();
            case 1 :

                return new FragmentPerfilDeportes();
            default:

                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
