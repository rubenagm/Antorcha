package com.mx.antorcha.MenuDrawer;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mx.antorcha.Activities.Actividades;
import com.mx.antorcha.Activities.BuscarActividad;
import com.mx.antorcha.Activities.Medallas;
import com.mx.antorcha.Activities.Metas;
import com.mx.antorcha.Activities.Perfil;
import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.R;

import java.util.ArrayList;

/**
 *
 */
public class AdapterDrawer extends ArrayAdapter<String> {
    private int resource;
    ImageView imageViewBuscarActividad;
    ImageView imageViewActividades;
    ImageView imageViewPerfil;
    ImageView imageViewMedallas;
    ImageView imageViewMetas;

    LinearLayout linearLayoutBuscarActividad;
    LinearLayout linearLayoutActividades;
    LinearLayout linearLayoutPerfil;
    LinearLayout linearLayoutMedallas;
    LinearLayout linearLayoutMetas;

    Activity activity;

    public AdapterDrawer(Activity activity, int resource, ArrayList<String> palabra) {
        super(activity, resource, palabra);
        this.activity = activity;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        convertView = vi.inflate(resource, null);

        //Se inicializan las imagenes para poder mostrar el icono en svg
        imageViewBuscarActividad = (ImageView)
                convertView.findViewById(R.id.drawer_icono_buscar_actividad);
        imageViewActividades = (ImageView) convertView.findViewById(R.id.drawer_icono_actividades);
        imageViewPerfil = (ImageView) convertView.findViewById(R.id.drawer_icono_perfil);
        imageViewMedallas = (ImageView) convertView.findViewById(R.id.drawer_icono_medallas);
        imageViewMetas = (ImageView) convertView.findViewById(R.id.drawer_icono_metas);

        //se muestran los iconos
        AdaptadorSVG.mostrarImagen(imageViewBuscarActividad, activity, R.raw.icono_buscar_actividad);
        AdaptadorSVG.mostrarImagen(imageViewActividades, activity, R.raw.icono_actividades);
        AdaptadorSVG.mostrarImagen(imageViewPerfil, activity, R.raw.icono_perfil);
        AdaptadorSVG.mostrarImagen(imageViewMedallas, activity, R.raw.icono_medallas);
        AdaptadorSVG.mostrarImagen(imageViewMetas, activity, R.raw.icono_metas);

        //se inicializan los linear layout
        linearLayoutBuscarActividad = (LinearLayout) convertView.findViewById(R.id.drawer_layout_buscar_actividad);
        linearLayoutActividades = (LinearLayout) convertView.findViewById(R.id.drawer_layout_actividades);
        linearLayoutPerfil = (LinearLayout) convertView.findViewById(R.id.drawer_layout_perfil);
        linearLayoutMedallas = (LinearLayout) convertView.findViewById(R.id.drawer_layout_medallas);
        linearLayoutMetas = (LinearLayout) convertView.findViewById(R.id.drawer_layout_metas);

        //Se agregan los clics del menú
        linearLayoutPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Perfil.class);
                activity.startActivity(intent);
            }
        });

        linearLayoutMedallas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Medallas.class);
                activity.startActivity(intent);
            }
        });

        linearLayoutBuscarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, BuscarActividad.class);
                activity.startActivity(intent);

            }
        });

        linearLayoutMetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Metas.class);
                activity.startActivity(intent);
            }
        });

        linearLayoutActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Actividades.class);
                activity.startActivity(intent);
            }
        });

        return convertView;
    }
}
