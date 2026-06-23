package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.HistorialSesion;
import com.utp.DemoOratorIA.infraestructure.entities.HistorialSesionEntity;

@Component
public class HistorialSesionMapper {

    public HistorialSesion toDomain(HistorialSesionEntity entity) {

        return new HistorialSesion.Builder()
                .idHistorial(entity.getIdHistorial())
                .idUsuario(entity.getIdUsuario())
                .accion(entity.getAccion())
                .descripcion(entity.getDescripcion())
                .ipUsuario(entity.getIpUsuario())
                .fecha(entity.getFecha())
                .build();
    }

    public HistorialSesionEntity toEntity(HistorialSesion historial) {

        return HistorialSesionEntity.builder()
                .idHistorial(historial.getIdHistorial())
                .idUsuario(historial.getIdUsuario())
                .accion(historial.getAccion())
                .descripcion(historial.getDescripcion())
                .ipUsuario(historial.getIpUsuario())
                .fecha(historial.getFecha())
                .build();
    }
}