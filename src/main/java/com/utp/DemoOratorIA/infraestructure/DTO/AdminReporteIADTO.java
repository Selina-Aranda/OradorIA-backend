
package com.utp.DemoOratorIA.infraestructure.DTO;

import java.util.List;

public record AdminReporteIADTO(
    Long totalAnalisis,
    Double promedioPuntuacion,
    Double promedioFluidez,
    Double promedioClaridad,
    Double promedioConfianza,
    Double promedioMuletillas,
    List<ConteoDTO> porEstado,
    List<ConteoDTO> porNivel,
    List<ConteoDTO> evolucionMensual,
    List<ConteoDTO> topMuletillas
) {
}