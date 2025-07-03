package com.ecomarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF para APIs REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()  // Rutas públicas
                        .requestMatchers("/api/productos/**").authenticated()  // Requiere autenticación
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")  // Solo admin
                        .anyRequest().authenticated()  // Todas las demás rutas requieren autenticación
                )
                .formLogin(form -> form.disable())  // Deshabilitar formulario de login
                .httpBasic(httpBasic -> httpBasic.disable());  // Deshabilitar autenticación básica

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}