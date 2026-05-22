package com.utp.DemoOratorIA.infraestructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.utp.DemoOratorIA.domain.model.aggregate.User;
import com.utp.DemoOratorIA.domain.model.repositories.IUserRepository;
import com.utp.DemoOratorIA.infraestructure.entities.UserEntity;
import com.utp.DemoOratorIA.infraestructure.repositories.JPAUserRepository;

import jakarta.servlet.http.HttpSession;

@Controller

public class LoginController {

    @Autowired
    private JPAUserRepository repo;

    @Autowired
    private IUserRepository userRepository;

    @GetMapping({ "/Login", "/login" })
    public String mostrarLogin() {
        return "Login";
    }

    @PostMapping({ "/Login", "/login" })
    public String login(@RequestParam String email,
            @RequestParam(required = false) String password,
            HttpSession session,
            Model model) {

        UserEntity user = repo.findByEmail(email);

        if (user != null) {
            session.setAttribute("user", user);
            if (user.getIdRol() != null && user.getIdRol() == 1) {
                return "redirect:/admin-dashboard";
            } else {
                return "redirect:/mainUser";
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "Login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/Login";
    }

    // registro

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("user", new User());
        return "Login";
    }

    @PostMapping("/registro")
    public String guardarRegistro(@ModelAttribute User user) {
        userRepository.save(user);
        return "Login";
    }
}
