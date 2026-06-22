package com.utp.DemoOratorIA.infraestructure.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.utp.DemoOratorIA.domain.model.aggregate.ResultadoIA;
import com.utp.DemoOratorIA.domain.model.repositories.IResultadoIARepository;
import com.utp.DemoOratorIA.infraestructure.entities.ResultadoIAEntity;
import com.utp.DemoOratorIA.infraestructure.mappers.ResultadoMapper;
import com.utp.DemoOratorIA.infraestructure.repositories.JPAResultadoIARepository;

@Repository
public class ResultadoIARepositoryAdapter implements IResultadoIARepository {

    private final JPAResultadoIARepository jpa;
    private final ResultadoMapper mapper;

    public ResultadoIARepositoryAdapter(JPAResultadoIARepository jpa,
                                        ResultadoMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public ResultadoIA save(ResultadoIA res) {
        ResultadoIAEntity entity = mapper.toEntity(res);
        ResultadoIAEntity saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public ResultadoIA findById(Integer id) {
        ResultadoIAEntity entity = jpa.findById(id).orElse(null);
        return entity != null ? mapper.toDomain(entity) : null;
    }

    @Override
    public ResultadoIA update(ResultadoIA res) {
        ResultadoIAEntity entity = mapper.toEntity(res);
        ResultadoIAEntity updated = jpa.save(entity);
        return mapper.toDomain(updated);
    }

    @Override
    public List<ResultadoIA> list() {
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