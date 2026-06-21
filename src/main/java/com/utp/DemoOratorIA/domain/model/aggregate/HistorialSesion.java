package com.utp.DemoOratorIA.domain.model.aggregate;

import java.time.LocalDateTime;

public class HistorialSesion {

    private Integer idHistorial;
    private Integer idUsuario;
    private String accion;
    private String descripcion;
    private String ipUsuario;
    private LocalDateTime fecha;

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIpUsuario() {
        return ipUsuario;
    }

    public void setIpUsuario(String ipUsuario) {
        this.ipUsuario = ipUsuario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    // Builder
    public static class Builder {

        private HistorialSesion historialSesion;

        public Builder() {
            this.historialSesion = new HistorialSesion();
        }

        public Builder idHistorial(Integer idHistorial) {
            this.historialSesion.idHistorial = idHistorial;
            return this;
        }

        public Builder idUsuario(Integer idUsuario) {
            this.historialSesion.idUsuario = idUsuario;
            return this;
        }

        public Builder accion(String accion) {
            this.historialSesion.accion = accion;
            return this;
        }

        public Builder descripcion(String descripcion) {
            this.historialSesion.descripcion = descripcion;
            return this;
        }

        public Builder ipUsuario(String ipUsuario) {
            this.historialSesion.ipUsuario = ipUsuario;
            return this;
        }

        public Builder fecha(LocalDateTime fecha) {
            this.historialSesion.fecha = fecha;
            return this;
        }

        public HistorialSesion build() {
            return this.historialSesion;
        }
    }
}