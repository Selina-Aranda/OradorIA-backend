package com.utp.DemoOratorIA.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.domain.model.repositories.IAnalisisRepository;

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