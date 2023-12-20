package dev.devriders.tracktrainer.models;

public class Amigo {
    private Long idamigos;
    private String estado;
    private String eliminacion;
    private Usuario usuario; // Asumiendo que tienes un modelo Usuario
    private Usuario amigo;

    // Getters y setters

    public Long getIdamigos() {
        return idamigos;
    }

    public void setIdamigos(Long idamigos) {
        this.idamigos = idamigos;
    }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getEliminacion() { return eliminacion; }
    public void setEliminacion(String eliminacion) { this.eliminacion = eliminacion; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Usuario getAmigo() { return amigo; }
    public void setAmigo(Usuario amigo) { this.amigo = amigo; }


}
