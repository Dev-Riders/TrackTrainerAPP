package dev.devriders.tracktrainer.models;

import java.util.HashSet;
import java.util.Set;

public class Categoria {
    private int idCategoria;

    private String nombre_categoria;

    //private Set<Ejercicio> ejercicios = new HashSet<>();

    public Categoria(int idCategoria, String nombre_categoria) {
        this.idCategoria = idCategoria;
        this.nombre_categoria = nombre_categoria;
        //this.ejercicios = ejercicios;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    /*public Set<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(Set<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }*/
}
