package dev.devriders.tracktrainer.models;

public class Ejexuser {

    private int id_ejexuser;

    private Usuario usuario;

    private Ejercicio ejercicio;

    private int cantidad;

    private float peso;

    private String tiempo;

    public Ejexuser(int id_ejexuser, Usuario usuario, Ejercicio ejercicio, int cantidad, float peso, String tiempo) {
        this.id_ejexuser = id_ejexuser;
        this.usuario = usuario;
        this.ejercicio = ejercicio;
        this.cantidad = cantidad;
        this.peso = peso;
        this.tiempo = tiempo;
    }

    public int getId_ejexuser() {
        return id_ejexuser;
    }

    public void setId_ejexuser(int id_ejexuser) {
        this.id_ejexuser = id_ejexuser;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
