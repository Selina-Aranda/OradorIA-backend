package com.utp.DemoOratorIA.infraestructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.utp.DemoOratorIA.application.service.HistorialSesionService;
import com.utp.DemoOratorIA.domain.model.aggregate.HistorialSesion;

@RestController
@RequestMapping("/historial-sesiones")
public class HistorialSesionController {

    private final HistorialSesionService historialSesionService;

    public HistorialSesionController(HistorialSesionService historialSesionService) {
        this.historialSesionService = historialSesionService;
    }

    @GetMapping
    public List<HistorialSesion> listar() {
        return historialSesionService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialSesion> obtenerPorId(@PathVariable Integer id) {

        HistorialSesion historial = historialSesionService.findById(id);

        if (historial == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(historial);
    }

    @PostMapping
    public ResponseEntity<HistorialSesion> guardar(
            @RequestBody HistorialSesion historialSesion) {

        HistorialSesion nuevo = historialSesionService.save(historialSesion);

        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialSesion> actualizar(
            @PathVariable Integer id,
            @RequestBody HistorialSesion historialSesion) {

        historialSesion.setIdHistorial(id);

        HistorialSesion actualizado = historialSesionService.update(historialSesion);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        historialSesionService.delete(id);

        return ResponseEntity.noContent().build();
    }
}