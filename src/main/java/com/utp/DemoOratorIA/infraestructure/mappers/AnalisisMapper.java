package com.utp.DemoOratorIA.infraestructure.mappers;

import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.infraestructure.entities.AnalisisEntity;
import org.springframework.stereotype.Component;

@Component
public class AnalisisMapper {

    public Analisis toDomain(AnalisisEntity entity) {
        if (entity == null) return null;

        return new Analisis.Builder()
                .idAnalisis(entity.getIdAnalisis())
                .idUsuario(entity.getIdUsuario())
                .titulo(entity.getTitulo())
                .descripcion(entity.getDescripcion())
                .estado(entity.getEstado())
                .build();
    }

    public AnalisisEntity toEntity(Analisis domain){
        if (domain == null) return null;

        return AnalisisEntity.builder()
                .idAnalisis(domain.getIdAnalisis())
                .idUsuario(domain.getIdUsuario())
                .titulo(domain.getTitulo())
                .descripcion(domain.getDescripcion())
                .estado(domain.getEstado())
                .build();
    }
}
