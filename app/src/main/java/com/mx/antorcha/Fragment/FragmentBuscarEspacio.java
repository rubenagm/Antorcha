package com.mx.antorcha.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mx.antorcha.R;

/**
 * Created by Ruben on 15/12/2015.
 */
public class FragmentBuscarEspacio extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buscar_espacio, container, false);
        return rootView;
    }


}
