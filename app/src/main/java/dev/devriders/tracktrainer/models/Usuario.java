package dev.devriders.tracktrainer.models;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String nickname;
    private String correo;
    private String contraseña;
    private String verificationCode;
    private boolean verified;
    private String fechaCreacion;
    private String resetCode;
    private int intentosFallidos;
    private boolean bloqueado;
    private boolean eliminado;
    private String fechaEliminacion;
    private String quienElimino;
    private String fechaActualizacion;
    private String quienActualizo;

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getVerificationCode() { return verificationCode; }
    public void setVerificationCode(String verificationCode) { this.verificationCode = verificationCode; }

    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getResetCode() { return resetCode; }
    public void setResetCode(String resetCode) { this.resetCode = resetCode; }

    public int getIntentosFallidos() { return intentosFallidos; }
    public void setIntentosFallidos(int intentosFallidos) { this.intentosFallidos = intentosFallidos; }

    public boolean isBloqueado() { return bloqueado; }
    public void setBloqueado(boolean bloqueado) { this.bloqueado = bloqueado; }

    public boolean isEliminado() { return eliminado; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }

    public String getFechaEliminacion() { return fechaEliminacion; }
    public void setFechaEliminacion(String fechaEliminacion) { this.fechaEliminacion = fechaEliminacion; }

    public String getQuienElimino() { return quienElimino; }
    public void setQuienElimino(String quienElimino) { this.quienElimino = quienElimino; }

    public String getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(String fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    public String getQuienActualizo() { return quienActualizo; }
    public void setQuienActualizo(String quienActualizo) { this.quienActualizo = quienActualizo; }
}
