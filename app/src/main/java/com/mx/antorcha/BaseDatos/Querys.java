package com.mx.antorcha.BaseDatos;

/**
 *
 */
public class Querys {

    /* TAGS PARA LA BASE DE DATOS */
    static String TAG_INSERTAR = "Insertando dato";

    /* QUERYS DE CREACION DE TABLAS */
    static String CREAR_TABLA_METAS = "CREATE TABLE Meta (Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Nombre TEXT, Inicio DECIMAL, Fin DECIMAL, FechaInicio DATE, FechaFin DATE, TipoMedida TEXT)";

    /* QUERYS PARA OBTENER DATOS */
    static String OBTENER_METAS = "SELECT * FROM Meta";
}
