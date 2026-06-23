
package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.Reporte;
import com.utp.DemoOratorIA.infraestructure.entities.ReporteEntity;

@Component
public class ReporteMapper {

    public Reporte toDomain(ReporteEntity entity) {

        if (entity == null) return null;

        return new Reporte.Builder()
                .idReporte(entity.getIdReporte())
                .idUsuario(entity.getIdUsuario())
                .titulo(entity.getTitulo())
                .descripcion(entity.getDescripcion())
                .tipo(entity.getTipo())
                .archivoUrl(entity.getArchivoUrl())
                .fechaGeneracion(entity.getFechaGeneracion())
                .build();
    }

    public ReporteEntity toEntity(Reporte reporte) {

        if (reporte == null) return null;

        return ReporteEntity.builder()
                .idReporte(reporte.getIdReporte())
                .idUsuario(reporte.getIdUsuario())
                .titulo(reporte.getTitulo())
                .descripcion(reporte.getDescripcion())
                .tipo(reporte.getTipo())
                .archivoUrl(reporte.getArchivoUrl())
                .fechaGeneracion(reporte.getFechaGeneracion())
                .build();
    }
}