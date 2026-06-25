package com.utp.DemoOratorIA.infraestructure.adapters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


import org.springframework.stereotype.Repository;

import com.utp.DemoOratorIA.domain.model.aggregate.Recomendacion;
import com.utp.DemoOratorIA.domain.model.repositories.IRecomendacionRepository;
import com.utp.DemoOratorIA.infraestructure.entities.RecomendacionEntity;
import com.utp.DemoOratorIA.infraestructure.mappers.RecomendacionMapper;
import com.utp.DemoOratorIA.infraestructure.repositories.JPARecomendacionRepository;

@Repository
public class RecomendacionRepositoryAdapter implements IRecomendacionRepository {

    private final JPARecomendacionRepository jpa;
    private final RecomendacionMapper mapper;

    public RecomendacionRepositoryAdapter(JPARecomendacionRepository jpa,
                                          RecomendacionMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public Recomendacion save(Recomendacion rec) {
        RecomendacionEntity entity = mapper.toEntity(rec);
        RecomendacionEntity saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Recomendacion> findById(Integer id) {
            return jpa.findById(id)
                    .map(mapper::toDomain);
    }

    @Override
    public Recomendacion update(Recomendacion rec) {
        RecomendacionEntity entity = mapper.toEntity(rec);
        RecomendacionEntity updated = jpa.save(entity);
        return mapper.toDomain(updated);
    }

    @Override
    public List<Recomendacion> list() {
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