package dev.devriders.tracktrainer.models;

import java.util.HashSet;
import java.util.Set;

public class Ejercicio {
    private String nombreEjercicio;
    private String imagenEjercicio;
    private String videoEjercicio;
    private String descripcionEjercicio;
    private int idEjercicio;

    private Categoria categorias;


    public Ejercicio(String nombreEjercicio, String imagenEjercicio, String videoEjercicio, String descripcionEjercicio, int idEjercicio, Categoria categorias) {
        this.nombreEjercicio = nombreEjercicio;
        this.imagenEjercicio = imagenEjercicio;
        this.videoEjercicio = videoEjercicio;
        this.descripcionEjercicio = descripcionEjercicio;
        this.idEjercicio = idEjercicio;
        this.categorias = categorias;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getImagenEjercicio() {
        return imagenEjercicio;
    }

    public void setImagenEjercicio(String imagenEjercicio) {
        this.imagenEjercicio = imagenEjercicio;
    }

    public String getVideoEjercicio() {
        return videoEjercicio;
    }

    public void setVideoEjercicio(String videoEjercicio) {
        this.videoEjercicio = videoEjercicio;
    }

    public String getDescripcionEjercicio() {
        return descripcionEjercicio;
    }

    public void setDescripcionEjercicio(String descripcionEjercicio) {
        this.descripcionEjercicio = descripcionEjercicio;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public Categoria getCategorias() {
        return categorias;
    }

    public void setCategorias(Categoria categorias) {
        this.categorias = categorias;
    }
}
