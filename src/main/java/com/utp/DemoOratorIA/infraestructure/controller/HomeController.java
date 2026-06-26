package com.utp.DemoOratorIA.infraestructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.utp.DemoOratorIA.infraestructure.entities.UserEntity;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/main")
    public String root(HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("user");

        if (user != null && user.getIdRol() != null && user.getIdRol() == 1) {
            return "redirect:/admin-dashboard";
        }

        return "main";
    }

    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null || user.getIdRol() == null || user.getIdRol() != 1) {
            return "redirect:/Login";
        }
        return "admin-dashboard";
    }

    @GetMapping("/mainUser")
    public String mainUser(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/Login";
        }
        return "mainUser";
    }
}
