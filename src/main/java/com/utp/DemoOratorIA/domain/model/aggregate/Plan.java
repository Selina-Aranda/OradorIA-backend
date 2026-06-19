package com.utp.DemoOratorIA.domain.model.aggregate;

public class Plan {

    private Integer idPlan;
    private String nombre;
    private Double precio;
    private Integer practicasMensuales;
    private Boolean analisisTiempoReal;
    private Boolean reportesAvanzados;
    private Boolean gestionEquipos;
    private String descripcion;

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getPracticasMensuales() {
        return practicasMensuales;
    }

    public void setPracticasMensuales(Integer practicasMensuales) {
        this.practicasMensuales = practicasMensuales;
    }

    public Boolean getAnalisisTiempoReal() {
        return analisisTiempoReal;
    }

    public void setAnalisisTiempoReal(Boolean analisisTiempoReal) {
        this.analisisTiempoReal = analisisTiempoReal;
    }

    public Boolean getReportesAvanzados() {
        return reportesAvanzados;
    }

    public void setReportesAvanzados(Boolean reportesAvanzados) {
        this.reportesAvanzados = reportesAvanzados;
    }

    public Boolean getGestionEquipos() {
        return gestionEquipos;
    }

    public void setGestionEquipos(Boolean gestionEquipos) {
        this.gestionEquipos = gestionEquipos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // BUILDER
    public static class Builder {

        private Plan plan = new Plan();

        public Builder idPlan(Integer idPlan) {
            plan.idPlan = idPlan;
            return this;
        }

        public Builder nombre(String nombre) {
            plan.nombre = nombre;
            return this;
        }

        public Builder precio(Double precio) {
            plan.precio = precio;
            return this;
        }

        public Builder practicasMensuales(Integer practicasMensuales) {
            plan.practicasMensuales = practicasMensuales;
            return this;
        }

        public Builder analisisTiempoReal(Boolean analisisTiempoReal) {
            plan.analisisTiempoReal = analisisTiempoReal;
            return this;
        }

        public Builder reportesAvanzados(Boolean reportesAvanzados) {
            plan.reportesAvanzados = reportesAvanzados;
            return this;
        }

        public Builder gestionEquipos(Boolean gestionEquipos) {
            plan.gestionEquipos = gestionEquipos;
            return this;
        }

        public Builder descripcion(String descripcion) {
            plan.descripcion = descripcion;
            return this;
        }

        public Plan build() {
            return plan;
        }
    }
}