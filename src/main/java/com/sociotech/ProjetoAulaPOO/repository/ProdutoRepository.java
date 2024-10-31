// aqui receberá as classes vindas do meu bando de dados. aqui é conversado com o banco. o jpa repository traduz o banco de dados sql
// quem vai consumir essa interface é a camada de serviço

package com.sociotech.ProjetoAulaPOO.repository;

import com.sociotech.ProjetoAulaPOO.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
