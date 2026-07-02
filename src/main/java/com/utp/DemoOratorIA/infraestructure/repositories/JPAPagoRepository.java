package com.utp.DemoOratorIA.infraestructure.repositories;

import java.util.List;

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

    @Query(value = """
        SELECT COALESCE(SUM(monto),0)
        FROM pagos
        WHERE estado = 'PAGADO'
        """, nativeQuery = true)
    Double ingresosTotales();

    @Query(value = """
        SELECT YEAR(fecha_pago), MONTH(fecha_pago), COALESCE(SUM(monto),0)
        FROM pagos
        WHERE estado = 'PAGADO'
        GROUP BY YEAR(fecha_pago), MONTH(fecha_pago)
        ORDER BY 1, 2
        """, nativeQuery = true)
    List<Object[]> ingresosPorMes();

    @Query(value = """
        SELECT pl.nombre, COALESCE(SUM(pg.monto),0)
        FROM pagos pg
        INNER JOIN suscripciones s ON pg.id_suscripcion = s.id_suscripcion
        INNER JOIN planes pl ON s.id_plan = pl.id_plan
        WHERE pg.estado = 'PAGADO'
        GROUP BY pl.nombre
        """, nativeQuery = true)
    List<Object[]> ingresosPorPlan();

    @Query(value = """
        SELECT metodo_pago, COUNT(*)
        FROM pagos
        WHERE estado = 'PAGADO'
        GROUP BY metodo_pago
        """, nativeQuery = true)
    List<Object[]> pagosPorMetodo();

    @Query(value = """
        SELECT estado, COUNT(*)
        FROM pagos
        GROUP BY estado
        """, nativeQuery = true)
    List<Object[]> pagosPorEstado();
}