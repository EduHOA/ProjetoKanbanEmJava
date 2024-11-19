# Rotas da Aplicação

## 1. **Autenticação**

- **POST /auth/cadastro**: Cadastra um usuário utilizando o nome e senha.
  - **Exemplo**:
    ```json
    {
      "nome": "user",
      "senha": "123456"
    }
    ```

- **POST /auth/login**: Loga o usuário, informando o Bearer Token.

---

## 2. **Tarefas**

- **POST /tasks**: Cria uma nova tarefa na coluna "A Fazer".
  - **Exemplo**:
    ```json
    {
      "titulo": "Tarefa Baixa Prioridade",
      "descricao": "Exemplo de tarefa",
      "dataLimite": "2024-11-20"
    }
    ```

- **GET /tarefa**: Lista todas as tarefas organizadas por coluna.
  
- **GET /tarefa/status/{status}**: Lista todas as tarefas organizadas por Status.

- **PUT /tarefa/{id}/mover**: Move uma tarefa para a próxima coluna (Status).

- **PUT /tarefa/{id}/prioridade/{prioridade}**: Atualiza a prioridade de uma tarefa.
  - **prioridade** pode ser: `BAIXA`, `MEDIO`, `ALTO`.

- **PUT /tarefa/{id}/atualizar**: Atualiza uma tarefa (título, descrição, dataLimite).

- **DELETE /tarefa/excluir/{id}**: Exclui uma tarefa.

---
