package com.mx.antorcha.BaseDatos;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mx.antorcha.Modelos.Meta;
import com.mx.antorcha.Modelos.MetaProgreso;

import java.util.ArrayList;

/**
 *
 */
public class ConexionBaseDatosObtener extends SQLiteOpenHelper {

    public ConexionBaseDatosObtener(Activity activity) {
        super(activity, "Antorcha", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Querys.CREAR_TABLA_METAS);
        db.execSQL(Querys.CREAR_TABLA_META_PROGRESO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Meta> obtenerMetas () {
        ArrayList<Meta> metas = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(Querys.OBTENER_METAS, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            metas.add(new Meta(
                    cursor.getInt(cursor.getColumnIndex("Id")),
                    cursor.getDouble(cursor.getColumnIndex("Inicio")),
                    cursor.getDouble(cursor.getColumnIndex("Fin")),
                    cursor.getString(cursor.getColumnIndex("Nombre")),
                    cursor.getString(cursor.getColumnIndex("FechaFin")),
                    cursor.getString(cursor.getColumnIndex("FechaInicio")),
                    cursor.getString(cursor.getColumnIndex("TipoMedida"))
            ));

            cursor.moveToNext();
        }
        cursor.close();

        return metas;
    }

    public ArrayList<MetaProgreso> obtenerMetaProgreso (int idMeta) {
        ArrayList<MetaProgreso> metaProgresos = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(Querys.OBTENER_METAS_PROGRESO + idMeta, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            metaProgresos.add(new MetaProgreso(
                    cursor.getInt(cursor.getColumnIndex("IdMeta")),
                    cursor.getDouble(cursor.getColumnIndex("Progreso")),
                    cursor.getString(cursor.getColumnIndex("Fecha"))
            ));

            cursor.moveToNext();
        }
        cursor.close();

        return metaProgresos;
    }

}
