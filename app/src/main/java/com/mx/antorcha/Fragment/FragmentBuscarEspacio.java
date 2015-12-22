package com.mx.antorcha.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.R;

/**
 * Created by Ruben on 15/12/2015.
 */
public class FragmentBuscarEspacio extends Fragment {

    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_buscar_espacio, container, false);

        //Se cargan las imagenes de los icono de contacto
        ImageView imageViewCompartir = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_espacio_compartir);
        ImageView imageViewContacto = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_espacio_contacto);
        ImageView imageViewLapiz = (ImageView) rootView.findViewById(R.id.sliding_buscar_actividades_espacio_lapiz);

        AdaptadorSVG.mostrarImagen(imageViewCompartir, activity, R.raw.icono_compartir);
        AdaptadorSVG.mostrarImagen(imageViewContacto, activity, R.raw.icono_llamada);
        AdaptadorSVG.mostrarImagen(imageViewLapiz, activity, R.raw.icono_lapiz);
        return rootView;
    }


    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
