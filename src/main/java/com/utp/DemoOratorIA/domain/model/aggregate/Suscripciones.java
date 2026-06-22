package com.utp.DemoOratorIA.domain.model.aggregate;

import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.SuscripcionesStatus;
import com.utp.DemoOratorIA.domain.model.enums.UserStatus;

public class Suscripciones {
    private Integer idSuscripcion;
    private Integer idUsuario;
    private Integer idPlan;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private SuscripcionesStatus estado;
    private Boolean renovacionAutomatica;

    public Integer getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Integer idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public SuscripcionesStatus getEstado() {
        return estado;
    }

    public void setEstado(SuscripcionesStatus estado) {
        this.estado = estado;
    }

    public Boolean getRenovacionAutomatica() {
        return renovacionAutomatica;
    }

    public void setRenovacionAutomatica(Boolean renovacionAutomatica) {
        this.renovacionAutomatica = renovacionAutomatica;
    }

    public static class Builder {
        private Suscripciones suscripciones;

        public Builder() {
            this.suscripciones = new Suscripciones();
        }

        public Builder idSuscripcion(Integer idSuscripcion){
            this.suscripciones.idSuscripcion = idSuscripcion;
            return this;
        }

        public Builder idUsuario(Integer idUsuario) {
            this.suscripciones.idUsuario = idUsuario;
            return this;
        }

        public Builder idPlan(Integer idPlan) {
            this.suscripciones.idPlan=idPlan;
            return this;
        }

        public Builder fechaInicio(LocalDateTime fechaInicio) {
            this.suscripciones.fechaInicio=fechaInicio;
            return this;
        }

        public Builder fechaFin(LocalDateTime fechaFin) {
            this.suscripciones.fechaFin=fechaFin;
            return this;
        }

        public Builder estado(SuscripcionesStatus estado) {
            this.suscripciones.estado=estado;
            return this;
        } 

        public Builder renovacionAutomatica(Boolean renovacionAutomatica) {
            this.suscripciones.renovacionAutomatica=renovacionAutomatica;
            return this;
        }

        public Suscripciones build() {
            return this.suscripciones;
        }
    }
    
}
