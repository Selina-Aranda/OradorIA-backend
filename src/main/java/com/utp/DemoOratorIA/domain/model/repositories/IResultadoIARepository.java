package com.utp.DemoOratorIA.domain.model.repositories;

import java.util.Optional;

import com.utp.DemoOratorIA.domain.model.aggregate.ResultadoIA;

public interface IResultadoIARepository extends ICRUD<ResultadoIA> {

         Optional<ResultadoIA> findByIdAnalisis(Integer idAnalisis);
}