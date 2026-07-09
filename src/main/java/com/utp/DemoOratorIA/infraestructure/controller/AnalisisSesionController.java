
package com.utp.DemoOratorIA.infraestructure.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.DemoOratorIA.application.service.AnalisisService;
import com.utp.DemoOratorIA.domain.model.aggregate.Analisis;
import com.utp.DemoOratorIA.domain.model.enums.AnalysisStatus;
import com.utp.DemoOratorIA.infraestructure.entities.UserEntity;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/analisis")
public class AnalisisSesionController {

    private final AnalisisService analisisService;

    public AnalisisSesionController(AnalisisService analisisService) {
        this.analisisService = analisisService;
    }

    public record IniciarAnalisisRequest(String titulo, String descripcion) {}

    public record FinalizarAnalisisRequest(Integer duracionSegundos) {}

    @PostMapping("/iniciar")
    public ResponseEntity<Analisis> iniciar(
            @RequestBody(required = false) IniciarAnalisisRequest request,
            HttpSession session) {

        UserEntity usuario = (UserEntity) session.getAttribute("user");

        if (usuario == null) {
            return ResponseEntity.status(401).build();
        }

        String titulo = (request != null && request.titulo() != null && !request.titulo().isBlank())
                ? request.titulo()
                : "Sesión de práctica";

        String descripcion = request != null ? request.descripcion() : null;

        Analisis nuevo = new Analisis.Builder()
                .idUsuario(usuario.getId())
                .titulo(titulo)
                .descripcion(descripcion)
                .fechaAnalisis(LocalDateTime.now())
                .estado(AnalysisStatus.PROCESANDO)
                .build();

        Analisis guardado = analisisService.save(nuevo);

        return ResponseEntity.ok(guardado);
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Analisis> finalizar(
            @PathVariable Integer id,
            @RequestBody FinalizarAnalisisRequest request,
            HttpSession session) {

        UserEntity usuario = (UserEntity) session.getAttribute("user");

        if (usuario == null) {
            return ResponseEntity.status(401).build();
        }

        Analisis existente = analisisService.findById(id).orElse(null);

        if (existente == null || !existente.getIdUsuario().equals(usuario.getId())) {
            return ResponseEntity.notFound().build();
        }

        existente.setDuracionSegundos(request.duracionSegundos());
        existente.setEstado(AnalysisStatus.COMPLETADO);

        Analisis actualizado = analisisService.update(existente);

        return ResponseEntity.ok(actualizado);
    }
}