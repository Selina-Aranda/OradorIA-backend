package com.utp.DemoOratorIA.infraestructure.adapters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.utp.DemoOratorIA.domain.model.aggregate.Pago;
import com.utp.DemoOratorIA.domain.model.repositories.IPagoRepository;
import com.utp.DemoOratorIA.infraestructure.entities.PagoEntity;
import com.utp.DemoOratorIA.infraestructure.mappers.PagoMapper;
import com.utp.DemoOratorIA.infraestructure.repositories.JPAPagoRepository;

@Repository
public class PagoRepositoryAdapter implements IPagoRepository {

    private final JPAPagoRepository jpa;
    private final PagoMapper mapper;

    public PagoRepositoryAdapter(
            JPAPagoRepository jpa,
            PagoMapper mapper) {

        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public Pago save(Pago pago) {

        PagoEntity entity = mapper.toEntity(pago);
        PagoEntity saved = jpa.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Pago> findById(Integer id) {
            return jpa.findById(id)
                    .map(mapper::toDomain);
    }

    @Override
    public Pago update(Pago pago) {

        PagoEntity entity = mapper.toEntity(pago);
        PagoEntity updated = jpa.save(entity);

        return mapper.toDomain(updated);
    }

    @Override
    public List<Pago> list() {

        return jpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        jpa.deleteById(id);
    }

    @Override
    public Double obtenerIngresosMensuales(int anio, int mes) {
        return jpa.obtenerIngresosMensuales(anio, mes);
    }
}