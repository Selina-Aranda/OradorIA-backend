package com.utp.DemoOratorIA.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ✅ Deshabilitar CSRF
            .csrf(csrf -> csrf.disable())
            
            // ✅ Deshabilitar CORS en Spring Security (se manejará con WebMvcConfigurer)
            .cors(cors -> cors.disable())

            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                    "/",
                    "/login",
                    "/Login",
                    "/registro",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/webjars/**",
                    "/api/**",
                    "/main",
                    "/index.html",
                    "/static/**"
                )
                .permitAll()
                .anyRequest().permitAll()
            )

            .formLogin(form -> form.disable())

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
            );

        return http.build();
    }

    // ✅ Configuración CORS vía WebMvcConfigurer (CORREGIDO)
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins(
                        "http://localhost:8001", 
                        "http://127.0.0.1:8001", 
                        "http://localhost:8080",
                        "http://127.0.0.1:8080"
                    )
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                    .allowedHeaders("*")
                    .allowCredentials(true)
                    .maxAge(3600);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}