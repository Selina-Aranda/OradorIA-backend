package com.utp.DemoOratorIA.domain.model.aggregate;

import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.AnalysisStatus;

public class Analisis {

    private Integer idAnalisis;
    private Integer idUsuario;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaAnalisis;
    private Integer duracionSegundos;
    private String videoUrl;
    private String audioUrl;
    private String textoTranscrito;
    private AnalysisStatus estado;

    // Getters

    public Integer getIdAnalisis() {
        return idAnalisis;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaAnalisis() {
        return fechaAnalisis;
    }

    public Integer getDuracionSegundos() {
        return duracionSegundos;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public String getTextoTranscrito() {
        return textoTranscrito;
    }

    public AnalysisStatus getEstado() {
        return estado;
    }

    // Setters

    public void setIdAnalisis(Integer idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
  
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaAnalisis(LocalDateTime fechaAnalisis) {
        this.fechaAnalisis = fechaAnalisis;
    }

    public void setDuracionSegundos(Integer duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public void setTextoTranscrito(String textoTranscrito) {
        this.textoTranscrito = textoTranscrito;
    }

    public void setEstado(AnalysisStatus estado) {
        this.estado = estado;
    }

    // Builder

    public static class Builder {

        private Analisis analisis;

        public Builder() {
            this.analisis = new Analisis();
        }

        public Builder idAnalisis(Integer idAnalisis) {
            this.analisis.idAnalisis = idAnalisis;
            return this;
        }

        public Builder idUsuario(Integer idUsuario) {
            this.analisis.idUsuario = idUsuario;
            return this;
        }

        public Builder titulo(String titulo) {
            this.analisis.titulo = titulo;
            return this;
        }

        public Builder descripcion(String descripcion) {
            this.analisis.descripcion = descripcion;
            return this;
        }

        public Builder fechaAnalisis(LocalDateTime fechaAnalisis) {
            this.analisis.fechaAnalisis = fechaAnalisis;
            return this;
        }

        public Builder duracionSegundos(Integer duracionSegundos) {
            this.analisis.duracionSegundos = duracionSegundos;
            return this;
        }

        public Builder videoUrl(String videoUrl) {
            this.analisis.videoUrl = videoUrl;
            return this;
        }

        public Builder audioUrl(String audioUrl) {
            this.analisis.audioUrl = audioUrl;
            return this;
        }

        public Builder textoTranscrito(String textoTranscrito) {
            this.analisis.textoTranscrito = textoTranscrito;
            return this;
        }

        public Builder estado(AnalysisStatus estado) {
            this.analisis.estado = estado;
            return this;
        }

        public Analisis build() {
            return this.analisis;
        }
    }
}