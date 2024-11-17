package com.sociotech.ProjetoAulaPOO.repository;

import com.sociotech.ProjetoAulaPOO.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNome(String nome);  // Método para encontrar o usuário pelo nome
}
