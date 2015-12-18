package com.mx.antorcha.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Modelos.Meta;
import com.mx.antorcha.R;

import java.util.ArrayList;

/**
 * Created by Ruben on 17/12/2015.
 */
public class AdaptadorListaMetas extends ArrayAdapter<Meta> {
    Activity activity;

    public AdaptadorListaMetas(Activity activity, ArrayList<Meta> metas) {
        super(activity, R.layout.item_lista_meta, metas);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        convertView = vi.inflate(R.layout.item_lista_meta, null);

        ImageView imageViewMasOpciones = (ImageView) convertView.findViewById(R.id.item_metas_mas_opciones);
        ImageView imageViewCompartir   = (ImageView) convertView.findViewById(R.id.item_metas_compartir);

        AdaptadorSVG.mostrarImagen(imageViewMasOpciones, activity, R.raw.icono_mas_opciones);
        AdaptadorSVG.mostrarImagen(imageViewCompartir, activity, R.raw.icono_compartir);

        return convertView;
    }

}
