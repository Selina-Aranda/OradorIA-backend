package com.utp.DemoOratorIA.infraestructure.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.DemoOratorIA.infraestructure.entities.ActividadRecienteEntity;


public interface JPAActividadRecienteRepository extends JpaRepository<ActividadRecienteEntity, Integer>  {

    void deleteByIdUsuario(Integer idUsuario);
}
