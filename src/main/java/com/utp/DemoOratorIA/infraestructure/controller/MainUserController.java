package com.utp.DemoOratorIA.infraestructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.utp.DemoOratorIA.application.service.UserService;

@Controller
public class MainUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/analysis-user")
    public String mostrarNuevoAnalisis() {
        return "analysisUser";
    }

    @GetMapping("/configuration-user")
    public String mostrarConfiguration() {
        return "configurationUser";
    }

    @GetMapping("/main-user")
    public String mostrarMainUsuario() {
        return "mainUser";
    }

    @GetMapping("/profile-user")
    public String mostrarPerfilUsuario() {
        return "profileUser";
    }

    @GetMapping("/record-user")
    public String mostrarHistorial() {
        return "recordUser";
    }

    @GetMapping("/reports-user")
    public String mostrarReportes() {
        return "reportsUser";
    }

    @GetMapping("/main-user-analysis")
    public String mostrarAnalisis() {
        return "admin-analysis";
    }

    @GetMapping("/admin-reports")
    public String mostrarReportesAdmin() {
        return "admin-reports";
    }

    @GetMapping("/admin-settings")
    public String mostrarConfigAdmin() {
        return "admin-settings";
    }

    @GetMapping("/admin-subscriptions")
    public String mostrarSubs() {
        return "admin-subscriptions";
    }

    @GetMapping("/admin-users")
    public String mostrarUsuarios(Model model) {
        model.addAttribute("usuarios", userService.listar());
        return "admin-users";
    }
}
