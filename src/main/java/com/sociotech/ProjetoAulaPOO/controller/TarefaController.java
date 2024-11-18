package com.sociotech.ProjetoAulaPOO.controller;


import com.sociotech.ProjetoAulaPOO.model.Prioridade;
import com.sociotech.ProjetoAulaPOO.model.Status;
import com.sociotech.ProjetoAulaPOO.model.Tarefa;
import com.sociotech.ProjetoAulaPOO.service.TarefaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tarefa") //para poder criar a rota
public class TarefaController {


    @Autowired // Para ele poder criar um objeto
    private TarefaService tarefaService; // Para puxar as funções do produto service

    // Lista todas as tarefas
    @GetMapping
    public List<Tarefa> listarTarefas(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        System.out.println("UserId extraído do token: " + userId); // Verifique se o ID está sendo extraído corretamente
        return tarefaService.listarTarefa();
    }

    // Lista as tarefas por status
    @GetMapping("/status/{status}")
    public List<Tarefa> listarTarefasPorStatus(@PathVariable Status status, HttpServletRequest request) {
        // Recupera o userId do token JWT
        String userId = (String) request.getAttribute("userId");
        System.out.println("UserId: " + userId); // Apenas para verificar se o id foi extraído corretamente
        return tarefaService.listarTarefasPorStatusOrdenadas(status);
    }

    // Inclui uma nova tarefa
    @PostMapping()
    public Tarefa incluirTarefa(@RequestBody Tarefa tarefa, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        System.out.println("UserId: " + userId); // Apenas para verificar se o id foi extraído corretamente
        tarefa.setDataCriacao(LocalDate.now());
        return tarefaService.inserirTarefa(tarefa);
    }

    // Deleta uma tarefa
    @DeleteMapping("/excluir/{id}")
    public void deletar(@PathVariable int id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        System.out.println("UserId: " + userId); // Apenas para verificar se o id foi extraído corretamente
        tarefaService.deletarTarefa(id);
    }

    // Atualiza uma tarefa
    @PutMapping("/{id}/atualizar")
    public Tarefa atualizarTarefa(@PathVariable int id, @RequestBody Tarefa tarefaAtualizada, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        System.out.println("UserId: " + userId); // Apenas para verificar se o id foi extraído corretamente
        return tarefaService.atualizarTarefa(id, tarefaAtualizada);
    }

    // Move uma tarefa
    @PutMapping("/{id}/mover")
    public Tarefa moverTarefa(@PathVariable int id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        System.out.println("UserId: " + userId); // Apenas para verificar se o id foi extraído corretamente
        return tarefaService.moverTarefa(id);
    }

    // Atualiza a prioridade de uma tarefa
    @PutMapping("/{id}/prioridade/{prioridade}")
    public Tarefa atualizarPrioridade(@PathVariable int id, @PathVariable Prioridade prioridade, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        System.out.println("UserId: " + userId); // Apenas para verificar se o id foi extraído corretamente
        return tarefaService.atualizarPrioridade(id, prioridade);
    }
}


