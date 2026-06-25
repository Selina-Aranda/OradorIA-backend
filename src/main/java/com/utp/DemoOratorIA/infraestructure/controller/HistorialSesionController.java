package com.utp.DemoOratorIA.infraestructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return historialSesionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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