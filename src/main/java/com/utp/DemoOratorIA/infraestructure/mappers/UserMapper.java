package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.User;
import com.utp.DemoOratorIA.infraestructure.entities.UserEntity;

@Component
public class UserMapper {
    
    public User toDomain(UserEntity userEntity) {
        return new User.Builder()
                .idUsuario(userEntity.getId())
                .idRol(userEntity.getIdRol())
                .idPlan(userEntity.getIdPlan())
                .nombres(userEntity.getNombre())
                .apellidos(userEntity.getApellidos())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .telefono(userEntity.getTelefono())
                .fotoPerfil(userEntity.getFotoPerfil())
                .estado(userEntity.getEstado())
                .fechaRegistro(userEntity.getFechaRegistro())
                .ultimoLogin(userEntity.getUltimoLogin())
                .build();
    }

    public UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getIdUsuario())
                .idRol(user.getIdRol())
                .idPlan(user.getIdPlan())
                .nombre(user.getNombres())
                .apellidos(user.getApellidos())
                .email(user.getEmail())
                .password(user.getPassword())
                .telefono(user.getTelefono())
                .fotoPerfil(user.getFotoPerfil())
                .estado(user.getEstado())
                .fechaRegistro(user.getFechaRegistro())
                .ultimoLogin(user.getUltimoLogin())
                .build();
    }

}
