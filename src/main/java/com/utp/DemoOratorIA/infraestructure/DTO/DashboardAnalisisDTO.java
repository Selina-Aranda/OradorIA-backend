package com.utp.DemoOratorIA.infraestructure.DTO;

public record DashboardAnalisisDTO(
        Integer totalAnalisis,
        Double promedioFluidez,
        Double promedioPostura,
        Double puntajePromedio,
        Double mejorPuntaje) {
}