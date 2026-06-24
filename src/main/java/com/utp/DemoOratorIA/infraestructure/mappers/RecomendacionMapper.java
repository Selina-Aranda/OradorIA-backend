package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.Recomendacion;
import com.utp.DemoOratorIA.infraestructure.entities.RecomendacionEntity;

@Component
public class RecomendacionMapper {

    public Recomendacion toDomain(RecomendacionEntity recomendacionEntity) {
        if (recomendacionEntity == null) return null;

        return new Recomendacion.Builder()
                .idRecomendacion(recomendacionEntity.getIdRecomendacion())
                .idResultado(recomendacionEntity.getIdResultado())
                .categoria(recomendacionEntity.getCategoria())
                .descripcion(recomendacionEntity.getDescripcion())
                .prioridad(recomendacionEntity.getPrioridad())
                .titulo(recomendacionEntity.getTitulo())
                .build();
    }

    public RecomendacionEntity toEntity(Recomendacion rec) {
        if (rec == null) return null;

        return RecomendacionEntity.builder()
                .idRecomendacion(rec.getIdRecomendacion())
                .idResultado(rec.getIdResultado())
                .categoria(rec.getCategoria())
                .descripcion(rec.getDescripcion())
                .prioridad(rec.getPrioridad())
                .titulo(rec.getTitulo())
                .build();
    }
}