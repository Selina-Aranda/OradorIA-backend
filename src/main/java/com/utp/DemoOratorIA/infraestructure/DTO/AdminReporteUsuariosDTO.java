
package com.utp.DemoOratorIA.infraestructure.DTO;

import java.util.List;

public record AdminReporteUsuariosDTO(
    Long totalUsuarios,
    Long usuariosActivos,
    Long usuariosNuevosEsteMes,
    List<ConteoDTO> porEstado,
    List<ConteoDTO> porPlan,
    List<ConteoDTO> registrosPorMes
) {
}