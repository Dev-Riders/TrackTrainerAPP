package dev.devriders.tracktrainer.models;


public class Puntaje {
    private Long idPuntaje;
    private Long puntajeMensual;
    private Long puntajeHistorico;
    private Usuario usuario; // El usuario asociado con el puntaje
    private Amigo amigo;

    public Puntaje(Long idPuntaje, Long puntajeMensual, Long puntajeHistorico, Usuario usuario, Amigo amigo) {
        this.idPuntaje = idPuntaje;
        this.puntajeMensual = puntajeMensual;
        this.puntajeHistorico = puntajeHistorico;
        this.usuario = usuario;
        this.amigo = amigo;
    }

    // Getters y setters
    public Long getIdPuntaje() { return idPuntaje; }
    public void setIdPuntaje(Long idPuntaje) { this.idPuntaje = idPuntaje; }

    public Long getPuntajeMensual() { return puntajeMensual; }
    public void setPuntajeMensual(Long puntajeMensual) { this.puntajeMensual = puntajeMensual; }

    public Long getPuntajeHistorico() { return puntajeHistorico; }
    public void setPuntajeHistorico(Long puntajeHistorico) { this.puntajeHistorico = puntajeHistorico; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Amigo getAmigo() {
        return amigo;
    }

    public void setAmigo(Amigo amigo) {
        this.amigo = amigo;
    }
}