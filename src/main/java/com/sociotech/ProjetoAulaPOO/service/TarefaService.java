package com.sociotech.ProjetoAulaPOO.service;

import com.sociotech.ProjetoAulaPOO.model.Prioridade;
import com.sociotech.ProjetoAulaPOO.model.Status;
import com.sociotech.ProjetoAulaPOO.model.Tarefa;
import com.sociotech.ProjetoAulaPOO.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired //Anotation que cria um novo objeto, não se usa mais o (new produto...)
    private TarefaRepository tarefaRepository; //assim extrai os metodos de operação dpo banco, serve como dependencia

    public List<Tarefa> listarTarefa() {
        List<Tarefa> tarefas = tarefaRepository.findAll(); // Recupera todas as tarefas

        // Ordena as tarefas dentro de cada coluna por prioridade
        return tarefas.stream()
                .sorted((t1, t2) -> t1.getPrioridade().compareTo(t2.getPrioridade()))
                .collect(Collectors.toList());
    }

        public List<Tarefa> listarTarefasPorStatusOrdenadas(Status status) {
            return tarefaRepository.findByStatusOrdered(status); // Usa o método do repositório


        // Outros métodos, como listarTarefas, inserirTarefa, etc.
    }

    public Tarefa inserirTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa findById(int id) {
        return tarefaRepository.findById(id).orElse(null);
    }

    public Tarefa save(Tarefa product) {
        return tarefaRepository.save(product);
    }

    public void deletarTarefa(int id) {
        tarefaRepository.deleteById(id);
    }

    public Tarefa buscarTarefaPorId(int id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa com ID " + id + " não encontrada."));
    }

    public Tarefa atualizarTarefa(int id, Tarefa tarefaAtualizada) {
        Tarefa tarefa = buscarTarefaPorId(id);  // Busca a tarefa pelo ID
        if (tarefa == null) {
            throw new NoSuchElementException("Tarefa não encontrada com o ID: " + id);
        }

        // Atualiza os campos da tarefa com os dados passados
        tarefa.setTitulo(tarefaAtualizada.getTitulo());
        tarefa.setDescricao(tarefaAtualizada.getDescricao());
        tarefa.setDataLimite(tarefaAtualizada.getDataLimite());

        // Salva a tarefa atualizada no banco de dados
        return tarefaRepository.save(tarefa);
    }


    public Tarefa moverTarefa(int id) {
        Tarefa tarefa = buscarTarefaPorId(id); // Busca a tarefa pelo ID
        if (tarefa == null) {
            throw new NoSuchElementException("Tarefa não encontrada com o ID: " + id);
        }

        switch (tarefa.getStatus()) {
            case FAZER:
                tarefa.setStatus(Status.PROGRESSO);
                break;
            case PROGRESSO:
                tarefa.setStatus(Status.FEITO);
                break;
            case FEITO:
                throw new IllegalStateException("Tarefa já está no estado CONCLUIDO.");
        }

        return tarefaRepository.save(tarefa); // Atualiza a tarefa no banco
    }

    public Tarefa atualizarPrioridade(int id, Prioridade prioridade) {
        Tarefa tarefa = buscarTarefaPorId(id);  // Busca a tarefa pelo ID
        if (tarefa == null) {
            throw new NoSuchElementException("Tarefa não encontrada com o ID: " + id);
        }

        // Atualiza a prioridade da tarefa
        tarefa.setPrioridade(prioridade);

        // Salva a tarefa com a nova prioridade
        return tarefaRepository.save(tarefa);
    }

}
