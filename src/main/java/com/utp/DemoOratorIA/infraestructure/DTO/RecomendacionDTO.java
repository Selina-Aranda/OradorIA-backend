package com.utp.DemoOratorIA.infraestructure.DTO;

public record RecomendacionDTO(
        Integer idRecomendacion,
        Integer idResultado,
        String titulo,
        String descripcion
) {
}