package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.Plan;
import com.utp.DemoOratorIA.infraestructure.entities.PlanEntity;

@Component
public class PlanMapper {

    public Plan toDomain(PlanEntity entity) {
        if (entity == null)
            return null;

        return new Plan.Builder()
                .idPlan(entity.getIdPlan())
                .nombre(entity.getNombre())
                .precio(entity.getPrecio())
                .practicasMensuales(entity.getPracticasMensuales())
                .analisisTiempoReal(entity.getAnalisisTiempoReal())
                .reportesAvanzados(entity.getReportesAvanzados())
                .gestionEquipos(entity.getGestionEquipos())
                .descripcion(entity.getDescripcion())
                .build();
    }

    public PlanEntity toEntity(Plan plan) {
        if (plan == null)
            return null;

        return PlanEntity.builder()
                .idPlan(plan.getIdPlan())
                .nombre(plan.getNombre())
                .precio(plan.getPrecio())
                .practicasMensuales(plan.getPracticasMensuales())
                .analisisTiempoReal(plan.getAnalisisTiempoReal())
                .reportesAvanzados(plan.getReportesAvanzados())
                .gestionEquipos(plan.getGestionEquipos())
                .descripcion(plan.getDescripcion())
                .build();
    }
}