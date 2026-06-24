package com.utp.DemoOratorIA.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.DemoOratorIA.infraestructure.entities.NotificacionEntity;


public interface JPANotificacionRepository extends JpaRepository<NotificacionEntity, Integer> {

}