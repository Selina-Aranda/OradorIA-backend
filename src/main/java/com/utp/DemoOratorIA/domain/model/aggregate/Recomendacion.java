package com.utp.DemoOratorIA.domain.model.aggregate;

import com.utp.DemoOratorIA.domain.model.enums.CategoriesRecommends;
import com.utp.DemoOratorIA.domain.model.enums.PriorityRecommends;

public class Recomendacion {

    private Integer idRecomendacion;
    private Integer idResultado;
    private CategoriesRecommends categoria;
    private String descripcion;
    private PriorityRecommends prioridad;
    private String titulo;

    public Integer getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(Integer idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public Integer getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(Integer idResultado) {
        this.idResultado = idResultado;
    }

    public CategoriesRecommends getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriesRecommends categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PriorityRecommends getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PriorityRecommends prioridad) {
        this.prioridad = prioridad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // BUILDER

    public static class Builder {

        private Recomendacion rec;

        public Builder() {
            this.rec = new Recomendacion();
        }

        public Builder idRecomendacion(Integer idRecomendacion) {
            rec.idRecomendacion = idRecomendacion;
            return this;
        }

        public Builder idResultado(Integer idResultado) {
            rec.idResultado = idResultado;
            return this;
        }

        public Builder categoria(CategoriesRecommends categoria) {
            rec.categoria = categoria;
            return this;
        }

        public Builder descripcion(String descripcion) {
            rec.descripcion = descripcion;
            return this;
        }

        public Builder prioridad(PriorityRecommends prioridad) {
            rec.prioridad = prioridad;
            return this;
        }

        public Builder titulo(String titulo) {
            rec.titulo = titulo;
            return this;
        }

        public Recomendacion build() {
            return rec;
        }
    }
}