package com.sociotech.ProjetoAulaPOO.controller;

import com.sociotech.ProjetoAulaPOO.Segurança.JwtUtil;
import com.sociotech.ProjetoAulaPOO.model.Usuario;
import com.sociotech.ProjetoAulaPOO.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

        @Autowired
        private UserService userService;

        @Autowired
        private JwtUtil jwtUtil;

        // Rota para cadastro de novo usuário
        @PostMapping("/cadastro")
        public ResponseEntity<Object> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
            try {
                Usuario novoUsuario = userService.cadastrarUsuario(usuario);
                return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Erro ao cadastrar usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUsuario(@RequestBody Usuario user) {
        try {
            // Chama o serviço para autenticar o usuário
            Usuario usuario = userService.autenticarUsuario(user.getNome(), user.getSenha());

            if (usuario != null) {
                // Gera o token JWT
                String token = jwtUtil.gerarToken(usuario.getNome());
                return ResponseEntity.ok().body("Bearer " + token); // Retorna o token gerado
            } else {
                return new ResponseEntity<>("Nome ou senha incorretos", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao realizar o login: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
