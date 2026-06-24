package com.utp.DemoOratorIA.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.Suscripciones;
import com.utp.DemoOratorIA.domain.model.repositories.ISuscripcionesRepository;


@Service
public class SuscripcionesService {

    private final ISuscripcionesRepository susRepository;

    public SuscripcionesService(ISuscripcionesRepository susRepository) {
        this.susRepository = susRepository;
    }

    public Suscripciones save(Suscripciones suscripciones) {
        return susRepository.save(suscripciones);
    }

    public List<Suscripciones> listar() {
        return susRepository.list();
    }

    public Optional<Suscripciones> findById(Integer id) {
        return susRepository.findById(id);
    }

    public Suscripciones update(Suscripciones suscripciones) {
        return susRepository.update(suscripciones);
    }

    public void delete(Integer id) {
        susRepository.delete(id);
    }

}
