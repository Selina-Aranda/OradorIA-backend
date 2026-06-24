package com.utp.DemoOratorIA.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.repositories.ResultadoQueryRepository;
import com.utp.DemoOratorIA.infraestructure.DTO.AnalisisResultadoDTO;
import com.utp.DemoOratorIA.infraestructure.entities.ResultadoIAEntity;

@Service
public class ResultadoQueryService {

    private final ResultadoQueryRepository repository;

    public ResultadoQueryService(ResultadoQueryRepository repository) {
        this.repository = repository;
    }

    public List<AnalisisResultadoDTO> listar(Integer idAnalisis) {

        List<ResultadoIAEntity> data = repository.findByIdAnalisis(idAnalisis);

        return data.stream()
                .map(r -> new AnalisisResultadoDTO(
                        r.getIdAnalisis(),
                        "Analisis IA",
                        "COMPLETADO",
                        r.getFecha(),
                        safe(r.getFluidez()),
                        safe(r.getPostura())))
                .collect(Collectors.toList());
    }

    private Double safe(Double v) {
        return v != null ? v : 0.0;
    }
}