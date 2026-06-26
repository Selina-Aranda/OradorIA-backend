package com.utp.DemoOratorIA.application.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.utp.DemoOratorIA.domain.model.aggregate.ActividadReciente;
import com.utp.DemoOratorIA.domain.model.aggregate.User;
import com.utp.DemoOratorIA.domain.model.repositories.IActividadRecienteRepository;
import com.utp.DemoOratorIA.domain.model.repositories.IUserRepository;
import com.utp.DemoOratorIA.infraestructure.DTO.ActividadRecienteDTO;
import com.utp.DemoOratorIA.infraestructure.mappers.ActividadRecienteMapper;

@Service
public class ActividadRecienteService {

    private final IActividadRecienteRepository actividadReciente;
    private final IUserRepository userRepository;
    private final ActividadRecienteMapper mapper;

    public ActividadRecienteService(IActividadRecienteRepository actividadReciente, IUserRepository userRepository,
            ActividadRecienteMapper mapper) {
        this.actividadReciente = actividadReciente;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public ActividadReciente save(ActividadReciente ar) {
        return actividadReciente.save(ar);
    }

    public List<ActividadRecienteDTO> listar() {

        List<ActividadRecienteDTO> lista = actividadReciente.list().stream()
                .map(a -> {

                    User usuario = userRepository.findById(a.getIdUsuario())
                            .orElse(null);

                    String nombre = (usuario != null)
                            ? usuario.getNombres() + " " + usuario.getApellidos()
                            : "Usuario eliminado";

                    return mapper.toDTO(a, nombre);
                })
                .sorted(Comparator.comparing(ActividadRecienteDTO::fecha).reversed())
                .limit(10)
                .toList();

        System.out.println("Actividades encontradas: " + lista.size());

        lista.forEach(System.out::println);

        return lista;
    }

    public Optional<ActividadReciente> findById(Integer id) {
        return actividadReciente.findById(id);
    }

    public ActividadReciente update(ActividadReciente ar) {
        return actividadReciente.update(ar);
    }

    public void delete(Integer id) {
        actividadReciente.delete(id);
    }

    // NUEVO MÉTODO
    public void registrar(Integer idUsuario,
            String actividad,
            String estado) {

        ActividadReciente ar = new ActividadReciente();

        ar.setIdUsuario(idUsuario);
        ar.setActividad(actividad);
        ar.setEstado(estado);
        ar.setFecha(LocalDateTime.now());

        save(ar);
    }

    public void deleteByUsuarioId(Integer idUsuario) {
        actividadReciente.deleteByUsuarioId(idUsuario);
    }

}
