package com.utp.DemoOratorIA.domain.model.aggregate;

import java.time.LocalDateTime;

public class ResultadoIA {

    private Integer idResultado;
    private String entradaUsuario;
    private String respuestaIA;
    private Double puntuacion;
    private LocalDateTime fecha;

    public Integer getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(Integer idResultado) {
        this.idResultado = idResultado;
    }

    public String getEntradaUsuario() {
        return entradaUsuario;
    }

    public void setEntradaUsuario(String entradaUsuario) {
        this.entradaUsuario = entradaUsuario;
    }

    public String getRespuestaIA() {
        return respuestaIA;
    }

    public void setRespuestaIA(String respuestaIA) {
        this.respuestaIA = respuestaIA;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    // BUILDER
    public static class Builder {

        private ResultadoIA obj = new ResultadoIA();

        public Builder idResultado(Integer idResultado) {
            obj.idResultado = idResultado;
            return this;
        }

        public Builder entradaUsuario(String entradaUsuario) {
            obj.entradaUsuario = entradaUsuario;
            return this;
        }

        public Builder respuestaIA(String respuestaIA) {
            obj.respuestaIA = respuestaIA;
            return this;
        }

        public Builder puntuacion(Double puntuacion) {
            obj.puntuacion = puntuacion;
            return this;
        }

        public Builder fecha(LocalDateTime fecha) {
            obj.fecha = fecha;
            return this;
        }

        public ResultadoIA build() {
            return obj;
        }
    }
}