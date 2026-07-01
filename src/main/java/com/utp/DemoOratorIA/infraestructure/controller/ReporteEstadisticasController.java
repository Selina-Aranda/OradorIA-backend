
package com.utp.DemoOratorIA.infraestructure.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.utp.DemoOratorIA.application.service.ReporteEstadisticasService;
import com.utp.DemoOratorIA.application.service.ReporteService;
import com.utp.DemoOratorIA.domain.model.aggregate.Reporte;
import com.utp.DemoOratorIA.domain.model.enums.ReportType;
import com.utp.DemoOratorIA.infraestructure.DTO.ReporteResumenDTO;
import com.utp.DemoOratorIA.infraestructure.entities.UserEntity;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/reportes")
public class ReporteEstadisticasController {

    private final ReporteEstadisticasService reporteEstadisticasService;
    private final ReporteService reporteService;

    public ReporteEstadisticasController(
            ReporteEstadisticasService reporteEstadisticasService,
            ReporteService reporteService) {

        this.reporteEstadisticasService = reporteEstadisticasService;
        this.reporteService = reporteService;
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<ReporteResumenDTO> obtenerEstadisticas(HttpSession session) {

        UserEntity usuario = (UserEntity) session.getAttribute("user");

        if (usuario == null) {
            return ResponseEntity.status(401).build();
        }

        ReporteResumenDTO estadisticas = reporteEstadisticasService.getEstadisticasReporte(usuario.getId());

        return ResponseEntity.ok(estadisticas);
    }

    @PostMapping("/exportar")
    public ResponseEntity<Reporte> registrarExportacion(
            @RequestParam(defaultValue = "all") String periodo,
            HttpSession session) {

        UserEntity usuario = (UserEntity) session.getAttribute("user");

        if (usuario == null) {
            return ResponseEntity.status(401).build();
        }

        Reporte reporte = new Reporte.Builder()
                .idUsuario(usuario.getId())
                .titulo("Reporte de estadísticas - " + periodo)
                .descripcion("Reporte de evolución de oratoria exportado por el usuario, período: " + periodo)
                .tipo(ReportType.INDIVIDUAL)
                .fechaGeneracion(LocalDateTime.now())
                .build();

        Reporte guardado = reporteService.save(reporte);

        return ResponseEntity.ok(guardado);
    }
}