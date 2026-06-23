package com.utp.DemoOratorIA.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utp.DemoOratorIA.infraestructure.entities.PagoEntity;

@Repository
public interface JPAPagoRepository extends JpaRepository<PagoEntity, Integer> {

}