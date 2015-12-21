package com.mx.antorcha.BaseDatos;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mx.antorcha.Modelos.Meta;

/**
 * Created by Ruben on 20/12/2015.
 */
public class ConexionBaseDatosInsertar extends SQLiteOpenHelper {

    public ConexionBaseDatosInsertar(Activity activity) {
        super(activity, "Antorcha", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Querys.CREAR_TABLA_METAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //NADA HASTA AHORITA
    }

    public void insertarMeta(Meta meta){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues   = new ContentValues();

        contentValues.put("Nombre", meta.getNombre());
        contentValues.put("Inicio", meta.getInicio());
        contentValues.put("Fin", meta.getFin());
        contentValues.put("FechaInicio", meta.getFechaInicio());
        contentValues.put("FechaFin", meta.getFechaFin());
        contentValues.put("TipoMedida", meta.getTipoMedida());

        sqLiteDatabase.insert("Meta", null, contentValues);

        Log.i(Querys.TAG_INSERTAR, "Se ha insertado una meta");
    }
}
