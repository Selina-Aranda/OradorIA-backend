package com.utp.DemoOratorIA.infraestructure.DTO;

import java.time.LocalDateTime;

public record ResultadoIADTO(
        Integer idResultado,
        String entradaUsuario,
        String respuestaIA,
        Double puntuacion,
        LocalDateTime fecha
) {
}