package com.utp.DemoOratorIA.infraestructure.DTO;

import java.time.LocalDateTime;

public record ResultadoIADTO(
        Integer idResultado,
        Integer idAnalisis,

        Double fluidez,
        Double claridad,
        Double volumen,
        Double velocidad,
        Double postura,
        Double contactoVisual,
        Double confianza,
        Double expresionFacial,

        Integer muletillasDetectadas,
        Integer pausasIncomodas,

        Double puntuacionGeneral,

        String observaciones,
        LocalDateTime fechaResultado,

        String entradaUsuario,
        String respuestaIA,
        Double puntuacion,
        LocalDateTime fecha
) {
}