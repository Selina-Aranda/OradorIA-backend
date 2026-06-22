package com.utp.DemoOratorIA.infraestructure.DTO;

import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.AnalysisStatus;

import lombok.Data;

@Data
public class AnalisisDTO {
    private Long idAnalisis;
    private Long idUsuario;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaAnalisis;
    private Integer duracionSegundos;
    private String videoUrl;
    private String audioUrl;
    private String textoTranscrito;
    private AnalysisStatus estado;
}
