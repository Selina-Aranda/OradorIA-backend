package com.utp.DemoOratorIA.domain.model.repositories;

import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;

import java.util.Optional;

public interface IAnalisisRepository {

    Analisis save(Analisis analisis);
    Optional<Analisis> findById(Long id);
}
