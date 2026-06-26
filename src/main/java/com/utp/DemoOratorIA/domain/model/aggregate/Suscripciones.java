package com.utp.DemoOratorIA.domain.model.aggregate;

import java.time.LocalDate;

import com.utp.DemoOratorIA.domain.model.enums.SuscripcionesStatus;

public class Suscripciones {

    private Integer idSuscripcion;
    private Integer idUsuario;
    private Integer idPlan;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
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
}