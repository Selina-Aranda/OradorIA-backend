package com.utp.DemoOratorIA.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.Plan;
import com.utp.DemoOratorIA.domain.model.aggregate.User;
import com.utp.DemoOratorIA.domain.model.repositories.IPlanRepository;
import com.utp.DemoOratorIA.domain.model.repositories.IUserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ActividadRecienteService actividadService;
    private final IPlanRepository planRepository;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder,
            ActividadRecienteService actividadService, IPlanRepository planRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.actividadService = actividadService;
        this.planRepository = planRepository;
    }

    public User save(User user) {
        // HASHEAR PASSWORD
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    public List<User> listar() {
        return userRepository.list();
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public User update(User user) {

        User existing = userRepository.findById(user.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existing.setNombres(user.getNombres());
        existing.setApellidos(user.getApellidos());
        existing.setEmail(user.getEmail());
        existing.setIdRol(user.getIdRol());
        existing.setEstado(user.getEstado());

        // HASHEAR PASSWORD
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        actividadService.registrar(
                existing.getIdUsuario(),
                "Actualización de perfil",
                "COMPLETADO");

        return userRepository.update(existing);
    }

    @Transactional
    public void delete(Integer id) {

        actividadService.deleteByUsuarioId(id);

        userRepository.delete(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public long contarUsuarios() {
        return userRepository.countUsuarios();
    }

    public long contarUsuariosPremiun() {
        return userRepository.countPremiunUsers();
    }

    public Plan obtenerPlanUsuario(Integer idUsuario) {

        User user = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return planRepository.findById(user.getIdPlan())
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));
    }

}
