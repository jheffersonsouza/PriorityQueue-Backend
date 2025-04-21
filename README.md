# Desafio Backend - Sistema de Fila com Prioridade

O enunciado deste desafio foi criado em 21/04/2025 com auxílio do ChatGPT, baseado no vídeo do Guilherme Negreiros: [ESTUDANDO ESTRUTURA DE DADOS EM JAVA: CRIANDO UMA FILA PREFERENCIAL](https://www.youtube.com/watch?v=2tqIEoK_Dxc).

## Objetivo

Desenvolver um sistema backend que gerencie uma fila de usuários com diferentes níveis de prioridade (`HIGH` e `NORMAL`). A escolha do método de armazenamento dos dados é livre.

O sistema deve ser capaz de:

- Inserir usuários na fila, respeitando suas prioridades.
- Exibir todos os usuários na fila.
- Consultar o tamanho da fila.
- Consultar um usuário específico pelo índice.

## Endpoints Requeridos

### 1. `POST /users` — Adicionar um novo usuário à fila

- **Descrição:** Insere um novo usuário na fila. Usuários com prioridade `HIGH` devem ser posicionados à direita do último usuário de prioridade `HIGH`. Usuários com prioridade `NORMAL` devem ser adicionados no final da fila.

- **Request Body (JSON):**
  ```json
  {
    "name": "Nome do Usuário",
    "priority": "HIGH" ou "NORMAL"
  }
