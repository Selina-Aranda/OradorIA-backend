package com.utp.DemoOratorIA.infraestructure.repositories;

import java.util.List;

import com.utp.DemoOratorIA.infraestructure.entities.AnalisisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JPAAnalisisRepository extends JpaRepository<AnalisisEntity, Integer> {

    @Query(value = """
        SELECT estado, COUNT(*)
        FROM analisis
        GROUP BY estado
        """, nativeQuery = true)
    List<Object[]> contarPorEstado();

    @Query(value = """
        SELECT YEAR(fecha_analisis), MONTH(fecha_analisis), COUNT(*)
        FROM analisis
        GROUP BY YEAR(fecha_analisis), MONTH(fecha_analisis)
        ORDER BY 1, 2
        """, nativeQuery = true)
    List<Object[]> evolucionMensualGlobal();

    @Query(value = "SELECT COUNT(*) FROM analisis", nativeQuery = true)
    Long contarTotal();
}