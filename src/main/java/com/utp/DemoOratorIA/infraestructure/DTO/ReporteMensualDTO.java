package com.utp.DemoOratorIA.infraestructure.DTO;

public record ReporteMensualDTO(

    Integer anio,
    Integer mes,
    String etiquetaMes,
    Integer totalAnalisis,
    Double puntajePromedio,
    Double fluidezPromedio,
    Double claridadPromedio,
    Double confianzaPromedio,
    Double muletillasPromedio,
    Double duracionTotalMinutos

) {

}