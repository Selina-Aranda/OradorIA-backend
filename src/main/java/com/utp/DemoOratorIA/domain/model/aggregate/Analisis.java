package com.utp.DemoOratorIA.domain.model.aggregate;

import com.utp.DemoOratorIA.domain.model.enums.AnalysisStatus;

public class Analisis {
    private final Long idAnalisis;
    private final Long idUsuario;
    private final String titulo;
    private final String descripcion;
    private final AnalysisStatus estado;

    private Analisis(Builder builder) {
        this.idAnalisis = builder.idAnalisis;
        this.idUsuario = builder.idUsuario;
        this.titulo = builder.titulo;
        this.descripcion = builder.descripcion;
        this.estado = builder.estado;
    }

    public Long getIdAnalisis() { return idAnalisis; }
    public Long getIdUsuario() { return idUsuario; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public AnalysisStatus getEstado() { return estado; }

    public static class Builder {
        private Long idAnalisis;
        private Long idUsuario;
        private String titulo;
        private String descripcion;
        private AnalysisStatus estado;

        public Builder idAnalisis(Long idAnalisis) {
            this.idAnalisis = idAnalisis;
            return this;
        }

        public Builder idUsuario(Long idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder estado(AnalysisStatus estado) {
            this.estado = estado;
            return this;
        }

        public Analisis build() {
            return new Analisis(this);
        }
    }
}
