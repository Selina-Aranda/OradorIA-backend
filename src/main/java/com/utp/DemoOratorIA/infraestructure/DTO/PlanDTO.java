package com.utp.DemoOratorIA.infraestructure.DTO;

public record PlanDTO(
        Integer idPlan,
        String nombre,
        Double precio,
        Integer practicasMensuales,
        Boolean analisisTiempoReal,
        Boolean reportesAvanzados,
        Boolean gestionEquipos,
        String descripcion) {
}