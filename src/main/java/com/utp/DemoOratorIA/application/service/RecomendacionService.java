package com.utp.DemoOratorIA.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.Recomendacion;
import com.utp.DemoOratorIA.domain.model.repositories.IRecomendacionRepository;

@Service
public class RecomendacionService {

    private final IRecomendacionRepository recomendacionRepository;

    public RecomendacionService(IRecomendacionRepository recomendacionRepository) {
        this.recomendacionRepository = recomendacionRepository;
    }

    public Recomendacion save(Recomendacion rec) {
        return recomendacionRepository.save(rec);
    }

    public List<Recomendacion> listar() {
        return recomendacionRepository.list();
    }

    public Recomendacion findById(Integer id) {
        return recomendacionRepository.findById(id);
    }

    public Recomendacion update(Recomendacion rec) {
        return recomendacionRepository.update(rec);
    }

    public void delete(Integer id) {
        recomendacionRepository.delete(id);
    }
}