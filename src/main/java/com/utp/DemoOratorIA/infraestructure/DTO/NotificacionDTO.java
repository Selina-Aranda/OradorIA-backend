
package com.utp.DemoOratorIA.infraestructure.DTO;

import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.NotificationType;

public record NotificacionDTO(

    Integer idNotificacion,
    Integer idUsuario,
    String titulo,
    String mensaje,
    NotificationType tipo,
    Boolean leido,
    LocalDateTime fecha

) {

}