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
    SELECT
        YEAR(r.fechaResultado),
        MONTH(r.fechaResultado),
        COUNT(r),
        AVG(r.puntuacionGeneral),
        AVG(r.fluidez),
        AVG(r.claridad),
        AVG(r.confianza),
        AVG(r.muletillasDetectadas),
        SUM(a.duracionSegundos) / 60.0
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
    List<Object[]> obtenerReporteMensualDatos(@Param("idUsuario") Integer idUsuario);

    default List<ReporteMensualDTO> obtenerReporteMensual(Integer idUsuario) {
        List<Object[]> datos = obtenerReporteMensualDatos(idUsuario);
        return datos.stream().map(fila -> {
            Integer anio = fila[0] != null ? ((Number) fila[0]).intValue() : 0;
            Integer mes = fila[1] != null ? ((Number) fila[1]).intValue() : 1;
            Integer totalAnalisis = fila[2] != null ? ((Number) fila[2]).intValue() : 0;
            Double puntaje = fila[3] != null ? ((Number) fila[3]).doubleValue() : 0.0;
            Double fluidez = fila[4] != null ? ((Number) fila[4]).doubleValue() : 0.0;
            Double claridad = fila[5] != null ? ((Number) fila[5]).doubleValue() : 0.0;
            Double confianza = fila[6] != null ? ((Number) fila[6]).doubleValue() : 0.0;
            Double muletillas = fila[7] != null ? ((Number) fila[7]).doubleValue() : 0.0;
            Double duracion = fila[8] != null ? ((Number) fila[8]).doubleValue() : 0.0;

            return new ReporteMensualDTO(
                    anio,
                    mes,
                    "",
                    totalAnalisis,
                    puntaje,
                    fluidez,
                    claridad,
                    confianza,
                    muletillas,
                    duracion
            );
        }).toList();
    }

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
    SELECT r.nivel
    FROM resultados_ia r
    INNER JOIN analisis a
        ON a.id_analisis = r.id_analisis
    WHERE a.id_usuario = :idUsuario
    ORDER BY r.fecha_resultado DESC
    LIMIT 1
    """, nativeQuery = true)
String obtenerNivel(Integer idUsuario);



//NUEVO QUERY
@Query(value = """
    SELECT r.nivel, COUNT(*) AS total
    FROM resultados_ia r
    GROUP BY r.nivel
    ORDER BY total DESC
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