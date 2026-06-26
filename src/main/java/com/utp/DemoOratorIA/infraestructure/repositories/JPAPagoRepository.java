package com.utp.DemoOratorIA.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utp.DemoOratorIA.infraestructure.entities.PagoEntity;


public interface JPAPagoRepository extends JpaRepository<PagoEntity, Integer> {

}