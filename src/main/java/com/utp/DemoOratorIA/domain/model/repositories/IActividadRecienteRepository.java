package com.utp.DemoOratorIA.domain.model.repositories;

import com.utp.DemoOratorIA.domain.model.aggregate.ActividadReciente;

public interface IActividadRecienteRepository extends ICRUD<ActividadReciente> {

    void deleteByUsuarioId(Integer idUsuario);

}
