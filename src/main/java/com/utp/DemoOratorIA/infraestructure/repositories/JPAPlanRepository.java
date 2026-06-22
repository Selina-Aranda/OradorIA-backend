package com.utp.DemoOratorIA.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utp.DemoOratorIA.infraestructure.entities.PlanEntity;

public interface JPAPlanRepository extends JpaRepository<PlanEntity, Integer> {

}