package com.utp.DemoOratorIA.infraestructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.utp.DemoOratorIA.application.service.SuscripcionesService;

@Controller
public class SuscripcionesController {

    private final SuscripcionesService suscripcionesService;

    public SuscripcionesController(SuscripcionesService suscripcionesService) {
        this.suscripcionesService = suscripcionesService;
    }

    @GetMapping("admin-subscriptions")
    public String mostrarMain(Model model) {

        model.addAttribute("suscripciones", suscripcionesService.listar());

        return "admin-subscriptions";
    }

}