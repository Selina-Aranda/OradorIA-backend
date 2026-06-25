package com.utp.DemoOratorIA.application.service;

import java.util.List;
import java.util.Optional;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.User;
import com.utp.DemoOratorIA.domain.model.repositories.IUserRepository;

@Service
public class UserService {
    
    private final IUserRepository userRepository;
     private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
         this.passwordEncoder = passwordEncoder;
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> listar(){
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

        // SOLO actualizar password si viene nueva
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.update(existing);
    }
    public void delete(Integer id) {
        userRepository.delete(id);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
