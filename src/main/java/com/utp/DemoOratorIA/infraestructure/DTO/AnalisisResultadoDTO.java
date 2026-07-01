package com.utp.DemoOratorIA.infraestructure.DTO;

import java.time.LocalDateTime;

public record AnalisisResultadoDTO(
        Integer idAnalisis,
        String titulo,
        String estado,
        LocalDateTime fecha,
        Double fluidez,
        Double postura) {
}