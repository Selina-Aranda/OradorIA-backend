package com.utp.DemoOratorIA.application.service;

import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.domain.model.enums.AnalysisStatus;
import com.utp.DemoOratorIA.domain.model.repositories.IAnalisisRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AnalisisService {
    private final IAnalisisRepository analisisRepository;
    private final RestTemplate restTemplate;

    public AnalisisService(IAnalisisRepository analisisRepository, RestTemplate restTemplate) {
        this.analisisRepository = analisisRepository;
        this.restTemplate = restTemplate;
    }

    public Analisis ejecutar(Long idUsuario, String titulo, String descripcion){

        Analisis analisis = new Analisis.Builder()
                .idUsuario(idUsuario)
                .titulo(titulo)
                .descripcion(descripcion)
                .estado(AnalysisStatus.PROCESANDO)
                .build();

        analisis = analisisRepository.save(analisis);

        String respuesta = restTemplate.getForObject(
            "http://127.0.0.1:8000/analizar", 
            String.class
        );

        analisis = new Analisis.Builder()
                .idAnalisis(analisis.getIdAnalisis())
                .idUsuario(analisis.getIdUsuario())
                .titulo(analisis.getTitulo())
                .descripcion(respuesta)
                .estado(AnalysisStatus.COMPLETADO)
                .build();

        analisisRepository.save(analisis);

        return analisis;
    }
}
