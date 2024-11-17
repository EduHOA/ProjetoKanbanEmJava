// aqui receberá as classes vindas do meu bando de dados. aqui é conversado com o banco. o jpa repository traduz o banco de dados sql
// quem vai consumir essa interface é a camada de serviço

package com.sociotech.ProjetoAulaPOO.repository;

import com.sociotech.ProjetoAulaPOO.model.Status;
import com.sociotech.ProjetoAulaPOO.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    // Consulta customizada: Filtra as tarefas pelo status e ordena pela prioridade #help na IA
    @Query("SELECT t FROM Tarefa t WHERE t.status = :status ORDER BY t.prioridade")
    List<Tarefa> findByStatusOrdered(@Param("status") Status status);

}
