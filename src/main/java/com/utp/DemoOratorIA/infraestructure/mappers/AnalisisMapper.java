package com.utp.DemoOratorIA.infraestructure.mappers;

import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.infraestructure.entities.AnalisisEntity;
import com.utp.DemoOratorIA.infraestructure.DTO.AnalisisDTO;
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

    public AnalisisDTO toDTO(Analisis domain){
        if (domain == null) return null;

        AnalisisDTO dto = new AnalisisDTO();
        dto.setIdAnalisis(domain.getIdAnalisis());
        dto.setIdUsuario(domain.getIdUsuario());
        dto.setTitulo(domain.getTitulo());
        dto.setDescripcion(domain.getDescripcion());
        dto.setEstado(domain.getEstado());
        return dto;
    }
}
