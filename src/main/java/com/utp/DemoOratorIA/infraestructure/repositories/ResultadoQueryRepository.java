package com.utp.DemoOratorIA.infraestructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utp.DemoOratorIA.infraestructure.DTO.ReporteMensualDTO;
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

@Query(value = """
    SELECT
        YEAR(a.fecha_analisis) AS anio,
        MONTH(a.fecha_analisis) AS mes,
        COUNT(DISTINCT a.id_analisis) AS total_analisis,
        AVG(r.puntuacion_general) AS promedio_puntaje,
        AVG(r.fluidez) AS promedio_fluidez,
        AVG(r.claridad) AS promedio_claridad,
        AVG(r.confianza) AS promedio_confianza,
        AVG(r.muletillas_detectadas) AS promedio_muletillas,
        SUM(a.duracion_segundos) / 60 AS duracion_total_minutos
    FROM analisis a
    INNER JOIN resultados_ia r
        ON a.id_analisis = r.id_analisis
    WHERE a.id_usuario = :idUsuario
    GROUP BY YEAR(a.fecha_analisis), MONTH(a.fecha_analisis)
    ORDER BY anio ASC, mes ASC
    """, nativeQuery = true)
List<Object[]> obtenerEvolucionMensual(@Param("idUsuario") Integer idUsuario);

    @Query("""
    SELECT new com.utp.DemoOratorIA.infraestructure.DTO.ReporteMensualDTO(

        YEAR(r.fechaResultado),
        MONTH(r.fechaResultado),
        '',
        CAST(COUNT(r) AS integer),
        AVG(r.puntuacionGeneral),
        AVG(r.fluidez),
        AVG(r.claridad),
        AVG(r.confianza),
        AVG(r.muletillasDetectadas),
        SUM(a.duracionSegundos) / 60.0

    )
    FROM AnalisisEntity a
    JOIN ResultadoIAEntity r
        ON a.idAnalisis = r.idAnalisis
    WHERE a.idUsuario = :idUsuario
    GROUP BY
        YEAR(r.fechaResultado),
        MONTH(r.fechaResultado)
    ORDER BY
        YEAR(r.fechaResultado),
        MONTH(r.fechaResultado)
    """)
List<ReporteMensualDTO> obtenerReporteMensual(@Param("idUsuario") Integer idUsuario);

@Query(value = """
    SELECT
        AVG(puntuacion_general),
        AVG(fluidez),
        AVG(claridad),
        AVG(confianza),
        AVG(muletillas_detectadas),
        COUNT(*)
    FROM resultados_ia
    """, nativeQuery = true)
List<Object[]> promediosGlobales();

@Query(value = """
    SELECT nivel, COUNT(*)
    FROM resultados_ia
    WHERE nivel IS NOT NULL
    GROUP BY nivel
    """, nativeQuery = true)
List<Object[]> contarPorNivel();

@Query(value = """
    SELECT palabra, SUM(cantidad) AS total
    FROM muletillas_detectadas
    GROUP BY palabra
    ORDER BY total DESC
    LIMIT 6
    """, nativeQuery = true)
List<Object[]> topMuletillas();

}