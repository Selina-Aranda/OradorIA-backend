package com.utp.DemoOratorIA.application.service;

import org.springframework.stereotype.Service;

import java.util.List;


import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.domain.model.repositories.IAnalisisRepository;

@Service
public class AnalisisService {

    private final IAnalisisRepository analisisRepository;

    public AnalisisService(IAnalisisRepository analisisRepository) {
        this.analisisRepository = analisisRepository;
    }

    public Analisis save(Analisis analisis) {
        return analisisRepository.save(analisis);
    }

    public List<Analisis> listar() {
        return analisisRepository.list();
    }

    public Analisis findById(Integer id) {
        return analisisRepository.findById(id);
    }

    public Analisis update(Analisis analisis) {
        return analisisRepository.update(analisis);
    }

    public void delete(Integer id) {
        analisisRepository.delete(id);
    }
}
