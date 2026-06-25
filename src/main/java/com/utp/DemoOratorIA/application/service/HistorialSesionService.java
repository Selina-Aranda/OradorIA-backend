package com.utp.DemoOratorIA.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.HistorialSesion;
import com.utp.DemoOratorIA.domain.model.repositories.IHistorialSesionRepository;

@Service
public class HistorialSesionService {

    private final IHistorialSesionRepository historialSesionRepository;

    public HistorialSesionService(IHistorialSesionRepository historialSesionRepository) {
        this.historialSesionRepository = historialSesionRepository;
    }

    public HistorialSesion save(HistorialSesion historialSesion) {
        return historialSesionRepository.save(historialSesion);
    }

    public List<HistorialSesion> listar() {
        return historialSesionRepository.list();
    }

    public Optional<HistorialSesion> findById(Integer id) {
        return historialSesionRepository.findById(id);
    }

    public HistorialSesion update(HistorialSesion historialSesion) {
        return historialSesionRepository.update(historialSesion);
    }

    public void delete(Integer id) {
        historialSesionRepository.delete(id);
    }
}