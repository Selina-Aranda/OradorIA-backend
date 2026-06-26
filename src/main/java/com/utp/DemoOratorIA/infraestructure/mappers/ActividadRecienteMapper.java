package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.ActividadReciente;
import com.utp.DemoOratorIA.infraestructure.DTO.ActividadRecienteDTO;
import com.utp.DemoOratorIA.infraestructure.entities.ActividadRecienteEntity;


@Component
public class ActividadRecienteMapper {
  public ActividadReciente toDomain(ActividadRecienteEntity arEntity) {
        return new ActividadReciente.Builder()
                .idActividad(arEntity.getIdActividad())
                .idUsuario(arEntity.getIdUsuario())
                .actividad(arEntity.getActividad())
                .estado(arEntity.getEstado())
                .fecha(arEntity.getFecha())
                .build();
    }

    public ActividadRecienteEntity toEntity(ActividadReciente ar) {
        return ActividadRecienteEntity.builder()
                .idActividad(ar.getIdActividad())
                .idUsuario(ar.getIdUsuario())
                .actividad(ar.getActividad())
                .estado(ar.getEstado())
                .fecha(ar.getFecha())
                .build();
    }

    // NUEVO
    public ActividadRecienteDTO toDTO(ActividadReciente actividad, String usuario) {
        return new ActividadRecienteDTO(
                usuario,
                actividad.getActividad(),
                actividad.getFecha(),
                actividad.getEstado()
        );
    }
}
