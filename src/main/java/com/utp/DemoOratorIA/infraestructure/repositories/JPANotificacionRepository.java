package com.utp.DemoOratorIA.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utp.DemoOratorIA.infraestructure.entities.NotificacionEntity;

@Repository
public interface JPANotificacionRepository extends JpaRepository<NotificacionEntity, Integer> {

}