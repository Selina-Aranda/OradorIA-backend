package com.utp.DemoOratorIA.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.infraestructure.DTO.AnalisisResultadoDTO;
import com.utp.DemoOratorIA.infraestructure.repositories.ResultadoQueryRepository;

@Service
public class ResultadoQueryService {

    private final ResultadoQueryRepository repository;

    public ResultadoQueryService(ResultadoQueryRepository repository) {
        this.repository = repository;
    }

     public List<AnalisisResultadoDTO> listar(Integer idUsuario) {

        List<Object[]> data = repository.listarAnalisisUsuario(idUsuario);

        return data.stream()
                .map(r -> new AnalisisResultadoDTO(

                        ((Number) r[0]).intValue(),
                        (String) r[1],
                        (String) r[2],
                        (LocalDateTime) r[3],
                        r[4] != null ? ((Number) r[4]).doubleValue() : 0.0,
                        r[5] != null ? ((Number) r[5]).doubleValue() : 0.0

                ))
                .toList();
    }

    private Double safe(Double v) {
        return v != null ? v : 0.0;
    }
}