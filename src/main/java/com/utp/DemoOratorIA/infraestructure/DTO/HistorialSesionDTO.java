package com.utp.DemoOratorIA.infraestructure.DTO;

import java.time.LocalDateTime;

public record HistorialSesionDTO(
        Integer idHistorial,
        Integer idUsuario,
        String accion,
        String descripcion,
        String ipUsuario,
        LocalDateTime fecha) {
}