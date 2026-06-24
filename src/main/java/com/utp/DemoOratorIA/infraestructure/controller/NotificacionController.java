
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

import com.utp.DemoOratorIA.application.service.NotificacionService;
import com.utp.DemoOratorIA.domain.model.aggregate.Notificacion;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @GetMapping
    public List<Notificacion> listar() {
        return notificacionService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerPorId(@PathVariable Integer id) {

        return notificacionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Notificacion> guardar(
            @RequestBody Notificacion notificacion) {

        Notificacion nuevaNotificacion =
                notificacionService.save(notificacion);

        return ResponseEntity.ok(nuevaNotificacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizar(
            @PathVariable Integer id,
            @RequestBody Notificacion notificacion) {

        notificacion.setIdNotificacion(id);

        Notificacion actualizada =
                notificacionService.update(notificacion);

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        notificacionService.delete(id);

        return ResponseEntity.noContent().build();
    }
}