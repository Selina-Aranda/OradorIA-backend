package com.utp.DemoOratorIA.application.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.domain.model.repositories.IAnalisisRepository;

@Service
public class AnalisisService {

    private final IAnalisisRepository analisisRepository;
    private final ActividadRecienteService actividadService;

    public AnalisisService(IAnalisisRepository analisisRepository, ActividadRecienteService actividadService) {
        this.analisisRepository = analisisRepository;
        this.actividadService = actividadService;
    }

    public Analisis save(Analisis analisis) {

        Analisis nuevoAnalisis = analisisRepository.save(analisis);

        actividadService.registrar(
                analisis.getIdUsuario(),
                "ANALISIS",
                "Nuevo análisis completado"
        );

        return nuevoAnalisis;
    }

    public List<Analisis> listar() {
        return analisisRepository.list();
    }

    public Optional<Analisis> findById(Integer id) {
        return analisisRepository.findById(id);
    }

    public Analisis update(Analisis analisis) {
        return analisisRepository.update(analisis);
    }

    public void delete(Integer id) {
        analisisRepository.delete(id);
    }
}
