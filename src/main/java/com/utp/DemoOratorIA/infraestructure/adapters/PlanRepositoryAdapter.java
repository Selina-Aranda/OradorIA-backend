package com.utp.DemoOratorIA.infraestructure.adapters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


import org.springframework.stereotype.Repository;

import com.utp.DemoOratorIA.domain.model.aggregate.Plan;
import com.utp.DemoOratorIA.domain.model.repositories.IPlanRepository;
import com.utp.DemoOratorIA.infraestructure.entities.PlanEntity;
import com.utp.DemoOratorIA.infraestructure.mappers.PlanMapper;
import com.utp.DemoOratorIA.infraestructure.repositories.JPAPlanRepository;

@Repository
public class PlanRepositoryAdapter implements IPlanRepository {

    private final JPAPlanRepository jpa;
    private final PlanMapper mapper;

    public PlanRepositoryAdapter(JPAPlanRepository jpa, PlanMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public Plan save(Plan plan) {
        PlanEntity entity = mapper.toEntity(plan);
        PlanEntity saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Plan> findById(Integer id) {
            return jpa.findById(id)
                    .map(mapper::toDomain);
    }

    @Override
    public Plan update(Plan plan) {
        PlanEntity entity = mapper.toEntity(plan);
        PlanEntity updated = jpa.save(entity);
        return mapper.toDomain(updated);
    }

    @Override
    public List<Plan> list() {
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