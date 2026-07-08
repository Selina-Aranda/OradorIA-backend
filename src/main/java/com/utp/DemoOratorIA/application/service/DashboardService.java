package com.utp.DemoOratorIA.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.infraestructure.DTO.DashboardAnalisisDTO;
import com.utp.DemoOratorIA.infraestructure.DTO.ReporteMensualDTO;
import com.utp.DemoOratorIA.infraestructure.entities.ResultadoIAEntity;
import com.utp.DemoOratorIA.infraestructure.repositories.ResultadoQueryRepository;

@Service
public class DashboardService {

    private final ResultadoQueryRepository repository;

    private static final String[] MESES = {
    "", "Ene", "Feb", "Mar", "Abr", "May", "Jun",
    "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"
    };

    public DashboardService(ResultadoQueryRepository repository) {
        this.repository = repository;
    }

    public String obtenerNivel(Integer idUsuario) {
    return repository.obtenerNivel(idUsuario);
    }

    public DashboardAnalisisDTO getEstadisticas(Integer idUsuario) {

        List<ResultadoIAEntity> data = repository.findByIdUsuario(idUsuario);

        List<Object[]> resultado = repository.obtenerPuntajesUsuario(idUsuario);

        Object[] puntajes = resultado.isEmpty() ? new Object[]{0.0, 0.0} : resultado.get(0);

        int total = data.size();

        double promedioFluidez = data.stream()
                .mapToDouble(r -> r.getFluidez() != null ? r.getFluidez() : 0.0)
                .average()
                .orElse(0.0);

        double promedioPostura = data.stream()
                .mapToDouble(r -> r.getPostura() != null ? r.getPostura() : 0.0)
                .average()
                .orElse(0.0);

        Double puntajePromedio = puntajes[0] != null ? ((Number) puntajes[0]).doubleValue() : 0.0;

        Double mejorPuntaje = puntajes[1] != null ? ((Number) puntajes[1]).doubleValue() : 0.0;

        double muletillasPromedio = data.stream()
                .mapToDouble(r -> r.getMuletillasDetectadas() != null ? r.getMuletillasDetectadas() : 0.0)
                .average()
                .orElse(0.0);

        double promedioConfianza = data.stream()
                .mapToDouble(r -> r.getConfianza() != null ? r.getConfianza() : 0.0)
                .average()
                .orElse(0.0);

        return new DashboardAnalisisDTO(
                total,
                promedioFluidez,
                promedioPostura,
                puntajePromedio,
                mejorPuntaje,
                muletillasPromedio,
                promedioConfianza,
                obtenerNivel(idUsuario)
        );
    }

    public List<ReporteMensualDTO> obtenerReporteMensual(Integer idUsuario) {

        return repository.obtenerReporteMensual(idUsuario)
                .stream()
                .map(r -> new ReporteMensualDTO(
                        r.anio(),
                        r.mes(),
                        MESES[r.mes()],
                        r.totalAnalisis(),
                        r.puntajePromedio(),
                        r.fluidezPromedio(),
                        r.claridadPromedio(),
                        r.confianzaPromedio(),
                        r.muletillasPromedio(),
                        r.duracionTotalMinutos()
                ))
                .toList();
    }
}