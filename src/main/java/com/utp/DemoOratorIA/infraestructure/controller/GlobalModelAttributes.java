package com.utp.DemoOratorIA.infraestructure.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.utp.DemoOratorIA.application.service.DashboardService;
import com.utp.DemoOratorIA.application.service.PlanService;
import com.utp.DemoOratorIA.application.service.ResultadoQueryService;
import com.utp.DemoOratorIA.domain.model.aggregate.Plan;
import com.utp.DemoOratorIA.infraestructure.DTO.AnalisisResultadoDTO;
import com.utp.DemoOratorIA.infraestructure.DTO.DashboardAnalisisDTO;
import com.utp.DemoOratorIA.infraestructure.entities.UserEntity;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalModelAttributes {

    private final PlanService planService;
    private final ResultadoQueryService resultadoQueryService;
    private final DashboardService dashboardService;

  public GlobalModelAttributes(
            PlanService planService,
            ResultadoQueryService resultadoQueryService,
            DashboardService dashboardService) {

        this.planService = planService;
        this.resultadoQueryService = resultadoQueryService;
        this.dashboardService = dashboardService;
    }

    @ModelAttribute("plan")
    public Plan cargarPlan(HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("user");

        if (user == null) {
            return null;
        }

        return planService.findById(user.getIdPlan())
                .orElse(null);
    }

   
    @ModelAttribute("analisis")
    public List<AnalisisResultadoDTO> cargarAnalisis(HttpSession session) {

        UserEntity usuario = (UserEntity) session.getAttribute("user");

        if (usuario == null) {
            return Collections.emptyList();
        }

        return resultadoQueryService.listar(usuario.getId());
    }

   
    @ModelAttribute
    public void cargarDashboard(Model model, HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("user");

        if (user == null) {
            return;
        }

        DashboardAnalisisDTO stats = dashboardService.getEstadisticas(user.getId());

        model.addAttribute("totalAnalisis", stats.totalAnalisis());
        model.addAttribute("promedioFluidez", stats.promedioFluidez());
        model.addAttribute("promedioPostura", stats.promedioPostura());
        model.addAttribute("puntajePromedio", stats.puntajePromedio());
        model.addAttribute("mejorPuntaje", stats.mejorPuntaje());
    }
}