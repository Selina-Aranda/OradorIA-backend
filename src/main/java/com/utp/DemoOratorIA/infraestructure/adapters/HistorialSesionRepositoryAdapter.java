package com.utp.DemoOratorIA.infraestructure.adapters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


import org.springframework.stereotype.Repository;

import com.utp.DemoOratorIA.domain.model.aggregate.HistorialSesion;
import com.utp.DemoOratorIA.domain.model.repositories.IHistorialSesionRepository;
import com.utp.DemoOratorIA.infraestructure.entities.HistorialSesionEntity;
import com.utp.DemoOratorIA.infraestructure.mappers.HistorialSesionMapper;
import com.utp.DemoOratorIA.infraestructure.repositories.JPAHistorialSesionRepository;

@Repository
public class HistorialSesionRepositoryAdapter implements IHistorialSesionRepository {

    private final JPAHistorialSesionRepository jpa;
    private final HistorialSesionMapper mapper;

    public HistorialSesionRepositoryAdapter(
            JPAHistorialSesionRepository jpa,
            HistorialSesionMapper mapper) {

        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public HistorialSesion save(HistorialSesion historialSesion) {

        HistorialSesionEntity entity = mapper.toEntity(historialSesion);

        HistorialSesionEntity saved = jpa.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Optional<HistorialSesion> findById(Integer id) {
            return jpa.findById(id)
                    .map(mapper::toDomain);
    }

    @Override
    public HistorialSesion update(HistorialSesion historialSesion) {

        HistorialSesionEntity entity = mapper.toEntity(historialSesion);

        HistorialSesionEntity updated = jpa.save(entity);

        return mapper.toDomain(updated);
    }

    @Override
    public List<HistorialSesion> list() {

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