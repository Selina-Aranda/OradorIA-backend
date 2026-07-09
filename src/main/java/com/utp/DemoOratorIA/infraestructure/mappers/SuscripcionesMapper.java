package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.Suscripciones;
import com.utp.DemoOratorIA.infraestructure.entities.SuscripcionesEntity;

@Component
public class SuscripcionesMapper {

    public SuscripcionesEntity toEntity(Suscripciones s) {
        SuscripcionesEntity e = new SuscripcionesEntity();

        e.setIdSuscripcion(s.getIdSuscripcion());
        e.setIdUsuario(s.getIdUsuario());
        e.setIdPlan(s.getIdPlan());
        e.setFechaInicio(s.getFechaInicio());
        e.setFechaFin(s.getFechaFin());
        e.setEstado(s.getEstado());
        e.setRenovacionAutomatica(s.getRenovacionAutomatica());

        return e;
    }

    public Suscripciones toDomain(SuscripcionesEntity e) {
        Suscripciones s = new Suscripciones();

        s.setIdSuscripcion(e.getIdSuscripcion());
        s.setIdUsuario(e.getIdUsuario());
        s.setIdPlan(e.getIdPlan());
        s.setFechaInicio(e.getFechaInicio());
        s.setFechaFin(e.getFechaFin());
        s.setEstado(e.getEstado());
        s.setRenovacionAutomatica(e.getRenovacionAutomatica());

        return s;
    }
}