package com.utp.DemoOratorIA.infraestructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.utp.DemoOratorIA.application.service.DashboardService;
import com.utp.DemoOratorIA.application.service.ResultadoQueryService;
import com.utp.DemoOratorIA.application.service.AnalisisService;
import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.infraestructure.DTO.DashboardAnalisisDTO;

@Controller
public class AnalisisController {

    private final ResultadoQueryService resultadoQueryService;
    private final DashboardService dashboardService;
    private final AnalisisService analisisService;

    public AnalisisController(ResultadoQueryService resultadoQueryService,
            DashboardService dashboardService, AnalisisService analisisService ) {
        this.resultadoQueryService = resultadoQueryService;
        this.dashboardService = dashboardService;
        this.analisisService = analisisService;
    }

    @GetMapping("/admin-analysis")
    public String adminAnalysis(Model model) {

        Integer idAnalisis = 1;

        // LISTA TABLA
        model.addAttribute("analisis", resultadoQueryService.listar(idAnalisis));

        // ESTADÍSTICAS DASHBOARD
        DashboardAnalisisDTO stats = dashboardService.getEstadisticas(idAnalisis);

        model.addAttribute("totalAnalisis", stats.totalAnalisis());
        model.addAttribute("promedioFluidez", stats.promedioFluidez());
        model.addAttribute("promedioPostura", stats.promedioPostura());

        return "admin-analysis";
    }

    @PostMapping
        public ResponseEntity<Analisis> guardar(@RequestBody Analisis analisis) {

            Analisis nuevo = analisisService.save(analisis);

            return ResponseEntity.ok(nuevo);
        }
}