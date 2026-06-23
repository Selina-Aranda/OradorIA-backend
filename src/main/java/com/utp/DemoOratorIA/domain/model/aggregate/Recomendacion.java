package com.utp.DemoOratorIA.domain.model.aggregate;

public class Recomendacion {

    private Integer idRecomendacion;
    private String titulo;
    private String descripcion;

    public Integer getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(Integer idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
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

    // BUILDER
    public static class Builder {

        private Recomendacion rec = new Recomendacion();

        public Builder idRecomendacion(Integer idRecomendacion) {
            rec.idRecomendacion = idRecomendacion;
            return this;
        }

        public Builder titulo(String titulo) {
            rec.titulo = titulo;
            return this;
        }

        public Builder descripcion(String descripcion) {
            rec.descripcion = descripcion;
            return this;
        }

        public Recomendacion build() {
            return rec;
        }
    }
}