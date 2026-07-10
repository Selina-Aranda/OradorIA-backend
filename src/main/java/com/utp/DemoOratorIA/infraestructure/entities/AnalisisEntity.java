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
    @Column(name = "id_analisis")
    private Integer idAnalisis;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "titulo")
    private String titulo;
    @Lob
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
    @Column(name = "fecha_analisis")
    private LocalDateTime fechaAnalisis;
    @Column(name = "duracion_segundos")
    private Integer duracionSegundos;
    @Column(name = "video_url")
    private String videoUrl;
    @Column(name = "audio_url")
    private String audioUrl;

    @Lob
    @Column(name = "texto_transcrito", columnDefinition = "LONGTEXT")
    private String textoTranscrito;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private AnalysisStatus estado;
}
