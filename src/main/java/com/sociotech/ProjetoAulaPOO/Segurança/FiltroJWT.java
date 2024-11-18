package com.sociotech.ProjetoAulaPOO.Segurança;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FiltroJWT extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public FiltroJWT(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " do token
            try {
                if (!jwtUtil.tokenExpirado(token)) {
                    String userId = jwtUtil.extrairUserId(token);
                    request.setAttribute("userId", userId); // Adiciona o userId no request
                    System.out.println("Token válido, User ID: " + userId); // Log para verificar se o token é válido

                    // Autentica o usuário com base no token
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userId, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                } else {
                    System.out.println("Token expirado.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao validar o token: " + e.getMessage());
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Token inválido.");
                return;
            }
        }
        filterChain.doFilter(request, response); // Continua a cadeia de filtros
    }
}

