package com.utp.DemoOratorIA.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.DemoOratorIA.infraestructure.entities.RecomendacionEntity;

public interface JPARecomendacionRepository extends JpaRepository<RecomendacionEntity, Integer> {
}