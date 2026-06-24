package com.utp.DemoOratorIA.infraestructure.entities;

import com.utp.DemoOratorIA.domain.model.enums.CategoriesRecommends;
import com.utp.DemoOratorIA.domain.model.enums.PriorityRecommends;

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
@Table(name = "recomendaciones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecomendacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recomendacion")
    private Integer idRecomendacion;

    @Column(name = "id_resultado")
    private Integer idResultado;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private CategoriesRecommends categoria;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "prioridad")
    private PriorityRecommends prioridad;

    @Column(name = "titulo")
    private String titulo;

}