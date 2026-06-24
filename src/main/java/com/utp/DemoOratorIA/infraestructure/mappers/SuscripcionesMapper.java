package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.Suscripciones;
import com.utp.DemoOratorIA.infraestructure.entities.SuscripcionesEntity;

@Component
public class SuscripcionesMapper {
    
    public Suscripciones toDomain(SuscripcionesEntity suscripcionesEntity){
        return new Suscripciones.Builder()
            .idSuscripcion(suscripcionesEntity.getIdSuscripciones())
            .idUsuario(suscripcionesEntity.getIdUsuario())
            .idPlan(suscripcionesEntity.getIdPlan())
            .fechaInicio(suscripcionesEntity.getFechaInicio())
            .fechaFin(suscripcionesEntity.getFechaFin())
            .estado(suscripcionesEntity.getEstado())
            .renovacionAutomatica(suscripcionesEntity.getRenovacionAutomatica())
            .build();

    }

    public SuscripcionesEntity toEntity(Suscripciones suscripciones){
       return SuscripcionesEntity.builder()
            .idSuscripciones(suscripciones.getIdSuscripcion())
            .idUsuario(suscripciones.getIdUsuario())
            .idPlan(suscripciones.getIdPlan())
            .fechaInicio(suscripciones.getFechaInicio())
            .fechaFin(suscripciones.getFechaFin())
            .estado(suscripciones.getEstado())
            .renovacionAutomatica(suscripciones.getRenovacionAutomatica())
            .build();


    }
}
