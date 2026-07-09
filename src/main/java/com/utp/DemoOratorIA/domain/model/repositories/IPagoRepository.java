package com.utp.DemoOratorIA.domain.model.repositories;

import com.utp.DemoOratorIA.domain.model.aggregate.Pago;

public interface IPagoRepository extends ICRUD<Pago> {
    Double obtenerIngresosMensuales(int anio, int mes);
}