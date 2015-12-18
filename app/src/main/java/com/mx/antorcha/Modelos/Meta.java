package com.mx.antorcha.Modelos;

/**
 *
 */
public class Meta {

    private int id;
    private double inicio;
    private double fin;
    private String nombre;
    private String fechaFin;
    private String fechaInicio;

    public Meta (int id, double inicio, double fin, String nombre, String fechaFin, String fechaInicio) {
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.nombre = nombre;
    }

    public Meta () {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getInicio() {
        return inicio;
    }

    public void setInicio(double inicio) {
        this.inicio = inicio;
    }

    public double getFin() {
        return fin;
    }

    public void setFin(double fin) {
        this.fin = fin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}