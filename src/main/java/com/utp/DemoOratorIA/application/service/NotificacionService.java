
package com.utp.DemoOratorIA.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.Notificacion;
import com.utp.DemoOratorIA.domain.model.repositories.INotificacionRepository;

@Service
public class NotificacionService {

    private final INotificacionRepository notificacionRepository;

    public NotificacionService(INotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    public Notificacion save(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> listar() {
        return notificacionRepository.list();
    }

    public Optional<Notificacion> findById(Integer id) {
        return notificacionRepository.findById(id);
    }

    public Notificacion update(Notificacion notificacion) {
        return notificacionRepository.update(notificacion);
    }

    public void delete(Integer id) {
        notificacionRepository.delete(id);
    }
}