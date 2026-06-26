package com.utp.DemoOratorIA.infraestructure.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.utp.DemoOratorIA.domain.model.aggregate.User;
import com.utp.DemoOratorIA.domain.model.enums.UserStatus;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping({ "/Login", "/login" })
    public String mostrarLogin() {
        return "login";
    }

   @PostMapping({ "/Login", "/login" })
    public String login(@RequestParam String email,
                        @RequestParam(required = false) String password,
                        HttpSession session,
                        Model model) {

        UserEntity user = repo.findByEmail(email);

        if (user == null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }

        if (password == null || password.isBlank()) {
            model.addAttribute("error", "Ingrese la contraseña");
            return "login";
        }

        // 🔥 AQUÍ está el cambio importante
        if (!passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }

        session.setAttribute("user", user);

        if (user.getIdRol() != null && user.getIdRol() == 1) {
            return "redirect:/admin-dashboard";
        } else {
            return "redirect:/mainUser";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/main";
    }

    // registro

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/registro")
    public String guardarRegistro(User user) {

        user.setIdRol(2);

        // si tienes los setters
        user.setEstado(UserStatus.ACTIVE);
        user.setFechaRegistro(LocalDateTime.now());

        userRepository.save(user);

        return "Login";
    }
}
