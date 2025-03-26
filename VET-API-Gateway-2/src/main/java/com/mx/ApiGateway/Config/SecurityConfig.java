package com.mx.ApiGateway.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configuración CORS
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:4200"); // Permitir solicitudes desde el frontend
        corsConfig.addAllowedMethod(HttpMethod.GET);   // Permitir GET
        corsConfig.addAllowedMethod(HttpMethod.POST);  // Permitir POST
        corsConfig.addAllowedMethod(HttpMethod.PUT);   // Permitir PUT
        corsConfig.addAllowedMethod(HttpMethod.DELETE); // Permitir DELETE
        corsConfig.addAllowedHeader("*");  // Permitir todas las cabeceras

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);  // Aplica la configuración a todas las rutas
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().configurationSource(corsConfigurationSource()) // Aplica la configuración CORS
            .and()
            .authorizeRequests()
                // Configura los métodos HTTP para cada ruta
                .requestMatchers(HttpMethod.GET, "/Clientes/**", "/Mascotas/**", "/Responsable/**", "/Veterinaria/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/Clientes/**", "/Mascotas/**", "/Responsable/**", "/Veterinaria/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/Clientes/**", "/Mascotas/**", "/Responsable/**", "/Veterinaria/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/Clientes/**", "/Mascotas/**", "/Responsable/**", "/Veterinaria/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .httpBasic() // Activar autenticación básica
            .and()
            .csrf().disable(); // Desactiva CSRF si no es necesario

        return http.build();
    }
}
