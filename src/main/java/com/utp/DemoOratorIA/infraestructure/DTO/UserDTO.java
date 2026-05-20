package com.utp.DemoOratorIA.infraestructure.DTO;


public record UserDTO (
    Integer id,
    Integer idRol,
    Integer idPlan,
    String nombre,
    String apellidos,
    String email,
    String password,
    String telefono,
    String fotoPerfil
) {
    
}
