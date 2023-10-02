package dev.devriders.tracktrainer.models;

public class InfoUsuario {
    private int usuarioId;
    private String fechaNacimiento;
    private double peso;
    private double estatura;
    private String genero;

    // Getters y setters
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public double getEstatura() { return estatura; }
    public void setEstatura(double estatura) { this.estatura = estatura; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}

