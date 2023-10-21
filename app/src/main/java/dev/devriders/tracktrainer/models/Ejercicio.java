package dev.devriders.tracktrainer.models;

public class Ejercicio {
    private String nombre_ejercicio;
    private String tipo_ejercicio;
    private String imagen_ejercicio;
    private String video_ejercicio;
    private String descripcion_ejercicio;
    private int id_ejercicio;

    public Ejercicio(String nombre_ejercicio, String tipo_ejercicio, String imagen_ejercicio, String video_ejercicio, String descripcion_ejercicio, int id_ejercicio) {
        this.nombre_ejercicio = nombre_ejercicio;
        this.tipo_ejercicio = tipo_ejercicio;
        this.imagen_ejercicio = imagen_ejercicio;
        this.video_ejercicio = video_ejercicio;
        this.descripcion_ejercicio = descripcion_ejercicio;
        this.id_ejercicio = id_ejercicio;
    }

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

    public String getImagen_ejercicio() {
        return imagen_ejercicio;
    }

    public void setImagen_ejercicio(String imagen_ejercicio) {
        this.imagen_ejercicio = imagen_ejercicio;
    }

    public String getVideo_ejercicio() {
        return video_ejercicio;
    }

    public void setVideo_ejercicio(String video_ejercicio) {
        this.video_ejercicio = video_ejercicio;
    }

    public String getDescripcion_ejercicio() {
        return descripcion_ejercicio;
    }

    public void setDescripcion_ejercicio(String descripcion_ejercicio) {
        this.descripcion_ejercicio = descripcion_ejercicio;
    }

    public int getId_ejercicio() {
        return id_ejercicio;
    }

    public void setId_ejercicio(int id_ejercicio) {
        this.id_ejercicio = id_ejercicio;
    }
}
