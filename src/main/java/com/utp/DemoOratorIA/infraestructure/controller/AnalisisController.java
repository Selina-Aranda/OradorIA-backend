package com.utp.DemoOratorIA.infraestructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.utp.DemoOratorIA.application.service.AnalisisService;
import com.utp.DemoOratorIA.application.service.DashboardService;
import com.utp.DemoOratorIA.application.service.ResultadoQueryService;
import com.utp.DemoOratorIA.application.service.UserService;
import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;


@Controller
public class AnalisisController {

    private final ResultadoQueryService resultadoQueryService;
    private final DashboardService dashboardService;
    private final AnalisisService analisisService;
     private final UserService userService;

    public AnalisisController(ResultadoQueryService resultadoQueryService,
            DashboardService dashboardService, AnalisisService analisisService, UserService userService) {
        this.resultadoQueryService = resultadoQueryService;
        this.dashboardService = dashboardService;
        this.analisisService = analisisService;
        this.userService = userService;
    }

    @GetMapping("/admin-analysis")
    public String adminAnalysis(Model model) {
        return "admin-analysis";
    }

    @PostMapping
        public ResponseEntity<Analisis> guardar(@RequestBody Analisis analisis) {

            Analisis nuevo = analisisService.save(analisis);

            return ResponseEntity.ok(nuevo);
        }

    @GetMapping("/profile-user")
    public String userAnalysis() {
        return "profileUser";
    }

    @GetMapping("/profile-user-data")
        public String profileUser() {
        return "profileUser";
    }
    
}