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
@Table(name = "actividad_reciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActividadRecienteEntity {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Integer idActividad;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name="actividad")
    private String actividad;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}
