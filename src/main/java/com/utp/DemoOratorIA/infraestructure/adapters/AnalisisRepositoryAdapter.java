package com.utp.DemoOratorIA.infraestructure.adapters;

import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.domain.model.repositories.IAnalisisRepository;
import com.utp.DemoOratorIA.infraestructure.entities.AnalisisEntity;
import com.utp.DemoOratorIA.infraestructure.mappers.AnalisisMapper;
import com.utp.DemoOratorIA.infraestructure.repositories.JPAAnalisisRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AnalisisRepositoryAdapter implements IAnalisisRepository {

    private final JPAAnalisisRepository jpaRepository;
    private final AnalisisMapper mapper;

    public AnalisisRepositoryAdapter(JPAAnalisisRepository jpaRepository, AnalisisMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Analisis save(Analisis analisis) {
        AnalisisEntity entity = mapper.toEntity(analisis);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Analisis> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }
}
