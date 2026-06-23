
package com.utp.DemoOratorIA.domain.model.aggregate;

import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.NotificationType;

public class Notificacion {

    private Integer idNotificacion;
    private Integer idUsuario;
    private String titulo;
    private String mensaje;
    private NotificationType tipo;
    private Boolean leido;
    private LocalDateTime fecha;

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public NotificationType getTipo() {
        return tipo;
    }

    public void setTipo(NotificationType tipo) {
        this.tipo = tipo;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    // BUILDER
    public static class Builder {

        private Notificacion notificacion;

        public Builder() {
            this.notificacion = new Notificacion();
        }

        public Builder idNotificacion(Integer idNotificacion) {
            this.notificacion.idNotificacion = idNotificacion;
            return this;
        }

        public Builder idUsuario(Integer idUsuario) {
            this.notificacion.idUsuario = idUsuario;
            return this;
        }

        public Builder titulo(String titulo) {
            this.notificacion.titulo = titulo;
            return this;
        }

        public Builder mensaje(String mensaje) {
            this.notificacion.mensaje = mensaje;
            return this;
        }

        public Builder tipo(NotificationType tipo) {
            this.notificacion.tipo = tipo;
            return this;
        }

        public Builder leido(Boolean leido) {
            this.notificacion.leido = leido;
            return this;
        }

        public Builder fecha(LocalDateTime fecha) {
            this.notificacion.fecha = fecha;
            return this;
        }

        public Notificacion build() {
            return this.notificacion;
        }
    }
}