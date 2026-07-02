package com.utp.DemoOratorIA.infraestructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.DemoOratorIA.infraestructure.entities.ResultadoIAEntity;

public interface JPAResultadoIARepository extends JpaRepository<ResultadoIAEntity, Integer> {
    Optional<ResultadoIAEntity> findByIdAnalisis(Integer idAnalisis);

    // Último resultado registrado
    ResultadoIAEntity findTopByOrderByFechaResultadoDesc();

    // Resultado con mayor puntaje
    ResultadoIAEntity findTopByOrderByPuntuacionGeneralDesc();

}