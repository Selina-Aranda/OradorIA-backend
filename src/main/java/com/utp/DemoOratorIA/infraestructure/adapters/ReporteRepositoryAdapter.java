
package com.utp.DemoOratorIA.infraestructure.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.utp.DemoOratorIA.domain.model.aggregate.Reporte;
import com.utp.DemoOratorIA.domain.model.repositories.IReporteRepository;
import com.utp.DemoOratorIA.infraestructure.entities.ReporteEntity;
import com.utp.DemoOratorIA.infraestructure.mappers.ReporteMapper;
import com.utp.DemoOratorIA.infraestructure.repositories.JPAReporteRepository;

@Repository
public class ReporteRepositoryAdapter implements IReporteRepository {

    private final JPAReporteRepository jpa;
    private final ReporteMapper mapper;

    public ReporteRepositoryAdapter(
            JPAReporteRepository jpa,
            ReporteMapper mapper) {

        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public Reporte save(Reporte reporte) {

        ReporteEntity entity = mapper.toEntity(reporte);
        ReporteEntity savedEntity = jpa.save(entity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Reporte findById(Integer id) {

        ReporteEntity entity = jpa.findById(id).orElse(null);

        return entity != null
                ? mapper.toDomain(entity)
                : null;
    }

    @Override
    public Reporte update(Reporte reporte) {

        ReporteEntity entity = mapper.toEntity(reporte);
        ReporteEntity updatedEntity = jpa.save(entity);

        return mapper.toDomain(updatedEntity);
    }

    @Override
    public List<Reporte> list() {

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