package com.mx.antorcha.BaseDatos;

/**
 *
 */
public class Querys {

    /* TAGS PARA LA BASE DE DATOS */
    static String TAG_INSERTAR = "Insertando dato";

    /* QUERYS DE CREACION DE TABLAS */
    static String CREAR_TABLA_METAS = "CREATE TABLE Meta (Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Nombre TEXT, Inicio DECIMAL, Fin DECIMAL, FechaInicio DATE, FechaFin DATE, TipoMedida TEXT)";
    static String CREAR_TABLA_META_PROGRESO = "CREATE TABLE MetaProgreso (IdMeta INTEGER, Progreso DECIMAL, Fecha DATE)";

    /* QUERYS PARA OBTENER DATOS */

    //Metas
    static String OBTENER_METAS = "SELECT * FROM Meta";
    static String OBTENER_METAS_PROGRESO = "SELECT * FROM MetaProgreso WHERE IdMeta = ";


}
