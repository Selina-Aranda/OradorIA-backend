package com.utp.DemoOratorIA.domain.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.DemoOratorIA.infraestructure.entities.ResultadoIAEntity;

public interface ResultadoQueryRepository extends JpaRepository<ResultadoIAEntity, Integer> {

    List<ResultadoIAEntity> findByIdAnalisis(Integer idAnalisis);
}