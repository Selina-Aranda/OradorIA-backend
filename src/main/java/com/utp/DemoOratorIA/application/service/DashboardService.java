package com.utp.DemoOratorIA.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.repositories.ResultadoQueryRepository;
import com.utp.DemoOratorIA.infraestructure.DTO.DashboardAnalisisDTO;
import com.utp.DemoOratorIA.infraestructure.entities.ResultadoIAEntity;

@Service
public class DashboardService {

    private final ResultadoQueryRepository repository;

    public DashboardService(ResultadoQueryRepository repository) {
        this.repository = repository;
    }

    public DashboardAnalisisDTO getEstadisticas(Integer idAnalisis) {

        List<ResultadoIAEntity> data = repository.findByIdAnalisis(idAnalisis);

        int total = data.size();

        double promedioFluidez = data.stream()
                .mapToDouble(r -> r.getFluidez() != null ? r.getFluidez() : 0.0)
                .average()
                .orElse(0.0);

        double promedioPostura = data.stream()
                .mapToDouble(r -> r.getPostura() != null ? r.getPostura() : 0.0)
                .average()
                .orElse(0.0);

        return new DashboardAnalisisDTO(total, promedioFluidez, promedioPostura);
    }
}