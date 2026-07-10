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
        return ejecutarYGuardarConResultado(1);
    }

    public Map<String, Object> ejecutarYGuardarConResultado(Integer idUsuario) {
        log.info("🚀 Ejecutando análisis completo con Python para usuario: {}", idUsuario);

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

            // Guardamos el análisis
            String descripcionAnalisis = analisis != null ? analisis : (String) resultadoPython.get("recomendaciones");
            if (descripcionAnalisis == null) {
                descripcionAnalisis = "Análisis de oratoria completado con éxito.";
            }

            Analisis analisisObj = new Analisis.Builder()
                    .idUsuario(idUsuario)
                    .titulo("Análisis de Oratoria - " + LocalDateTime.now())
                    .descripcion(truncarDescripcion(descripcionAnalisis))
                    .fechaAnalisis(LocalDateTime.now())
                    .duracionSegundos(30)
                    .textoTranscrito(texto)
                    .estado(AnalysisStatus.COMPLETADO)
                    .build();

            Analisis guardado = analisisService.save(analisisObj);
            log.info("✅ Análisis guardado con ID: {}", guardado.getIdAnalisis());

            // Construir y guardar el resultado IA
            ResultadoIA resultadoIA = construirResultadoIA(guardado.getIdAnalisis(), resultadoPython);
            ResultadoIA resultadoGuardado = resultadoIAService.save(resultadoIA);
            log.info("✅ Resultado IA guardado con ID: {}", resultadoGuardado.getIdResultado());

            // Actualizar resultadoPython con los datos escalados y procesados para el frontend
            resultadoPython.put("fluidez", resultadoIA.getFluidez());
            resultadoPython.put("claridad", resultadoIA.getClaridad());
            resultadoPython.put("volumen", resultadoIA.getVolumen());
            resultadoPython.put("velocidad", resultadoIA.getVelocidad());
            resultadoPython.put("postura", resultadoIA.getPostura());
            resultadoPython.put("contacto_visual", resultadoIA.getContactoVisual());
            resultadoPython.put("confianza", resultadoIA.getConfianza());
            resultadoPython.put("expresion_facial", resultadoIA.getExpresionFacial());
            resultadoPython.put("puntuacion_general", resultadoIA.getPuntuacionGeneral());
            resultadoPython.put("nivel", resultadoIA.getNivel().name());
            resultadoPython.put("observaciones", resultadoIA.getObservaciones());
            resultadoPython.put("analisis", resultadoIA.getObservaciones());
            resultadoPython.put("pausas_incomodas", resultadoIA.getPausasIncomodas());
            resultadoPython.put("muletillas_detectadas", resultadoIA.getMuletillasDetectadas());

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

            Analisis analisisObj = new Analisis.Builder()
                    .idUsuario(idUsuario)
                    .titulo("Análisis Fallido")
                    .descripcion(truncarDescripcion("Error: " + mensajeError))
                    .fechaAnalisis(LocalDateTime.now())
                    .estado(AnalysisStatus.ERROR)
                    .build();

            Analisis guardado = analisisService.save(analisisObj);

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

    private Double escalarA100(Object valor) {
        if (valor == null) {
            return null;
        }
        try {
            double val = Double.parseDouble(valor.toString());
            // Si el valor viene de 0 a 10 (ej. desde Ollama), lo escalamos a porcentaje (0 a 100)
            if (val <= 10.0) {
                return val * 10.0;
            }
            return val;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private ResultadoIA construirResultadoIA(Integer idAnalisis, Map<String, Object> resultadoPython) {
        Map<String, Object> pausas = resultadoPython.get("pausas") instanceof Map
                ? (Map<String, Object>) resultadoPython.get("pausas")
                : new HashMap<>();

        Integer cantidadPausas = extraerEntero(resultadoPython.get("pausas_incomodas"));
        if (cantidadPausas == null) {
            cantidadPausas = extraerEntero(pausas.get("cantidad_pausas_largas"));
        }
        Integer totalMuletillas = extraerEntero(resultadoPython.get("muletillas_detectadas"));
        if (totalMuletillas == null) {
            totalMuletillas = extraerEntero(resultadoPython.get("total_muletillas"));
        }
        Integer miradas = extraerEntero(resultadoPython.get("miradas_desviadas"));
        if (miradas == null) {
            miradas = extraerEntero(resultadoPython.get("miradas"));
        }
        Integer ppm = extraerEntero(resultadoPython.get("velocidad_ppm"));
        if (ppm == null) {
            ppm = extraerEntero(resultadoPython.get("ppm"));
        }

        Double fluidez = escalarA100(resultadoPython.get("fluidez"));
        Double claridad = escalarA100(resultadoPython.get("claridad"));
        Double volumen = escalarA100(resultadoPython.get("volumen"));
        Double velocidad = escalarA100(resultadoPython.get("velocidad"));
        Double postura = escalarA100(resultadoPython.get("postura"));
        Double contactoVisual = escalarA100(resultadoPython.get("contacto_visual"));
        if (contactoVisual == null) {
            contactoVisual = escalarA100(resultadoPython.get("contactoVisual"));
        }
        Double confianza = escalarA100(resultadoPython.get("confianza"));
        Double expresionFacial = escalarA100(resultadoPython.get("expresion_facial"));
        if (expresionFacial == null) {
            expresionFacial = escalarA100(resultadoPython.get("expresionFacial"));
        }
        Double puntuacionGeneral = escalarA100(resultadoPython.get("puntuacion_general"));

        // Determinar el nivel según la puntuación general
        ResultsLevel nivel = ResultsLevel.BASICO;
        if (puntuacionGeneral != null) {
            if (puntuacionGeneral >= 80.0) {
                nivel = ResultsLevel.AVANZADO;
            } else if (puntuacionGeneral >= 50.0) {
                nivel = ResultsLevel.INTERMEDIO;
            }
        }

        // Construir observaciones combinando errores_detectados y recomendaciones
        String observaciones = null;
        Object obsObj = resultadoPython.get("observaciones");
        if (obsObj != null) {
            observaciones = obsObj.toString();
        } else {
            StringBuilder combined = new StringBuilder();
            Object errores = resultadoPython.get("errores_detectados");
            Object recs = resultadoPython.get("recomendaciones");
            if (errores != null && !errores.toString().isBlank()) {
                combined.append("Errores detectados:\n").append(errores.toString()).append("\n\n");
            }
            if (recs != null && !recs.toString().isBlank()) {
                combined.append("Recomendaciones:\n").append(recs.toString());
            }
            if (combined.length() > 0) {
                observaciones = combined.toString().trim();
            } else {
                observaciones = resultadoPython.get("analisis") != null ? resultadoPython.get("analisis").toString() : "Sin observaciones.";
            }
        }

        String texto = resultadoPython.get("texto") != null ? resultadoPython.get("texto").toString() : null;

        return new ResultadoIA.Builder()
                .idAnalisis(idAnalisis)
                .fluidez(fluidez)
                .claridad(claridad)
                .volumen(volumen)
                .velocidad(velocidad)
                .postura(postura)
                .contactoVisual(contactoVisual)
                .confianza(confianza)
                .expresionFacial(expresionFacial)
                .muletillasDetectadas(totalMuletillas)
                .pausasIncomodas(cantidadPausas)
                .puntuacionGeneral(puntuacionGeneral)
                .nivel(nivel)
                .observaciones(observaciones)
                .fechaResultado(LocalDateTime.now())
                .entradaUsuario(texto)
                .respuestaIA(observaciones)
                .puntuacion(puntuacionGeneral)
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

    public void detenerAnalisis() throws Exception {
        pythonExecutor.detenerAnalisis();
    }
}