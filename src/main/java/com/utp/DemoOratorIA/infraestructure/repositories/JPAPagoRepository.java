package com.utp.DemoOratorIA.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.utp.DemoOratorIA.infraestructure.entities.PagoEntity;


public interface JPAPagoRepository extends JpaRepository<PagoEntity, Integer> {

    @Query("""
        SELECT COALESCE(SUM(p.monto),0)
        FROM PagoEntity p
        WHERE p.estado = 'PAGADO'
        AND YEAR(p.fechaPago) = :anio
        AND MONTH(p.fechaPago) = :mes
    """)
    Double obtenerIngresosMensuales(
            @Param("anio") int anio,
            @Param("mes") int mes);
}