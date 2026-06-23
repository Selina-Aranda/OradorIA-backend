package com.utp.DemoOratorIA.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.DemoOratorIA.infraestructure.entities.HistorialSesionEntity;

public interface JPAHistorialSesionRepository extends JpaRepository<HistorialSesionEntity, Integer> {
}