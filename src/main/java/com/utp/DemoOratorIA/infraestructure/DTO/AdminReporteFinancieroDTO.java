package com.utp.DemoOratorIA.infraestructure.DTO;

import java.util.List;

public record AdminReporteFinancieroDTO(
    Double ingresosTotales,
    Double ingresosEsteMes,
    Long suscripcionesActivas,
    List<MontoDTO> ingresosPorMes,
    List<MontoDTO> ingresosPorPlan,
    List<ConteoDTO> pagosPorMetodo,
    List<ConteoDTO> pagosPorEstado,
    List<ConteoDTO> suscripcionesPorEstado
) {
}
