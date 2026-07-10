package com.utp.DemoOratorIA.domain.model.repositories;

import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;

import java.util.List;

public interface IAnalisisRepository extends ICRUD<Analisis>{
    List<Analisis> findByUsuario(Integer idUsuario);
}
