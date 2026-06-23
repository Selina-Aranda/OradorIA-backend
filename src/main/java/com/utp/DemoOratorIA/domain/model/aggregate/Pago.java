package com.utp.DemoOratorIA.domain.model.aggregate;

import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.PaymentMethod;
import com.utp.DemoOratorIA.domain.model.enums.PaymentStatus;

public class Pago {

    private Integer idPago;
    private Integer idSuscripcion;
    private Double monto;
    private PaymentMethod metodoPago;
    private PaymentStatus estado;
    private String transaccionId;
    private LocalDateTime fechaPago;

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Integer getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Integer idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public PaymentMethod getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(PaymentMethod metodoPago) {
        this.metodoPago = metodoPago;
    }

    public PaymentStatus getEstado() {
        return estado;
    }

    public void setEstado(PaymentStatus estado) {
        this.estado = estado;
    }

    public String getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(String transaccionId) {
        this.transaccionId = transaccionId;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    // BUILDER
    public static class Builder {

        private Pago pago;

        public Builder() {
            this.pago = new Pago();
        }

        public Builder idPago(Integer idPago) {
            this.pago.idPago = idPago;
            return this;
        }

        public Builder idSuscripcion(Integer idSuscripcion) {
            this.pago.idSuscripcion = idSuscripcion;
            return this;
        }

        public Builder monto(Double monto) {
            this.pago.monto = monto;
            return this;
        }

        public Builder metodoPago(PaymentMethod metodoPago) {
            this.pago.metodoPago = metodoPago;
            return this;
        }

        public Builder estado(PaymentStatus estado) {
            this.pago.estado = estado;
            return this;
        }

        public Builder transaccionId(String transaccionId) {
            this.pago.transaccionId = transaccionId;
            return this;
        }

        public Builder fechaPago(LocalDateTime fechaPago) {
            this.pago.fechaPago = fechaPago;
            return this;
        }

        public Pago build() {
            return this.pago;
        }
    }
}