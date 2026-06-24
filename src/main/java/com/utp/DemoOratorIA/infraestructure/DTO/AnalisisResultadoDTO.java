package com.utp.DemoOratorIA.infraestructure.DTO;

import java.time.LocalDateTime;

public record AnalisisResultadoDTO(
        Integer idUsuario,
        String titulo,
        String estado,
        LocalDateTime fecha,
        Double fluidez,
        Double postura) {
}