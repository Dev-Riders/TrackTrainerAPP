package dev.devriders.tracktrainer.models;

public class Ejercicio {
    private String nombre_ejercicio;
    private String tipo_ejercicio;
    private int id_ejercicio;

    public Ejercicio(String nombre_ejercicio, String tipo_ejercicio, int id_ejercicio) {
        this.nombre_ejercicio = nombre_ejercicio;
        this.tipo_ejercicio = tipo_ejercicio;
        this.id_ejercicio = id_ejercicio;
    }

    // Getters y setters

    public String getNombre_ejercicio() {
        return nombre_ejercicio;
    }

    public void setNombre_ejercicio(String nombre_ejercicio) {
        this.nombre_ejercicio = nombre_ejercicio;
    }

    public String getTipo_ejercicio() {
        return tipo_ejercicio;
    }

    public void setTipo_ejercicio(String tipo_ejercicio) {
        this.tipo_ejercicio = tipo_ejercicio;
    }

    public int getId_ejercicio() {
        return id_ejercicio;
    }

    public void setId_ejercicio(int id_ejercicio) {
        this.id_ejercicio = id_ejercicio;
    }
}
