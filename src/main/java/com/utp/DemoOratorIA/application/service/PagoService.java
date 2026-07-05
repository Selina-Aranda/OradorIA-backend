package com.utp.DemoOratorIA.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.Pago;
import com.utp.DemoOratorIA.domain.model.aggregate.Suscripciones;
import com.utp.DemoOratorIA.domain.model.repositories.IPagoRepository;
import com.utp.DemoOratorIA.domain.model.repositories.ISuscripcionesRepository;

@Service
public class PagoService {

    private final IPagoRepository pagoRepository;
    private final ISuscripcionesRepository suscripcionRepository;
    private final ActividadRecienteService actividadService;

    public PagoService(IPagoRepository pagoRepository, ISuscripcionesRepository suscripcionRepository,
            ActividadRecienteService actividadService) {
        this.pagoRepository = pagoRepository;
        this.suscripcionRepository = suscripcionRepository;
        this.actividadService = actividadService;
    }

    public Pago save(Pago pago) {

        Pago nuevoPago = pagoRepository.save(pago);

        Suscripciones suscripcion = suscripcionRepository
                .findById(pago.getIdSuscripcion())
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada"));

        actividadService.registrar(
                suscripcion.getIdUsuario(),
                "PAGO",
                "Pago realizado por S/ " + pago.getMonto());

        return nuevoPago;
    }

    public List<Pago> listar() {
        return pagoRepository.list();
    }

    public Optional<Pago> findById(Integer id) {
        return pagoRepository.findById(id);
    }

    public Pago update(Pago pago) {
        return pagoRepository.update(pago);
    }

    public void delete(Integer id) {
        pagoRepository.delete(id);
    }

    public Double obtenerIngresosMensuales() {

        LocalDate hoy = LocalDate.now();

        return pagoRepository.obtenerIngresosMensuales(
                hoy.getYear(),
                hoy.getMonthValue());
    }
}