package com.utp.DemoOratorIA.infraestructure.entities;

import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.SuscripcionesStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "suscripciones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuscripcionesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suscripcion")
    private Integer idSuscripciones;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "id_plan")
    private Integer idPlan;
    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;
    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;
    @Column(name = "renovacion_automatica")
    private Boolean renovacionAutomatica;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private SuscripcionesStatus estado;

}
