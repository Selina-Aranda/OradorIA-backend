package com.utp.DemoOratorIA.infraestructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.utp.DemoOratorIA.infraestructure.entities.UserEntity;

public interface JPAUserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);
    Optional<UserEntity> findById(Integer id);

    //Metodo paraa contar usuarios
    long countByIdRol(Integer idRol);

    //Mostrar cantidad de usuarios premiun
    long countByIdPlan(Integer idPlan);

    @Query(value = """
        SELECT estado, COUNT(*)
        FROM usuarios
        GROUP BY estado
        """, nativeQuery = true)
    List<Object[]> contarPorEstado();

    @Query(value = """
        SELECT p.nombre, COUNT(*)
        FROM usuarios u
        LEFT JOIN planes p ON u.id_plan = p.id_plan
        GROUP BY p.nombre
        """, nativeQuery = true)
    List<Object[]> contarPorPlan();

    @Query(value = """
        SELECT YEAR(fecha_registro), MONTH(fecha_registro), COUNT(*)
        FROM usuarios
        WHERE fecha_registro IS NOT NULL
        GROUP BY YEAR(fecha_registro), MONTH(fecha_registro)
        ORDER BY 1, 2
        """, nativeQuery = true)
    List<Object[]> registrosPorMes();

    @Query(value = """
        SELECT COUNT(*)
        FROM usuarios
        WHERE YEAR(fecha_registro) = YEAR(CURDATE())
        AND MONTH(fecha_registro) = MONTH(CURDATE())
        """, nativeQuery = true)
    Long contarNuevosEsteMes();
}
