package com.sociotech.ProjetoAulaPOO.Segurança;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroJWT extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Ignora as rotas de login e cadastro
        if (request.getRequestURI().startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");

        // Verifica se o token está presente e começa com "Bearer "
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove o prefixo "Bearer "

            try {
                if (!jwtUtil.tokenExpirado(token)) {
                    // Se o token for válido, extrai o ID do usuário e o coloca como atributo na requisição
                    request.setAttribute("userId", jwtUtil.extrairUserId(token));
                } else {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.getWriter().write("Token expirado.");
                    return;
                }
            } catch (Exception e) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Token inválido.");
                return;
            }
        } else {
            // Se o token não estiver presente ou não for válido
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Token não fornecido.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}