package com.utp.DemoOratorIA.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.User;
import com.utp.DemoOratorIA.domain.model.repositories.IUserRepository;

@Service
public class UserService {
    
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> listar(){
        return userRepository.list();
    }
    public User findById(Integer id) {
        return userRepository.findById(id);
    }
    public User update(User user) {
        return userRepository.update(user);
    }
    public void delete(Integer id) {
        userRepository.delete(id);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
