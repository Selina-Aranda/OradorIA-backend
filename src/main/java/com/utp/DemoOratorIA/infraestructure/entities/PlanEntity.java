package com.utp.DemoOratorIA.infraestructure.entities;

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
@Table(name = "planes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan")
    private Integer idPlan;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "practicas_mensuales")
    private Integer practicasMensuales;

    @Column(name = "analisis_tiempo_real")
    private Boolean analisisTiempoReal;

    @Column(name = "reportes_avanzados")
    private Boolean reportesAvanzados;

    @Column(name = "gestion_equipos")
    private Boolean gestionEquipos;

    @Column(name = "descripcion")
    private String descripcion;
}