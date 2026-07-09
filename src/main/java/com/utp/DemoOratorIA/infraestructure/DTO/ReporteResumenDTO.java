
package com.utp.DemoOratorIA.infraestructure.DTO;

import java.util.List;

public record ReporteResumenDTO(

    Double promedioGeneral,
    String mejoraTotal,
    Double promedioMuletillas,
    Integer totalSesiones,
    String mejoraFluidez,
    String mejoraClaridad,
    String mejoraConfianza,
    String reduccionMuletillas,
    List<ReporteMensualDTO> datosMensuales

) {

}