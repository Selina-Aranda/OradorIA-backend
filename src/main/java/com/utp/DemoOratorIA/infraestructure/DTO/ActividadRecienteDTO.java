package com.utp.DemoOratorIA.infraestructure.DTO;

import java.time.LocalDateTime;

public record ActividadRecienteDTO(
        String usuario,
        String actividad,
        LocalDateTime fecha,
        String estado
) {}