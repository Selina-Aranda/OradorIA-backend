package com.utp.DemoOratorIA.infraestructure.DTO;

import java.time.LocalDate;

import com.utp.DemoOratorIA.domain.model.enums.SuscripcionesStatus;

public record SuscripcionesDTO(
        Integer idSuscripcion,
        Integer idUsuario,
        Integer idPlan,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        SuscripcionesStatus estado,
        Boolean renovacionAutomatica) {
}