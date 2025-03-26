package com.mx.SalaCine.Config;

import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF
            .cors(cors -> cors.configure(http)) // Activa CORS globalmente
            .authorizeHttpRequests(auth -> auth
                //.requestMatchers("/Sala/**").authenticated() // Protege /Sala
                .anyRequest().permitAll()) // Permite otras rutas
            .httpBasic(httpBasic -> httpBasic.realmName("SalaCine")) // Activa autenticación básica
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Sin sesiones

        return http.build();
    }*/
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF
            .cors() // Habilita CORS
            .and()
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()) // Permite todas las solicitudes
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Sin sesiones

        return http.build();
	}
}
