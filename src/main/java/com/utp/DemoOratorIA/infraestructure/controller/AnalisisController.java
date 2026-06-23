package com.utp.DemoOratorIA.infraestructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.utp.DemoOratorIA.application.service.AnalisisService;
import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;

@Controller
@RequestMapping("/analisis")
public class AnalisisController {

    private final AnalisisService analisisService;

    public AnalisisController(AnalisisService analisisService) {
        this.analisisService = analisisService;
    }

    @PostMapping("/iniciar")
    @ResponseBody
    public Analisis iniciar() {
        return analisisService.ejecutar(
            1L, 
            "Nuevo análisis", 
            "Análisis iniciado desde la web"
        );
    }
}
