package dev.devriders.tracktrainer.models;

public class Mision {

    private Long idmision;
    private String nombreMision;
    private String descripcionMision;
    private int puntaje;
    private int tipo;

    public Mision(Long idmision, String nombreMision, String descripcionMision, int puntaje, int tipo) {
        this.idmision = idmision;
        this.nombreMision = nombreMision;
        this.descripcionMision = descripcionMision;
        this.puntaje = puntaje;
        this.tipo = tipo;
    }

    public Long getIdmision() {
        return idmision;
    }

    public void setIdmision(Long idmision) {
        this.idmision = idmision;
    }

    public String getNombreMision() {
        return nombreMision;
    }

    public void setNombreMision(String nombreMision) {
        this.nombreMision = nombreMision;
    }

    public String getDescripcionMision() {
        return descripcionMision;
    }

    public void setDescripcionMision(String descripcionMision) {
        this.descripcionMision = descripcionMision;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
