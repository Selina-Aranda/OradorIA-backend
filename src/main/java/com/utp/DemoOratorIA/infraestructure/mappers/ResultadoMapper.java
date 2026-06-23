package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.ResultadoIA;
import com.utp.DemoOratorIA.infraestructure.entities.ResultadoIAEntity;

@Component
public class ResultadoMapper {

    public ResultadoIA toDomain(ResultadoIAEntity entity) {
        if (entity == null) return null;

        return new ResultadoIA.Builder()
                .idResultado(entity.getIdResultado())
                .entradaUsuario(entity.getEntradaUsuario())
                .respuestaIA(entity.getRespuestaIA())
                .puntuacion(entity.getPuntuacion())
                .fecha(entity.getFecha())
                .build();
    }

    public ResultadoIAEntity toEntity(ResultadoIA res) {
        if (res == null) return null;

        return ResultadoIAEntity.builder()
                .idResultado(res.getIdResultado())
                .entradaUsuario(res.getEntradaUsuario())
                .respuestaIA(res.getRespuestaIA())
                .puntuacion(res.getPuntuacion())
                .fecha(res.getFecha())
                .build();
    }
}