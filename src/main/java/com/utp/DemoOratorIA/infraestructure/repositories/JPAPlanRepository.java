package com.utp.DemoOratorIA.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utp.DemoOratorIA.infraestructure.entities.PlanEntity;

@Repository
public interface JPAPlanRepository extends JpaRepository<PlanEntity, Integer> {

}