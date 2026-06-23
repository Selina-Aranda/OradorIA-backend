
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

import com.utp.DemoOratorIA.application.service.ReporteService;
import com.utp.DemoOratorIA.domain.model.aggregate.Reporte;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public List<Reporte> listar() {
        return reporteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> obtenerPorId(@PathVariable Integer id) {

        Reporte reporte = reporteService.findById(id);

        if (reporte == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reporte);
    }

    @PostMapping
    public ResponseEntity<Reporte> guardar(@RequestBody Reporte reporte) {

        Reporte nuevoReporte = reporteService.save(reporte);

        return ResponseEntity.ok(nuevoReporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> actualizar(
            @PathVariable Integer id,
            @RequestBody Reporte reporte) {

        reporte.setIdReporte(id);

        Reporte reporteActualizado = reporteService.update(reporte);

        return ResponseEntity.ok(reporteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        reporteService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
