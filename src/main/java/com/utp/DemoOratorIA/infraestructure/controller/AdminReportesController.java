
package com.utp.DemoOratorIA.infraestructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.DemoOratorIA.application.service.AdminReportesService;
import com.utp.DemoOratorIA.infraestructure.DTO.AdminReporteFinancieroDTO;
import com.utp.DemoOratorIA.infraestructure.DTO.AdminReporteIADTO;
import com.utp.DemoOratorIA.infraestructure.DTO.AdminReporteUsuariosDTO;
import com.utp.DemoOratorIA.infraestructure.entities.UserEntity;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin/reportes")
public class AdminReportesController {

    private static final Integer ROL_ADMIN = 1;

    private final AdminReportesService adminReportesService;

    public AdminReportesController(AdminReportesService adminReportesService) {
        this.adminReportesService = adminReportesService;
    }

    private boolean esAdmin(HttpSession session) {
        UserEntity usuario = (UserEntity) session.getAttribute("user");
        return usuario != null && ROL_ADMIN.equals(usuario.getIdRol());
    }

    @GetMapping("/usuarios")
    public ResponseEntity<AdminReporteUsuariosDTO> reporteUsuarios(HttpSession session) {

        if (!esAdmin(session)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(adminReportesService.getReporteUsuarios());
    }

    @GetMapping("/ia")
    public ResponseEntity<AdminReporteIADTO> reporteIA(HttpSession session) {

        if (!esAdmin(session)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(adminReportesService.getReporteIA());
    }

    @GetMapping("/financiero")
    public ResponseEntity<AdminReporteFinancieroDTO> reporteFinanciero(HttpSession session) {

        if (!esAdmin(session)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(adminReportesService.getReporteFinanciero());
    }
}