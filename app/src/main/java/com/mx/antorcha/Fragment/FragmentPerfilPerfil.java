package com.mx.antorcha.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Adaptadores.AdaptadorListaMedallas;
import com.mx.antorcha.Modelos.Medalla;
import com.mx.antorcha.R;

import java.util.ArrayList;

/**
 * Created by Ruben on 09/12/2015.
 */
public class FragmentPerfilPerfil extends Fragment {

    private Activity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_perfil_perfil, container, false);

        /******** VARIABLES TEMPORALES PARA PRUEBAS *******/
        ArrayList<Medalla> medallas = new ArrayList<>();
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());
        medallas.add(new Medalla());

        /*************************************************/

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);

        //Se crea el recycler view para las medallas
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.perfil_lista_medallas);
        recyclerView.setLayoutManager(layoutManager);
        AdaptadorListaMedallas adaptadorListaMedallas = new AdaptadorListaMedallas(activity, medallas);

        recyclerView.setAdapter(adaptadorListaMedallas);

        //Se colocan las imagenes de las medallas
        ImageView imageViewFlechaIzquierda = (ImageView) rootView.findViewById(R.id.perfil_flecha_izquierda);
        ImageView imageViewFlechaDerecha = (ImageView) rootView.findViewById(R.id.perfil_flecha_derecha);

        AdaptadorSVG.mostrarImagen(imageViewFlechaDerecha, activity, R.raw.icono_flecha_derecha);
        AdaptadorSVG.mostrarImagen(imageViewFlechaIzquierda, activity, R.raw.icono_flecha_izquierda);

        return rootView;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
