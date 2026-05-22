package com.utp.DemoOratorIA.infraestructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.utp.DemoOratorIA.domain.model.aggregate.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/main")
    public String root(HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user != null) {

            // Admin
            if (user.getIdRol() == 1) {
                return "redirect:/admin-dashboard";
            }

            // Usuario normal
            return "main";
        }

        // Sin sesión
        return "main";
    }
}
