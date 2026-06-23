
package com.utp.DemoOratorIA.infraestructure.entities;

import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.NotificationType;

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
@Table(name = "notificaciones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Integer idNotificacion;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "mensaje")
    private String mensaje;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private NotificationType tipo;

    @Column(name = "leido")
    private Boolean leido;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}
