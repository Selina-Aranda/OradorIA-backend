package com.utp.DemoOratorIA.infraestructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.utp.DemoOratorIA.infraestructure.entities.SuscripcionesEntity;

public interface JPASuscripcionesRepository
        extends JpaRepository<SuscripcionesEntity, Integer> {

    @Query(value = """
        SELECT estado, COUNT(*)
        FROM suscripciones
        GROUP BY estado
        """, nativeQuery = true)
    List<Object[]> contarPorEstado();

    @Query(value = "SELECT COUNT(*) FROM suscripciones WHERE estado = 'ACTIVA'", nativeQuery = true)
    Long contarActivas();
}