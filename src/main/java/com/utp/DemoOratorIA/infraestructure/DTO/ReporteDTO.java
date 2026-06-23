
package com.utp.DemoOratorIA.infraestructure.DTO;

import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.ReportType;

public record ReporteDTO(

    Integer idReporte,
    Integer idUsuario,
    String titulo,
    String descripcion,
    ReportType tipo,
    String archivoUrl,
    LocalDateTime fechaGeneracion

) {

}