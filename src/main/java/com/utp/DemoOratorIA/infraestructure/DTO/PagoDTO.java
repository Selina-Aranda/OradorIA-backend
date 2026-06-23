package com.utp.DemoOratorIA.infraestructure.DTO;

import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.PaymentMethod;
import com.utp.DemoOratorIA.domain.model.enums.PaymentStatus;

public record PagoDTO(
        Integer idPago,
        Integer idSuscripcion,
        Double monto,
        PaymentMethod metodoPago,
        PaymentStatus estado,
        String transaccionId,
        LocalDateTime fechaPago) {
}