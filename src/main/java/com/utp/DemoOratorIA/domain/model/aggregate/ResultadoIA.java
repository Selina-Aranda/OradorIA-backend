package com.utp.DemoOratorIA.domain.model.aggregate;

import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.ResultsLevel;

public class ResultadoIA {

    private Integer idResultado;
    private Integer idAnalisis;

    private Double fluidez;
    private Double claridad;
    private Double volumen;
    private Double velocidad;
    private Double postura;
    private Double contactoVisual;
    private Double confianza;
    private Double expresionFacial;

    private Integer muletillasDetectadas;
    private Integer pausasIncomodas;

    private Double puntuacionGeneral;
    private ResultsLevel nivel;

    private String observaciones;
    private LocalDateTime fechaResultado;

    private String entradaUsuario;
    private String respuestaIA;
    private Double puntuacion;
    private LocalDateTime fecha;

    // GETTERS

    public Integer getIdResultado() {
        return idResultado;
    }

    public Integer getIdAnalisis() {
        return idAnalisis;
    }

    public Double getFluidez() {
        return fluidez;
    }

    public Double getClaridad() {
        return claridad;
    }

    public Double getVolumen() {
        return volumen;
    }

    public Double getVelocidad() {
        return velocidad;
    }

    public Double getPostura() {
        return postura;
    }

    public Double getContactoVisual() {
        return contactoVisual;
    }

    public Double getConfianza() {
        return confianza;
    }

    public Double getExpresionFacial() {
        return expresionFacial;
    }

    public Integer getMuletillasDetectadas() {
        return muletillasDetectadas;
    }

    public Integer getPausasIncomodas() {
        return pausasIncomodas;
    }

    public Double getPuntuacionGeneral() {
        return puntuacionGeneral;
    }

    public ResultsLevel getNivel() {
        return nivel;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public LocalDateTime getFechaResultado() {
        return fechaResultado;
    }

    public String getEntradaUsuario() {
        return entradaUsuario;
    }

    public String getRespuestaIA() {
        return respuestaIA;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    // SETTERS

    public void setIdResultado(Integer idResultado) {
        this.idResultado = idResultado;
    }

    public void setIdAnalisis(Integer idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public void setFluidez(Double fluidez) {
        this.fluidez = fluidez;
    }

    public void setClaridad(Double claridad) {
        this.claridad = claridad;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }

    public void setPostura(Double postura) {
        this.postura = postura;
    }

    public void setContactoVisual(Double contactoVisual) {
        this.contactoVisual = contactoVisual;
    }

    public void setConfianza(Double confianza) {
        this.confianza = confianza;
    }

    public void setExpresionFacial(Double expresionFacial) {
        this.expresionFacial = expresionFacial;
    }

    public void setMuletillasDetectadas(Integer muletillasDetectadas) {
        this.muletillasDetectadas = muletillasDetectadas;
    }

    public void setPausasIncomodas(Integer pausasIncomodas) {
        this.pausasIncomodas = pausasIncomodas;
    }

    public void setPuntuacionGeneral(Double puntuacionGeneral) {
        this.puntuacionGeneral = puntuacionGeneral;
    }

    public void setNivel(ResultsLevel nivel) {
        this.nivel = nivel;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setFechaResultado(LocalDateTime fechaResultado) {
        this.fechaResultado = fechaResultado;
    }

    public void setEntradaUsuario(String entradaUsuario) {
        this.entradaUsuario = entradaUsuario;
    }

    public void setRespuestaIA(String respuestaIA) {
        this.respuestaIA = respuestaIA;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    // BUILDER

    public static class Builder {

        private ResultadoIA resultadoia;

        public Builder() {
            this.resultadoia = new ResultadoIA();
        }

        public Builder idResultado(Integer idResultado) {
            this.resultadoia.idResultado = idResultado;
            return this;
        }

        public Builder idAnalisis(Integer idAnalisis) {
            this.resultadoia.idAnalisis = idAnalisis;
            return this;
        }

        public Builder fluidez(Double fluidez) {
            this.resultadoia.fluidez = fluidez;
            return this;
        }

        public Builder claridad(Double claridad) {
            this.resultadoia.claridad = claridad;
            return this;
        }

        public Builder volumen(Double volumen) {
            this.resultadoia.volumen = volumen;
            return this;
        }

        public Builder velocidad(Double velocidad) {
            this.resultadoia.velocidad = velocidad;
            return this;
        }

        public Builder postura(Double postura) {
            this.resultadoia.postura = postura;
            return this;
        }

        public Builder contactoVisual(Double contactoVisual) {
            this.resultadoia.contactoVisual = contactoVisual;
            return this;
        }

        public Builder confianza(Double confianza) {
            this.resultadoia.confianza = confianza;
            return this;
        }

        public Builder expresionFacial(Double expresionFacial) {
            this.resultadoia.expresionFacial = expresionFacial;
            return this;
        }

        public Builder muletillasDetectadas(Integer muletillasDetectadas) {
            this.resultadoia.muletillasDetectadas = muletillasDetectadas;
            return this;
        }

        public Builder pausasIncomodas(Integer pausasIncomodas) {
            this.resultadoia.pausasIncomodas = pausasIncomodas;
            return this;
        }

        public Builder puntuacionGeneral(Double puntuacionGeneral) {
            this.resultadoia.puntuacionGeneral = puntuacionGeneral;
            return this;
        }

        public Builder nivel(ResultsLevel nivel) {
            this.resultadoia.nivel = nivel;
            return this;
        }

        public Builder observaciones(String observaciones) {
            this.resultadoia.observaciones = observaciones;
            return this;
        }

        public Builder fechaResultado(LocalDateTime fechaResultado) {
            this.resultadoia.fechaResultado = fechaResultado;
            return this;
        }

        public Builder entradaUsuario(String entradaUsuario) {
            this.resultadoia.entradaUsuario = entradaUsuario;
            return this;
        }

        public Builder respuestaIA(String respuestaIA) {
            this.resultadoia.respuestaIA = respuestaIA;
            return this;
        }

        public Builder puntuacion(Double puntuacion) {
            this.resultadoia.puntuacion = puntuacion;
            return this;
        }

        public Builder fecha(LocalDateTime fecha) {
            this.resultadoia.fecha = fecha;
            return this;
        }

        public ResultadoIA build() {
            return resultadoia;
        }
    }
}