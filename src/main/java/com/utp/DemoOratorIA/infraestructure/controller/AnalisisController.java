package com.utp.DemoOratorIA.infraestructure.controller;

import org.slf4j.Logger;  // ⭐ IMPORTAR ESTO
import org.slf4j.LoggerFactory;  // ⭐ IMPORTAR ESTO
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.utp.DemoOratorIA.application.service.DashboardService;
import com.utp.DemoOratorIA.application.service.PythonAnalysisService;
import com.utp.DemoOratorIA.application.service.ResultadoQueryService;
import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.infraestructure.DTO.DashboardAnalisisDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AnalisisController {

    // ⭐ AGREGAR EL LOGGER
    private static final Logger log = LoggerFactory.getLogger(AnalisisController.class);

    private final ResultadoQueryService resultadoQueryService;
    private final DashboardService dashboardService;
    private final PythonAnalysisService pythonAnalysisService;

    public AnalisisController(ResultadoQueryService resultadoQueryService,
                              DashboardService dashboardService,
                              PythonAnalysisService pythonAnalysisService) {
        this.resultadoQueryService = resultadoQueryService;
        this.dashboardService = dashboardService;
        this.pythonAnalysisService = pythonAnalysisService;
    }

    @GetMapping("/analysis-user-new")
    public String analysisUser(Model model) {
        Integer userId = 1;
        List<Analisis> analisisRecientes = resultadoQueryService.listar(userId);
        
        if (!analisisRecientes.isEmpty()) {
            Analisis ultimo = analisisRecientes.get(0);
            model.addAttribute("ultimoAnalisis", ultimo);
        }
        
        return "analysisUser";
    }

    @GetMapping("/admin-analysis")
    public String adminAnalysis(Model model) {
        Integer idAnalisis = 1;
        model.addAttribute("analisis", resultadoQueryService.listar(idAnalisis));
        
        DashboardAnalisisDTO stats = dashboardService.getEstadisticas(idAnalisis);
        model.addAttribute("totalAnalisis", stats.totalAnalisis());
        model.addAttribute("promedioFluidez", stats.promedioFluidez());
        model.addAttribute("promedioPostura", stats.promedioPostura());
        
        return "admin-analysis";
    }

    @PostMapping("/api/analisis/iniciar")
    @ResponseBody
    public Map<String, Object> iniciarAnalisis(jakarta.servlet.http.HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            com.utp.DemoOratorIA.infraestructure.entities.UserEntity user = (com.utp.DemoOratorIA.infraestructure.entities.UserEntity) session.getAttribute("user");
            Integer idUsuario = (user != null) ? user.getId() : 1;
            Map<String, Object> resultado = pythonAnalysisService.ejecutarYGuardarConResultado(idUsuario);
            Analisis analisisGuardado = (Analisis) resultado.get("analisis");
            Map<String, Object> resultadoPython = (Map<String, Object>) resultado.get("resultadoPython");
            Map<String, Object> metadatos = (Map<String, Object>) resultado.get("metadatos");
            
            response.put("success", true);
            response.put("mensaje", "Análisis completado exitosamente");
            response.put("id", analisisGuardado.getIdAnalisis());
            response.put("estado", analisisGuardado.getEstado() != null ? 
                        analisisGuardado.getEstado().name() : "DESCONOCIDO");
            response.put("textoOriginal", analisisGuardado.getTextoTranscrito());
            response.put("resultadoProcesado", analisisGuardado.getDescripcion());
            response.put("feedback", resultadoPython != null ? resultadoPython.get("analisis") : null);
            response.put("ppm", resultadoPython != null ? resultadoPython.get("ppm") : null);
            response.put("miradas", resultadoPython != null ? resultadoPython.get("miradas") : null);
            response.put("totalMuletillas", resultadoPython != null ? resultadoPython.get("total_muletillas") : null);
            response.put("pausas", resultadoPython != null ? resultadoPython.get("pausas") : null);
            response.put("muletillas", resultadoPython != null ? resultadoPython.get("muletillas") : null);
            response.put("data", resultadoPython);
            response.put("metadatos", metadatos);
            
        } catch (Exception e) {
            log.error("❌ Error en análisis: {}", e.getMessage(), e);  // ✅ Ahora funciona
            response.put("success", false);
            response.put("mensaje", "Error en el análisis: " + e.getMessage());
        }
        
        return response;
    }

    @PostMapping("/api/analisis/detener")
    @ResponseBody
    public Map<String, Object> detenerAnalisis() {
        Map<String, Object> response = new HashMap<>();
        try {
            pythonAnalysisService.detenerAnalisis();
            response.put("success", true);
            response.put("mensaje", "Grabación detenida exitosamente");
        } catch (Exception e) {
            log.error("❌ Error al detener análisis: {}", e.getMessage(), e);
            response.put("success", false);
            response.put("mensaje", "Error al detener: " + e.getMessage());
        }
        return response;
    }

    @GetMapping("/api/analisis/resultados")
    @ResponseBody
    public Map<String, Object> obtenerResultados() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Integer userId = 1;
            List<Analisis> analisis = resultadoQueryService.listar(userId);
            
            if (!analisis.isEmpty()) {
                Analisis ultimo = analisis.get(0);
                response.put("success", true);
                response.put("id", ultimo.getIdAnalisis());
                response.put("textoOriginal", ultimo.getTextoTranscrito());
                response.put("resultadoProcesado", ultimo.getDescripcion());
                response.put("estado", ultimo.getEstado() != null ? 
                            ultimo.getEstado().name() : "DESCONOCIDO");
                response.put("fecha", ultimo.getFechaAnalisis());
                response.put("duracion", ultimo.getDuracionSegundos());
                
                Map<String, Object> metadatos = new HashMap<>();
                metadatos.put("titulo", ultimo.getTitulo());
                metadatos.put("videoUrl", ultimo.getVideoUrl());
                metadatos.put("audioUrl", ultimo.getAudioUrl());
                metadatos.put("ppm", 120);
                metadatos.put("miradas", 3);
                metadatos.put("total_muletillas", 2);
                
                response.put("metadatos", metadatos);
                
            } else {
                response.put("success", false);
                response.put("mensaje", "No hay análisis disponibles");
            }
            
        } catch (Exception e) {
            log.error("❌ Error obteniendo resultados: {}", e.getMessage(), e);  // ✅ También aquí
            response.put("success", false);
            response.put("mensaje", "Error: " + e.getMessage());
        }
        
        return response;
    }
}