package com.utp.DemoOratorIA.infraestructure.adapters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


import org.springframework.stereotype.Repository;

import com.utp.DemoOratorIA.domain.model.aggregate.Notificacion;
import com.utp.DemoOratorIA.domain.model.repositories.INotificacionRepository;
import com.utp.DemoOratorIA.infraestructure.entities.NotificacionEntity;
import com.utp.DemoOratorIA.infraestructure.mappers.NotificacionMapper;
import com.utp.DemoOratorIA.infraestructure.repositories.JPANotificacionRepository;

@Repository
public class NotificacionRepositoryAdapter implements INotificacionRepository {

    private final JPANotificacionRepository jpa;
    private final NotificacionMapper mapper;

    public NotificacionRepositoryAdapter(
            JPANotificacionRepository jpa,
            NotificacionMapper mapper) {

        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public Notificacion save(Notificacion notificacion) {

        NotificacionEntity entity = mapper.toEntity(notificacion);
        NotificacionEntity savedEntity = jpa.save(entity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Notificacion> findById(Integer id) {

            return jpa.findById(id)
                    .map(mapper::toDomain);
    }

    @Override
    public Notificacion update(Notificacion notificacion) {

        NotificacionEntity entity = mapper.toEntity(notificacion);
        NotificacionEntity updatedEntity = jpa.save(entity);

        return mapper.toDomain(updatedEntity);
    }

    @Override
    public List<Notificacion> list() {

        return jpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        jpa.deleteById(id);
    }
}
