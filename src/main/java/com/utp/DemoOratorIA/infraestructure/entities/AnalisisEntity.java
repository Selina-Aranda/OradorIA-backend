package com.utp.DemoOratorIA.infraestructure.entities;

import com.utp.DemoOratorIA.domain.model.enums.AnalysisStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "analisis")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AnalisisEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idAnalisis;
    private Long idUsuario;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaAnalisis;
    private Integer duracionSegundos;
    private String videoUrl;
    private String audioUrl;

    @Lob
    private String textoTranscrito;

    @Enumerated(EnumType.STRING)
    private AnalysisStatus estado;
}
