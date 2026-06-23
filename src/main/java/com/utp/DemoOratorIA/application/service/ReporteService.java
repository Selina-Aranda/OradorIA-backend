
package com.utp.DemoOratorIA.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.Reporte;
import com.utp.DemoOratorIA.domain.model.repositories.IReporteRepository;

@Service
public class ReporteService {

    private final IReporteRepository reporteRepository;

    public ReporteService(IReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    public Reporte save(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public List<Reporte> listar() {
        return reporteRepository.list();
    }

    public Reporte findById(Integer id) {
        return reporteRepository.findById(id);
    }

    public Reporte update(Reporte reporte) {
        return reporteRepository.update(reporte);
    }

    public void delete(Integer id) {
        reporteRepository.delete(id);
    }
}