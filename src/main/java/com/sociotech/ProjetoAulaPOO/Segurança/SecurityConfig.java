package com.sociotech.ProjetoAulaPOO.Segurança;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configurações de segurança
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/auth/**").permitAll() // Permite o acesso às rotas de login e cadastro
                        .anyRequest().authenticated() // Requer autenticação para outras rotas
                )
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF, adequado para APIs REST
                .addFilterBefore(new FiltroJWT(jwtUtil), UsernamePasswordAuthenticationFilter.class); // Adiciona o filtro JWT

        return http.build();  // Retorna o SecurityFilterChain configurado
    }
}

