package com.utp.DemoOratorIA.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.DemoOratorIA.infraestructure.entities.ResultadoIAEntity;

public interface JPAResultadoIARepository extends JpaRepository<ResultadoIAEntity, Integer> {
}