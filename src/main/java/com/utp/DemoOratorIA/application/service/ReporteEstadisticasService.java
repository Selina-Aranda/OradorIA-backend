
package com.utp.DemoOratorIA.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.infraestructure.DTO.ReporteMensualDTO;
import com.utp.DemoOratorIA.infraestructure.DTO.ReporteResumenDTO;
import com.utp.DemoOratorIA.infraestructure.repositories.ResultadoQueryRepository;

@Service
public class ReporteEstadisticasService {

    private static final String[] NOMBRES_MES = {
            "Ene", "Feb", "Mar", "Abr", "May", "Jun",
            "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"
    };

    private final ResultadoQueryRepository repository;

    public ReporteEstadisticasService(ResultadoQueryRepository repository) {
        this.repository = repository;
    }

    public ReporteResumenDTO getEstadisticasReporte(Integer idUsuario) {

        List<Object[]> filas = repository.obtenerEvolucionMensual(idUsuario);

        List<ReporteMensualDTO> datosMensuales = filas.stream()
                .map(this::mapearFila)
                .toList();

        if (datosMensuales.isEmpty()) {
            return new ReporteResumenDTO(0.0, "0%", 0.0, 0, "0%", "0%", "0%", "0%", datosMensuales);
        }

        Double promedioGeneral = redondear(
                datosMensuales.stream().mapToDouble(ReporteMensualDTO::puntajePromedio).average().orElse(0.0));

        Double promedioMuletillas = redondear(
                datosMensuales.stream().mapToDouble(ReporteMensualDTO::muletillasPromedio).average().orElse(0.0));

        Integer totalSesiones = datosMensuales.stream()
                .mapToInt(ReporteMensualDTO::totalAnalisis)
                .sum();

        ReporteMensualDTO primerMes = datosMensuales.get(0);
        ReporteMensualDTO ultimoMes = datosMensuales.get(datosMensuales.size() - 1);

        String mejoraTotal = calcularPorcentaje(primerMes.puntajePromedio(), ultimoMes.puntajePromedio());
        String mejoraFluidez = calcularPorcentaje(primerMes.fluidezPromedio(), ultimoMes.fluidezPromedio());
        String mejoraClaridad = calcularPorcentaje(primerMes.claridadPromedio(), ultimoMes.claridadPromedio());
        String mejoraConfianza = calcularPorcentaje(primerMes.confianzaPromedio(), ultimoMes.confianzaPromedio());
        String reduccionMuletillas = calcularPorcentaje(primerMes.muletillasPromedio(), ultimoMes.muletillasPromedio());

        return new ReporteResumenDTO(
                promedioGeneral,
                mejoraTotal,
                promedioMuletillas,
                totalSesiones,
                mejoraFluidez,
                mejoraClaridad,
                mejoraConfianza,
                reduccionMuletillas,
                datosMensuales
        );
    }

    private ReporteMensualDTO mapearFila(Object[] fila) {

        Integer anio = ((Number) fila[0]).intValue();
        Integer mes = ((Number) fila[1]).intValue();
        Integer totalAnalisis = ((Number) fila[2]).intValue();
        Double puntaje = fila[3] != null ? ((Number) fila[3]).doubleValue() : 0.0;
        Double fluidez = fila[4] != null ? ((Number) fila[4]).doubleValue() : 0.0;
        Double claridad = fila[5] != null ? ((Number) fila[5]).doubleValue() : 0.0;
        Double confianza = fila[6] != null ? ((Number) fila[6]).doubleValue() : 0.0;
        Double muletillas = fila[7] != null ? ((Number) fila[7]).doubleValue() : 0.0;
        Double duracion = fila[8] != null ? ((Number) fila[8]).doubleValue() : 0.0;

        return new ReporteMensualDTO(
                anio,
                mes,
                NOMBRES_MES[mes - 1],
                totalAnalisis,
                redondear(puntaje),
                redondear(fluidez),
                redondear(claridad),
                redondear(confianza),
                redondear(muletillas),
                redondear(duracion)
        );
    }

    private String calcularPorcentaje(Double inicial, Double actual) {

        if (inicial == null || inicial == 0.0) {
            return "0%";
        }

        double cambio = ((actual - inicial) / inicial) * 100;
        String signo = cambio >= 0 ? "+" : "";

        return signo + Math.round(cambio) + "%";
    }

    private Double redondear(Double valor) {

        if (valor == null) {
            return 0.0;
        }

        return Math.round(valor * 10.0) / 10.0;
    }
}