package com.utp.DemoOratorIA.infraestructure.adapters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.utp.DemoOratorIA.domain.model.aggregate.ActividadReciente;
import com.utp.DemoOratorIA.domain.model.repositories.IActividadRecienteRepository;
import com.utp.DemoOratorIA.infraestructure.entities.ActividadRecienteEntity;
import com.utp.DemoOratorIA.infraestructure.mappers.ActividadRecienteMapper;
import com.utp.DemoOratorIA.infraestructure.repositories.JPAActividadRecienteRepository;

@Repository
public class ActividadRecienteRepositoryAdapter implements IActividadRecienteRepository {

        private final JPAActividadRecienteRepository jpa;
        private final ActividadRecienteMapper mapper;

        public ActividadRecienteRepositoryAdapter(JPAActividadRecienteRepository jpa, ActividadRecienteMapper mapper) {
            this.jpa = jpa;
            this.mapper = mapper;
        }

    @Override
    public ActividadReciente save(ActividadReciente actividadReciente) {
        ActividadRecienteEntity actividadrecienteEntity = mapper.toEntity(actividadReciente);
        ActividadRecienteEntity savedEntity = jpa.save(actividadrecienteEntity);
            return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<ActividadReciente> findById(Integer id) {
        return jpa.findById(id)
            .map(mapper::toDomain);
    }

    @Override
    public ActividadReciente update(ActividadReciente t) {
        ActividadRecienteEntity actividadrecienteEntity = mapper.toEntity(t);
        ActividadRecienteEntity updatedEntity = jpa.save(actividadrecienteEntity);
        return mapper.toDomain(updatedEntity);

    }

    @Override
    public List<ActividadReciente> list() {
        return jpa.findAll().stream()
                    .map(mapper::toDomain)
                    .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        jpa.deleteById(id);
    }

    @Override
    public void deleteByUsuarioId(Integer idUsuario) {
        jpa.deleteByIdUsuario(idUsuario);
    }


}
