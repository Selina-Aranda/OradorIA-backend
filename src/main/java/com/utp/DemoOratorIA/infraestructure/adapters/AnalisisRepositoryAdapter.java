package com.utp.DemoOratorIA.infraestructure.adapters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.domain.model.repositories.IAnalisisRepository;
import com.utp.DemoOratorIA.infraestructure.entities.AnalisisEntity;
import com.utp.DemoOratorIA.infraestructure.mappers.AnalisisMapper;
import com.utp.DemoOratorIA.infraestructure.repositories.JPAAnalisisRepository;

@Repository
public class AnalisisRepositoryAdapter implements IAnalisisRepository {

    private final JPAAnalisisRepository jpa;
    private final AnalisisMapper mapper;

    public AnalisisRepositoryAdapter(JPAAnalisisRepository jpa, AnalisisMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public Analisis save(Analisis analisis) {
        AnalisisEntity analisisEntity = mapper.toEntity(analisis);
        AnalisisEntity savedEntity = jpa.save(analisisEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Analisis> findById(Integer id) {
        return jpa.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Analisis update(Analisis analisis) {
        AnalisisEntity analisisEntity = mapper.toEntity(analisis);
        AnalisisEntity updatedEntity = jpa.save(analisisEntity);
        return mapper.toDomain(updatedEntity);
    }

    @Override
    public List<Analisis> list() {
        return jpa.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        jpa.deleteById(id);
    }

}
