# Desafio Backend - Sistema de Fila com Prioridade

Este desafio foi criado em 20/04/2025 com auxílio do ChatGPT, baseado no vídeo do Guilherme Negreiros: [ESTUDANDO ESTRUTURA DE DADOS EM JAVA: CRIANDO UMA FILA PREFERENCIAL](https://www.youtube.com/watch?v=2tqIEoK_Dxc).

## Objetivo

Desenvolver um sistema backend que gerencie uma fila de usuários com diferentes níveis de prioridade (`HIGH` e `NORMAL`). A escolha da tecnologia de armazenamento dos dados é livre.

O sistema deve ser capaz de:
- Inserir usuários na fila, respeitando a prioridade.
- Exibir todos os usuários da fila.
- Consultar o tamanho da fila.
- Consultar um usuário específico pelo índice.

## Endpoints Requeridos

### 1. `POST /users`: Adicionar um novo usuário à fila

- **Descrição:** Insere um novo usuário na fila. Usuários com prioridade `HIGH` devem ser colocados imediatamente após o último usuário `HIGH`. Usuários `NORMAL` são adicionados ao final da fila.
- **Request Body (JSON):**
  ```json
  {
    "name": "Nome do Usuário",
    "priority": "HIGH" ou "NORMAL"
  }
  ```
- **Resposta (Sucesso):**
  - **Código:** 201 Created
  - **(Opcional)**: Informação sobre a posição do usuário na fila.

### 2. `GET /users`: Exibir todos os usuários da fila

- **Descrição:** Retorna todos os usuários da fila em ordem de inserção, respeitando as prioridades.
- **Resposta (JSON):**
  ```json
  [
    {"name": "João", "priority": "HIGH"},
    {"name": "Maria", "priority": "NORMAL"}
  ]
  ```
  - **Código:** 200 OK
- **Resposta (Fila Vazia):**
  - **Código:** 204 No Content

### 3. `GET /users/size`: Consultar o tamanho da fila

- **Descrição:** Retorna o número de usuários atualmente na fila.
- **Resposta (JSON):**
  ```json
  {
    "size": 0
  }
  ```
  - **Código:** 200 OK

### 4. `GET /users/{index}`: Consultar usuário pelo índice

- **Descrição:** Retorna o usuário na posição especificada.
- **Resposta (JSON):**
  ```json
  {
    "name": "João",
    "priority": "HIGH"
  }
  ```
  - **Código:** 200 OK
- **Resposta (Falha):**
  - **Código:** 404 Not Found

## Tarefas Opcionais

### 1. Documentação

- Criar a documentação da API utilizando Swagger ou outra ferramenta.
- Deve conter:
  - Descrição detalhada de cada endpoint.
  - Exemplos de requisição e resposta.
  - Detalhamento dos tipos de dados esperados.

### 2. Testes Automatizados

- Implementar testes para garantir o funcionamento correto dos endpoints.
- Cobertura de testes:
  - Inserção de usuários com diferentes prioridades.
  - Exibição correta da fila.
  - Verificação do tamanho da fila.
  - Acesso a índices inválidos.

### 3. Autenticação e Autorização (JWT)

- Implementar autenticação com **JSON Web Token (JWT)**.
- Criar o endpoint `POST /auth/login` para autenticar e gerar o token.
- Exigir token JWT para acessar qualquer endpoint de manipulação de usuários.

### 4. Cache com Redis(ou outro de sua opção)

- Utilizar **Redis** para armazenar a fila de usuários em cache.

### 5. Persistência em Banco de Dados

- Implementar persistência em um banco de dados (relacional ou não relacional).
- Registrar o usuário no banco de dados ao adicioná-lo na fila.
- Considerar o uso de ORM (ex: Spring Data).

### 6. Manejo de Erros

- Tratar erros e retornar códigos HTTP apropriados:
  - **400 Bad Request:** Dados inválidos ou incompletos.
  - **404 Not Found:** Índice de usuário inexistente.
  - **500 Internal Server Error:** Falha interna no servidor.

### 7. Escalabilidade

- Pensar em como a aplicação pode escalar com aumento de usuários.
- Avaliar se o endpoint `GET /users` continua viável em alta demanda.

### 8. Fila Não Bloqueante

- Assegurar que o sistema funcione corretamente em múltiplas instâncias.
- Considerar cenários com inserção, remoção e consulta concorrentes.

---
