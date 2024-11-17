package com.sociotech.ProjetoAulaPOO.service;

import com.sociotech.ProjetoAulaPOO.Segurança.JwtUtil;
import com.sociotech.ProjetoAulaPOO.model.Usuario;
import com.sociotech.ProjetoAulaPOO.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;  // Utilitário para gerar e validar JWT

    public Usuario cadastrarUsuario(Usuario usuario) {
        try {
            return userRepository.save(usuario);  // Persiste o usuário no banco
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o usuário: " + e.getMessage());
        }
    }

    public Usuario autenticarUsuario(String nome, String senha) {
        Usuario usuario = userRepository.findByNome(nome); // Encontre o usuário pelo nome
        if (usuario != null && usuario.getSenha().equals(senha)) { // Verifique se a senha bate
            return usuario;
        }
        return null; // Retorna null se as credenciais estiverem erradas
    }

}
