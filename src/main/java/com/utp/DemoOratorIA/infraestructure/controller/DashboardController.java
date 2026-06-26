package com.utp.DemoOratorIA.infraestructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.utp.DemoOratorIA.application.service.ActividadRecienteService;
import com.utp.DemoOratorIA.application.service.DashboardService;
import com.utp.DemoOratorIA.application.service.PagoService;
import com.utp.DemoOratorIA.application.service.ResultadoQueryService;
import com.utp.DemoOratorIA.application.service.UserService;
import com.utp.DemoOratorIA.infraestructure.DTO.DashboardAnalisisDTO;


@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private PagoService pagoService;

    @Autowired
    private ResultadoQueryService resultadoQueryService;

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private ActividadRecienteService actividadService;

    @GetMapping("/admin-dashboard")
    public String dashboard(Model model){

        Integer idAnalisis = 1;

        long totalUsuarios = userService.contarUsuarios();
        model.addAttribute("totalUsuarios", totalUsuarios);

         DashboardAnalisisDTO stats = dashboardService.getEstadisticas(idAnalisis);
        model.addAttribute("totalAnalisis", stats.totalAnalisis());

        long totalUsuariosPremiun = userService.contarUsuariosPremiun();
        model.addAttribute("totalUsuariosPremiun", totalUsuariosPremiun);

        model.addAttribute("ingresosMensuales", pagoService.obtenerIngresosMensuales());

        model.addAttribute("actividades", actividadService.listar());


        return "admin-dashboard";
    }
    
}
