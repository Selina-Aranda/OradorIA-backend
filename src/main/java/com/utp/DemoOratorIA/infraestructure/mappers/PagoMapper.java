package com.utp.DemoOratorIA.infraestructure.mappers;

import org.springframework.stereotype.Component;

import com.utp.DemoOratorIA.domain.model.aggregate.Pago;
import com.utp.DemoOratorIA.infraestructure.entities.PagoEntity;

@Component
public class PagoMapper {

    public Pago toDomain(PagoEntity entity) {

        if (entity == null)
            return null;

        return new Pago.Builder()
                .idPago(entity.getIdPago())
                .idSuscripcion(entity.getIdSuscripcion())
                .monto(entity.getMonto())
                .metodoPago(entity.getMetodoPago())
                .estado(entity.getEstado())
                .transaccionId(entity.getTransaccionId())
                .fechaPago(entity.getFechaPago())
                .build();
    }

    public PagoEntity toEntity(Pago pago) {

        if (pago == null)
            return null;

        return PagoEntity.builder()
                .idPago(pago.getIdPago())
                .idSuscripcion(pago.getIdSuscripcion())
                .monto(pago.getMonto())
                .metodoPago(pago.getMetodoPago())
                .estado(pago.getEstado())
                .transaccionId(pago.getTransaccionId())
                .fechaPago(pago.getFechaPago())
                .build();
    }
}