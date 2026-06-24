package com.utp.DemoOratorIA.infraestructure.entities;

import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.ResultsLevel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resultados_ia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultadoIAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado")
    private Integer idResultado;

    @Column(name = "id_analisis")
    private Integer idAnalisis;

    @Column(name = "fluidez")
    private Double fluidez;

    @Column(name = "claridad")
    private Double claridad;

    @Column(name = "volumen")
    private Double volumen;

    @Column(name = "velocidad")
    private Double velocidad;

    @Column(name = "postura")
    private Double postura;

    @Column(name = "contacto_visual")
    private Double contactoVisual;

    @Column(name = "confianza")
    private Double confianza;

    @Column(name = "expresion_facial")
    private Double expresionFacial;

    @Column(name = "muletillas_detectadas")
    private Integer muletillasDetectadas;

    @Column(name = "pausas_incomodas")
    private Integer pausasIncomodas;

    @Column(name = "puntuacion_general")
    private Double puntuacionGeneral;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel")
    private ResultsLevel nivel;

    @Lob
    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "fecha_resultado")
    private LocalDateTime fechaResultado;

    @Column(name = "entrada_usuario")
    private String entradaUsuario;

    @Column(name = "respuesta_ia")
    private String respuestaIA;

    @Column(name = "puntuacion")
    private Double puntuacion;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}