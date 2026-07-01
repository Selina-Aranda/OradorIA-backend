package com.utp.DemoOratorIA.infraestructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utp.DemoOratorIA.infraestructure.entities.ResultadoIAEntity;

public interface ResultadoQueryRepository extends JpaRepository<ResultadoIAEntity, Integer> {

    List<ResultadoIAEntity> findByIdAnalisis(Integer idAnalisis);

@Query(value = """
        SELECT
            a.id_analisis,
            a.titulo,
            a.estado,
            r.fecha_resultado,
            r.fluidez,
            r.postura
        FROM analisis a
        INNER JOIN resultados_ia r
            ON a.id_analisis = r.id_analisis
        WHERE a.id_usuario = :idUsuario
        ORDER BY r.fecha_resultado DESC
        """, nativeQuery = true)
    List<Object[]> listarAnalisisUsuario(Integer idUsuario);

@Query(value = """
    SELECT r.*
    FROM resultados_ia r
    INNER JOIN analisis a
        ON r.id_analisis = a.id_analisis
    WHERE a.id_usuario = :idUsuario
    """, nativeQuery = true)
List<ResultadoIAEntity> findByIdUsuario(Integer idUsuario);

@Query("""
    SELECT
        AVG(r.puntuacionGeneral),
        MAX(r.puntuacionGeneral)
    FROM AnalisisEntity a
    JOIN ResultadoIAEntity r
        ON a.idAnalisis = r.idAnalisis
    WHERE a.idUsuario = :idUsuario
""")
List<Object[]> obtenerPuntajesUsuario(@Param("idUsuario") Integer idUsuario);

}