
package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.Notificacion;
import com.utp.DemoOratorIA.infraestructure.entities.NotificacionEntity;

@Component
public class NotificacionMapper {

    public Notificacion toDomain(NotificacionEntity entity) {

        if (entity == null) return null;

        return new Notificacion.Builder()
                .idNotificacion(entity.getIdNotificacion())
                .idUsuario(entity.getIdUsuario())
                .titulo(entity.getTitulo())
                .mensaje(entity.getMensaje())
                .tipo(entity.getTipo())
                .leido(entity.getLeido())
                .fecha(entity.getFecha())
                .build();
    }

    public NotificacionEntity toEntity(Notificacion notificacion) {

        if (notificacion == null) return null;

        return NotificacionEntity.builder()
                .idNotificacion(notificacion.getIdNotificacion())
                .idUsuario(notificacion.getIdUsuario())
                .titulo(notificacion.getTitulo())
                .mensaje(notificacion.getMensaje())
                .tipo(notificacion.getTipo())
                .leido(notificacion.getLeido())
                .fecha(notificacion.getFecha())
                .build();
    }
}