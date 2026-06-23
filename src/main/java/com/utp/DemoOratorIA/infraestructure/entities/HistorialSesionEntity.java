package com.utp.DemoOratorIA.infraestructure.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "historial_sesiones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistorialSesionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "accion")
    private String accion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "ip_usuario")
    private String ipUsuario;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}