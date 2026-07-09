package com.utp.DemoOratorIA.application.service;

import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.domain.model.repositories.IAnalisisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultadoQueryService {

    private final IAnalisisRepository analisisRepository;

    public ResultadoQueryService(IAnalisisRepository analisisRepository) {
        this.analisisRepository = analisisRepository;
    }

    public List<Analisis> listar(Integer usuarioId) {
        // Aquí deberías filtrar por usuarioId
        // Por ahora retorna todos
        return analisisRepository.list();
    }
    
    public Analisis obtenerUltimoPorUsuario(Integer usuarioId) {
        List<Analisis> lista = listar(usuarioId);
        if (!lista.isEmpty()) {
            return lista.get(0);
        }
        return null;
    }
}