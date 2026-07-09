package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.infraestructure.entities.AnalisisEntity;

@Component
public class AnalisisMapper {

    public AnalisisEntity toEntity(Analisis analisisEntity) {
        return AnalisisEntity.builder()
                .idAnalisis(analisisEntity.getIdAnalisis())
                .idUsuario(analisisEntity.getIdUsuario())
                .titulo(analisisEntity.getTitulo())
                .descripcion(analisisEntity.getDescripcion())
                .fechaAnalisis(analisisEntity.getFechaAnalisis())
                .duracionSegundos(analisisEntity.getDuracionSegundos())
                .videoUrl(analisisEntity.getVideoUrl())
                .audioUrl(analisisEntity.getAudioUrl())
                .textoTranscrito(analisisEntity.getTextoTranscrito())
                .estado(analisisEntity.getEstado())
                .build();
    }

    public Analisis toDomain(AnalisisEntity analisis) {
        return new Analisis.Builder()
                .idAnalisis(analisis.getIdAnalisis())
                .idUsuario(analisis.getIdUsuario())
                .titulo(analisis.getTitulo())
                .descripcion(analisis.getDescripcion())
                .estado(analisis.getEstado())
                .fechaAnalisis(analisis.getFechaAnalisis())
                .duracionSegundos(analisis.getDuracionSegundos())
                .videoUrl(analisis.getVideoUrl())
                .audioUrl(analisis.getAudioUrl())
                .textoTranscrito(analisis.getTextoTranscrito())
                .build();
    }

}
