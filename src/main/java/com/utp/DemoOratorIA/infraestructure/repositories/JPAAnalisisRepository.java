package com.utp.DemoOratorIA.infraestructure.repositories;

import com.utp.DemoOratorIA.infraestructure.entities.AnalisisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAAnalisisRepository extends JpaRepository<AnalisisEntity, Long> {
}