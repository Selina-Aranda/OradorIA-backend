package com.utp.DemoOratorIA.infraestructure.adapters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.utp.DemoOratorIA.domain.model.aggregate.Suscripciones;
import com.utp.DemoOratorIA.domain.model.repositories.ISuscripcionesRepository;
import com.utp.DemoOratorIA.infraestructure.entities.SuscripcionesEntity;
import com.utp.DemoOratorIA.infraestructure.mappers.SuscripcionesMapper;
import com.utp.DemoOratorIA.infraestructure.repositories.JPASuscripcionesRepository;

@Repository
public class SuscripcionesRepositoryAdapter implements ISuscripcionesRepository{

    private final JPASuscripcionesRepository jpa;
    private final SuscripcionesMapper mapper;

    public SuscripcionesRepositoryAdapter(JPASuscripcionesRepository jpa, SuscripcionesMapper mapper){
        this.jpa=jpa;
        this.mapper=mapper;
    }

    @Override
    public Suscripciones save(Suscripciones suscripciones) {
        SuscripcionesEntity suscripcionesEntity = mapper.toEntity(suscripciones);
        SuscripcionesEntity savedEntity = jpa.save(suscripcionesEntity);
            return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Suscripciones> findById(Integer id) {
            return jpa.findById(id)
                    .map(mapper::toDomain);
    }

    @Override
    public Suscripciones update(Suscripciones t) {
        SuscripcionesEntity suscripcionesEntity = mapper.toEntity(t);
        SuscripcionesEntity updatedEntity = jpa.save(suscripcionesEntity);
        return mapper.toDomain(updatedEntity);
    }

    @Override
    public List<Suscripciones> list() {
        return jpa.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        jpa.deleteById(id);
    }
    
}
