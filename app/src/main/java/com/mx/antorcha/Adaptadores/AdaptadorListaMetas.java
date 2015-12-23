package com.mx.antorcha.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.LruCache;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mx.antorcha.Activities.Perfil;
import com.mx.antorcha.AdaptadorSVG.AdaptadorSVG;
import com.mx.antorcha.BaseDatos.ConexionBaseDatosObtener;
import com.mx.antorcha.Dialogos.DialogoMeta;
import com.mx.antorcha.Modelos.Meta;
import com.mx.antorcha.Modelos.MetaProgreso;
import com.mx.antorcha.R;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 */
public class AdaptadorListaMetas extends ArrayAdapter<Meta> {
    Activity activity;

    FragmentManager fragmentManager;
    private ListView listView;
    View viewCompartir;
    LinearLayout linearLayoutClic;

    public AdaptadorListaMetas(Activity activity, ArrayList<Meta> metas, FragmentManager fragmentManager) {
        super(activity, R.layout.item_lista_meta, metas);
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Meta meta = getItem(position);

        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        convertView = vi.inflate(R.layout.item_lista_meta, null);
        viewCompartir = convertView;

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



        //Onclick de compartir
        RelativeLayout relativeLayoutClicCompartir = (RelativeLayout) convertView.findViewById(R.id.item_metas_clic_compartir);

        relativeLayoutClicCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmapCompartir = viewABitmap(linearLayoutClic);
                //se guarda la imagen
                String root = Environment.getExternalStorageDirectory().toString();
                File myDir = new File(root + "/antorcha");
                myDir.mkdirs();
                String fname = "compartir.jpg";
                File file = new File (myDir, fname);

                if (file.exists ()) {
                    file.delete ();
                }
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bitmapCompartir.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Uri imageUri = Uri.parse(Environment.getExternalStorageDirectory().toString() + "/antorcha/compartir.jpg");
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("image/*");
                sendIntent.putExtra(Intent.EXTRA_STREAM, imageUri);// this is for image . here filename_toshare is your file path.
                sendIntent.putExtra(Intent.EXTRA_TEXT, "antorcha.com.mx");// this is for text
                activity.startActivity(Intent.createChooser(sendIntent, "Email:"));
            }
        });

        //Se cargan los progresos de las metas
        ConexionBaseDatosObtener conexionBaseDatosObtener = new ConexionBaseDatosObtener(activity);
        ArrayList<MetaProgreso> metaProgresos = conexionBaseDatosObtener.obtenerMetaProgreso(meta.getId());
        MetaProgreso metaProgreso = null;
        //Se crea un meta progreso para guardar el último
        if (metaProgresos.size() != 0) {
            metaProgreso = metaProgresos.get(metaProgresos.size() - 1);
        }

        ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.item_meta_progress);

        int porcentaje = obtenerPorcentaje(meta, metaProgreso);

        //Se muestra en texto el porcentaje de la meta
        TextView textViewPorcentaje = (TextView) convertView.findViewById(R.id.item_meta_porcentaje);
        textViewPorcentaje.setText(porcentaje + "%");

        progressBar.setProgress(porcentaje);


        //Onlclick del item de la lista
        linearLayoutClic = (LinearLayout) convertView.findViewById(R.id.item_meta_clic_item);
        linearLayoutClic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoMeta dialogoMeta = new DialogoMeta();
                dialogoMeta.setFragmentManager(fragmentManager);
                dialogoMeta.setIdMeta(meta.getId());
                dialogoMeta.setActivity(activity);
                dialogoMeta.setListView(listView);
                dialogoMeta.show(fragmentManager, "dialogo_meta");
            }
        });
        return convertView;
    }

    static public int obtenerPorcentaje (Meta meta, MetaProgreso metaProgreso) {
        //se calcula el porcentaje
        double porcentaje = 0;

        if (meta.getFin() > meta.getInicio()) {
            porcentaje = 100 / (meta.getFin() - meta.getInicio()) ;
        } else {
            porcentaje = 100 / (meta.getInicio() - meta.getFin());
        }


        //Se calcula el último porcentaje
        if (metaProgreso != null) {

            if (meta.getInicio() < meta.getFin()) {
                porcentaje = (metaProgreso.getProgreso() - meta.getInicio()) * porcentaje;
            } else {
                porcentaje = (meta.getInicio() - metaProgreso.getProgreso()) * porcentaje;
            }
        } else {
            porcentaje = 0;
        }

        //si es mayor a 100 se pone 100
        if (porcentaje > 100) {
            porcentaje = 100;
        }

        if (porcentaje < 0) {
            porcentaje = 0;
        }

        return (int) porcentaje;
    }

    public static Bitmap viewABitmap(View v) {

        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        Bitmap bm = v.getDrawingCache();
        return bm;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public static Bitmap scaleDownBitmap(Bitmap photo, int newHeight, Context context) {

        final float densityMultiplier = context.getResources().getDisplayMetrics().density;

        int h= (int) (newHeight*densityMultiplier);
        int w= (int) (h * photo.getWidth()/((double) photo.getHeight()));

        photo=Bitmap.createScaledBitmap(photo, w, h, true);

        return photo;
    }
}
