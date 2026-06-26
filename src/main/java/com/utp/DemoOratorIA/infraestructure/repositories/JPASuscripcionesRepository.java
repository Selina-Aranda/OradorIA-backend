package com.utp.DemoOratorIA.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.DemoOratorIA.infraestructure.entities.SuscripcionesEntity;

public interface JPASuscripcionesRepository
        extends JpaRepository<SuscripcionesEntity, Integer> {
}