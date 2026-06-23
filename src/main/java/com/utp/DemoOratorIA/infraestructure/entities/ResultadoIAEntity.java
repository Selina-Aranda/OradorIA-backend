package com.utp.DemoOratorIA.infraestructure.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "entrada_usuario", length = 1000)
    private String entradaUsuario;

    @Column(name = "respuesta_ia", length = 2000)
    private String respuestaIA;

    @Column(name = "puntuacion")
    private Double puntuacion;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}