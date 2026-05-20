package com.utp.DemoOratorIA.domain.model.aggregate;


import java.time.LocalDateTime;

import com.utp.DemoOratorIA.domain.model.enums.UserStatus;


public class User {
private Integer idUsuario;
    private Integer idRol;
    private Integer idPlan;

    private String nombres;
    private String apellidos;
    private String email;
    private String password;
    private String telefono;
    private String fotoPerfil;

    private UserStatus estado;

    private LocalDateTime fechaRegistro;
    private LocalDateTime ultimoLogin;


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public UserStatus getEstado() {
        return estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public static class Builder {
        private User user;

        public Builder() {
            this.user = new User();
        }

        public Builder idUsuario(Integer idUsuario) {
            this.user.idUsuario = idUsuario;
            return this;
        }

        public Builder idRol(Integer idRol){
            this.user.idRol = idRol;
            return this;
        }

        public Builder idPlan(Integer idPlan){
            this.user.idPlan = idPlan;
            return this;
        }

        public Builder nombres(String nombres) {
            this.user.nombres = nombres;
            return this;
        }

        public Builder apellidos(String apellidos) {
            this.user.apellidos = apellidos;
            return this;
        }

        public Builder email(String email) {
            this.user.email = email;
            return this;
        }

        public Builder password(String password) {
            this.user.password = password;
            return this;
        }

        public Builder telefono(String telefono) {
            this.user.telefono = telefono;
            return this;
        }

        public Builder fotoPerfil(String fotoPerfil) {
            this.user.fotoPerfil = fotoPerfil;
            return this;
        }

        public Builder estado(UserStatus estado) {
            this.user.estado = estado;
            return this;
        }   

        public Builder fechaRegistro(LocalDateTime fechaRegistro) {
            this.user.fechaRegistro = fechaRegistro;
            return this;
        }

        public Builder ultimoLogin(LocalDateTime ultimoLogin) {
            this.user.ultimoLogin = ultimoLogin;
            return this;
        }

        public User build() {
            return this.user;
        }
    }


}
