package com.utp.DemoOratorIA.domain.model.aggregate;

import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.ReportType;

public class Reporte {

    private Integer idReporte;
    private Integer idUsuario;
    private String titulo;
    private String descripcion;
    private ReportType tipo;
    private String archivoUrl;
    private LocalDateTime fechaGeneracion;

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ReportType getTipo() {
        return tipo;
    }

    public void setTipo(ReportType tipo) {
        this.tipo = tipo;
    }

    public String getArchivoUrl() {
        return archivoUrl;
    }

    public void setArchivoUrl(String archivoUrl) {
        this.archivoUrl = archivoUrl;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    // BUILDER
    public static class Builder {

        private Reporte reporte;

        public Builder() {
            this.reporte = new Reporte();
        }

        public Builder idReporte(Integer idReporte) {
            this.reporte.idReporte = idReporte;
            return this;
        }

        public Builder idUsuario(Integer idUsuario) {
            this.reporte.idUsuario = idUsuario;
            return this;
        }

        public Builder titulo(String titulo) {
            this.reporte.titulo = titulo;
            return this;
        }

        public Builder descripcion(String descripcion) {
            this.reporte.descripcion = descripcion;
            return this;
        }

        public Builder tipo(ReportType tipo) {
            this.reporte.tipo = tipo;
            return this;
        }

        public Builder archivoUrl(String archivoUrl) {
            this.reporte.archivoUrl = archivoUrl;
            return this;
        }

        public Builder fechaGeneracion(LocalDateTime fechaGeneracion) {
            this.reporte.fechaGeneracion = fechaGeneracion;
            return this;
        }

        public Reporte build() {
            return this.reporte;
        }
    }
}