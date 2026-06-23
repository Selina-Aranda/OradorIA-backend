package com.utp.DemoOratorIA.infraestructure.DTO;

public record SuscripcionesDTO(
    Integer idPlan,
    String nombre,
    Double precio,
    Integer practicasMensuales,
    Boolean analisisTiempoReal,
    Boolean reportesAvanzados,
    Boolean gestionEquipos,
    String descripcion
) {
    
}
