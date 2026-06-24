
package com.utp.DemoOratorIA.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.DemoOratorIA.infraestructure.entities.ReporteEntity;

public interface JPAReporteRepository extends JpaRepository<ReporteEntity, Integer> {

}