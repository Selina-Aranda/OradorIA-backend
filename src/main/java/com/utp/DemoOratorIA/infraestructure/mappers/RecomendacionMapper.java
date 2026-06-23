package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.Recomendacion;
import com.utp.DemoOratorIA.infraestructure.entities.RecomendacionEntity;

@Component
public class RecomendacionMapper {

    public Recomendacion toDomain(RecomendacionEntity entity) {
        if (entity == null) return null;

        return new Recomendacion.Builder()
                .idRecomendacion(entity.getIdRecomendacion())
                .titulo(entity.getTitulo())
                .descripcion(entity.getDescripcion())
                .build();
    }

    public RecomendacionEntity toEntity(Recomendacion rec) {
        if (rec == null) return null;

        return RecomendacionEntity.builder()
                .idRecomendacion(rec.getIdRecomendacion())
                .titulo(rec.getTitulo())
                .descripcion(rec.getDescripcion())
                .build();
    }
}