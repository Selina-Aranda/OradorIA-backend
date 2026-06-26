package com.utp.DemoOratorIA.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.Pago;
import com.utp.DemoOratorIA.domain.model.repositories.IPagoRepository;

@Service
public class PagoService {

    private final IPagoRepository pagoRepository;

    public PagoService(IPagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }

    public List<Pago> listar() {
        return pagoRepository.list();
    }

    public Optional<Pago> findById(Integer id) {
        return pagoRepository.findById(id);
    }

    public Pago update(Pago pago) {
        return pagoRepository.update(pago);
    }

    public void delete(Integer id) {
        pagoRepository.delete(id);
    }
}