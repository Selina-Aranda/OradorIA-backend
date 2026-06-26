package com.utp.DemoOratorIA.infraestructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.DemoOratorIA.infraestructure.entities.UserEntity;

public interface JPAUserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);
    Optional<UserEntity> findById(Integer id);

    //Metodo paraa contar usuarios
    long countByIdRol(Integer idRol);

    //Mostrar cantidad de usuarios premiun
    long countByIdPlan(Integer idPlan);
}
