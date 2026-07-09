package com.utp.DemoOratorIA.application.service;

import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.domain.model.aggregate.ResultadoIA;
import com.utp.DemoOratorIA.domain.model.enums.AnalysisStatus;
import com.utp.DemoOratorIA.domain.model.enums.ResultsLevel;
import com.utp.DemoOratorIA.infraestructure.adapters.PythonExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class PythonAnalysisService {

    private static final Logger log = LoggerFactory.getLogger(PythonAnalysisService.class);
    private static final int MAX_DESCRIPCION_LENGTH = 250;

    private final PythonExecutor pythonExecutor;
    private final AnalisisService analisisService;
    private final ResultadoIAService resultadoIAService;

    public PythonAnalysisService(PythonExecutor pythonExecutor,
                                 AnalisisService analisisService,
                                 ResultadoIAService resultadoIAService) {
        this.pythonExecutor = pythonExecutor;
        this.analisisService = analisisService;
        this.resultadoIAService = resultadoIAService;
    }

    public Analisis ejecutarYGuardar() {
        Map<String, Object> resultado = ejecutarYGuardarConResultado();
        return (Analisis) resultado.get("analisis");
    }

    public Map<String, Object> ejecutarYGuardarConResultado() {
        log.info("🚀 Ejecutando análisis completo con Python");

        try {
            Map<String, Object> resultadoPython = pythonExecutor.ejecutarAnalisis();

            log.info("📊 Resultado Python: {}", resultadoPython);

            String texto = (String) resultadoPython.get("texto");
            String analisis = (String) resultadoPython.get("analisis");
            Integer ppm = extraerEntero(resultadoPython.get("ppm"));
            Integer miradas = extraerEntero(resultadoPython.get("miradas"));
            Integer totalMuletillas = extraerEntero(resultadoPython.get("total_muletillas"));
            Map<String, Object> pausas = resultadoPython.get("pausas") instanceof Map
                    ? (Map<String, Object>) resultadoPython.get("pausas")
                    : new HashMap<>();
            Map<String, Object> muletillas = resultadoPython.get("muletillas") instanceof Map
                    ? (Map<String, Object>) resultadoPython.get("muletillas")
                    : new HashMap<>();

            Map<String, Object> metadatos = new HashMap<>();
            metadatos.put("ppm", ppm);
            metadatos.put("miradas", miradas);
            metadatos.put("total_muletillas", totalMuletillas);
            metadatos.put("pausas", pausas);
            metadatos.put("muletillas", muletillas);

            Analisis analisisObj = new Analisis.Builder()
                    .idUsuario(1)  // TODO: Obtener de sesión
                    .titulo("Análisis de Oratoria - " + LocalDateTime.now())
                    .descripcion(truncarDescripcion(analisis))
                    .fechaAnalisis(LocalDateTime.now())
                    .duracionSegundos(30)
                    .textoTranscrito(texto)
                    .estado(AnalysisStatus.COMPLETADO)
                    .build();

            Analisis guardado = analisisService.save(analisisObj);
            log.info("✅ Análisis guardado con ID: {}", guardado.getIdAnalisis());

            ResultadoIA resultadoIA = construirResultadoIA(guardado.getIdAnalisis(), resultadoPython);
            ResultadoIA resultadoGuardado = resultadoIAService.save(resultadoIA);
            log.info("✅ Resultado IA guardado con ID: {}", resultadoGuardado.getIdResultado());

            Map<String, Object> resultado = new HashMap<>();
            resultado.put("analisis", guardado);
            resultado.put("resultadoIA", resultadoGuardado);
            resultado.put("resultadoPython", resultadoPython);
            resultado.put("metadatos", metadatos);
            return resultado;

        } catch (Exception e) {
            log.error("❌ Error en análisis: {}", e.getMessage(), e);

            String mensajeError = e.getMessage();
            if (mensajeError == null || mensajeError.isBlank()) {
                mensajeError = e.getClass().getSimpleName();
            }

            Analisis analisis = new Analisis.Builder()
                    .idUsuario(1)
                    .titulo("Análisis Fallido")
                    .descripcion(truncarDescripcion("Error: " + mensajeError))
                    .fechaAnalisis(LocalDateTime.now())
                    .estado(AnalysisStatus.ERROR)
                    .build();

            Analisis guardado = analisisService.save(analisis);

            Map<String, Object> resultadoPython = new HashMap<>();
            resultadoPython.put("success", false);
            resultadoPython.put("mensaje", mensajeError);
            resultadoPython.put("error", e.getClass().getSimpleName());

            Map<String, Object> resultado = new HashMap<>();
            resultado.put("analisis", guardado);
            resultado.put("resultadoPython", resultadoPython);
            resultado.put("metadatos", new HashMap<>());
            return resultado;
        }
    }

    private ResultadoIA construirResultadoIA(Integer idAnalisis, Map<String, Object> resultadoPython) {
        Map<String, Object> pausas = resultadoPython.get("pausas") instanceof Map
                ? (Map<String, Object>) resultadoPython.get("pausas")
                : new HashMap<>();

        Integer cantidadPausas = extraerEntero(pausas.get("cantidad_pausas_largas"));
        Integer totalMuletillas = extraerEntero(resultadoPython.get("total_muletillas"));
        Integer miradas = extraerEntero(resultadoPython.get("miradas"));
        Integer ppm = extraerEntero(resultadoPython.get("ppm"));
        String analisis = resultadoPython.get("analisis") != null ? resultadoPython.get("analisis").toString() : null;
        String texto = resultadoPython.get("texto") != null ? resultadoPython.get("texto").toString() : null;

        return new ResultadoIA.Builder()
                .idAnalisis(idAnalisis)
                .fluidez(ppm != null ? ppm.doubleValue() : null)
                .claridad(null)
                .volumen(null)
                .velocidad(ppm != null ? ppm.doubleValue() : null)
                .postura(miradas != null ? miradas.doubleValue() : null)
                .contactoVisual(null)
                .confianza(null)
                .expresionFacial(null)
                .muletillasDetectadas(totalMuletillas)
                .pausasIncomodas(cantidadPausas)
                .puntuacionGeneral(null)
                .nivel(ResultsLevel.BASICO)
                .observaciones(analisis)
                .fechaResultado(LocalDateTime.now())
                .entradaUsuario(texto)
                .respuestaIA(analisis)
                .puntuacion(null)
                .fecha(LocalDateTime.now())
                .build();
    }

    private Integer extraerEntero(Object valor) {
        if (valor == null) {
            return null;
        }
        if (valor instanceof Number number) {
            return number.intValue();
        }
        try {
            return Integer.parseInt(valor.toString());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private String truncarDescripcion(String texto) {
        if (texto == null) {
            return null;
        }

        String normalizado = texto.trim();
        if (normalizado.length() <= MAX_DESCRIPCION_LENGTH) {
            return normalizado;
        }

        return normalizado.substring(0, MAX_DESCRIPCION_LENGTH - 3).trim() + "...";
    }
}