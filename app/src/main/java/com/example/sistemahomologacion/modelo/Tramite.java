package com.example.sistemahomologacion.modelo;

public class Tramite {

    private int idTramite;
    private String registro;
    private String estado;
    private String tipo;
    private String nombreT;
    private String apellidoT;
    private int cedulaT;
    private String emailT;

    public Tramite() {
    }

    public int getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(int idTramite) {
        this.idTramite = idTramite;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreT() {
        return nombreT;
    }

    public void setNombreT(String nombreT) {
        this.nombreT = nombreT;
    }

    public String getApellidoT() {
        return apellidoT;
    }

    public void setApellidoT(String apellidoT) {
        this.apellidoT = apellidoT;
    }

    public int getCedulaT() {
        return cedulaT;
    }

    public void setCedulaT(int cedulaT) {
        this.cedulaT = cedulaT;
    }

    public String getEmailT() {
        return emailT;
    }

    public void setEmailT(String emailT) {
        this.emailT = emailT;
    }
}
