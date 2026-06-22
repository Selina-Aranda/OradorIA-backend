package com.utp.DemoOratorIA.application.service;

import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.domain.model.enums.AnalysisStatus;
import com.utp.DemoOratorIA.domain.model.repositories.IAnalisisRepository;
import org.springframework.stereotype.Service;

@Service
public class AnalisisService {
    private final IAnalisisRepository analisisRepository;

    public AnalisisService(IAnalisisRepository analisisRepository) {
        this.analisisRepository = analisisRepository;
    }

    public Analisis ejecutar(Long idUsuario, String titulo, String descripcion){

        Analisis analisis = new Analisis.Builder()
                .idUsuario(idUsuario)
                .titulo(titulo)
                .descripcion(descripcion)
                .estado(AnalysisStatus.PROCESANDO)
                .build();

        return analisisRepository.save(analisis);
    }
}
