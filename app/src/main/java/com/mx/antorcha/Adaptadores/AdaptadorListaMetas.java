package com.mx.antorcha.Adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.Modelos.Meta;
import com.mx.antorcha.R;

import java.util.ArrayList;

/**
 *
 */
public class AdaptadorListaMetas extends ArrayAdapter<Meta> {
    Activity activity;

    public AdaptadorListaMetas(Activity activity, ArrayList<Meta> metas) {
        super(activity, R.layout.item_lista_meta, metas);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Meta meta = getItem(position);

        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        convertView = vi.inflate(R.layout.item_lista_meta, null);

        ImageView imageViewMasOpciones = (ImageView) convertView.findViewById(R.id.item_metas_mas_opciones);
        ImageView imageViewCompartir   = (ImageView) convertView.findViewById(R.id.item_metas_compartir);

        AdaptadorSVG.mostrarImagen(imageViewMasOpciones, activity, R.raw.icono_mas_opciones);
        AdaptadorSVG.mostrarImagen(imageViewCompartir, activity, R.raw.icono_compartir);

        //nombre de la meta
        TextView textViewNombre = (TextView) convertView.findViewById(R.id.item_meta_nombre_meta);
        textViewNombre.setText(meta.getNombre());

        //Cantidad de la meta
        TextView textViewInicio = (TextView) convertView.findViewById(R.id.item_meta_inicio);
        textViewInicio.setText(meta.getInicio() + "KG");

        TextView textViewFin = (TextView) convertView.findViewById(R.id.item_meta_fin);
        textViewFin.setText(meta.getFin() + "KG");

        return convertView;
    }

}
