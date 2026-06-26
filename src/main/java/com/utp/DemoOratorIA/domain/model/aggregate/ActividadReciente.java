package com.utp.DemoOratorIA.domain.model.aggregate;

import java.time.LocalDateTime;

public class ActividadReciente {

    private Integer idActividad;
    private Integer idUsuario;
    private String actividad;
    private String estado;
    private LocalDateTime fecha;

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public static class Builder {
        private ActividadReciente ar;

        public Builder() {
            this.ar = new ActividadReciente();
        }

        public Builder idActividad(Integer idActividad) {
            this.ar.idActividad = idActividad;
            return this;
        }

        public Builder idUsuario(Integer idUsuario) {
            this.ar.idUsuario = idUsuario;
            return this;
        }

        public Builder actividad(String actividad) {
            this.ar.actividad = actividad;
            return this;
        }

        public Builder estado(String estado) {
            this.ar.estado = estado;
            return this;
        }

        public Builder fecha(LocalDateTime fecha) {
            this.ar.fecha = fecha;
            return this;
        }

        public ActividadReciente build() {
            return this.ar;
        }
    }
    
}
