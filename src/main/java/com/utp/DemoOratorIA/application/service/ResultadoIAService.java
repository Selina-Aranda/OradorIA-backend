package com.utp.DemoOratorIA.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.ResultadoIA;
import com.utp.DemoOratorIA.domain.model.repositories.IResultadoIARepository;

@Service
public class ResultadoIAService {

    private final IResultadoIARepository resultadoRepository;

    public ResultadoIAService(IResultadoIARepository resultadoRepository) {
        this.resultadoRepository = resultadoRepository;
    }

    public ResultadoIA save(ResultadoIA res) {
        return resultadoRepository.save(res);
    }

    public List<ResultadoIA> listar() {
        return resultadoRepository.list();
    }

    public ResultadoIA findById(Integer id) {
        return resultadoRepository.findById(id);
    }

    public ResultadoIA update(ResultadoIA res) {
        return resultadoRepository.update(res);
    }

    public void delete(Integer id) {
        resultadoRepository.delete(id);
    }
}