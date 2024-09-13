package Modelo;

import java.util.Calendar;
import java.util.Date;

public class Mensaje {
    private String texto;
    private Usuario remitente;
    private Date fechaEnvio;
    private Boolean leido;
    private Integer contador;

    public Mensaje(String texto, Usuario remitente) {
        this.texto = texto;
        this.remitente = remitente;
        Calendar calendario = Calendar.getInstance();
        fechaEnvio = calendario.getTime();
        leido = false;
    }

    public String getTexto() {
        return texto;
    }

    public Usuario getRemitente() {
        return remitente;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leer) {
        leido = leer;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

}
