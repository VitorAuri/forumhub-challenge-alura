# ForumHub API

## Descrição
ForumHub API é uma simulação de uma API para um fórum, construída com Spring Boot. Ela permite gerenciar usuários, tópicos e mensagens, incluindo autenticação via JWT, controle de acesso baseado em roles e operações básicas de CRUD.

## Funcionalidades
- Cadastro, edição e listagem de usuários.
- Criação, edição e listagem de tópicos.
- Criação e listagem de mensagens em tópicos.
- Autenticação via JWT.
- Restrição de ações (edição/deleção de tópicos) apenas para autores.
- Persistência de dados usando JPA e MySQL.
- Migrações de banco de dados com Flyway.

## Tecnologias e Dependências
O projeto utiliza as seguintes dependências principais:

- **JWT / Segurança**
    - `com.nimbusds:nimbus-jose-jwt`
    - `com.auth0:java-jwt:4.5.0`
    - `org.springframework.boot:spring-boot-starter-security`
    - `org.springframework.boot:spring-boot-starter-oauth2-resource-server`

- **Spring Boot / Web / Dados**
    - `org.springframework.boot:spring-boot-starter-web`
    - `org.springframework.boot:spring-boot-starter-data-jpa`
    - `org.springframework.boot:spring-boot-starter-validation`

- **Banco de Dados / Migração**
    - `org.flywaydb:flyway-core`
    - `org.flywaydb:flyway-mysql`
    - `com.mysql:mysql-connector-j`

- **Desenvolvimento / Utilitários**
    - `org.springframework.boot:spring-boot-devtools` (runtime)
    - `org.projectlombok:lombok` (opcional)

- **Testes**
    - `org.springframework.boot:spring-boot-starter-test` (test)
    - `org.springframework.security:spring-security-test` (test)

## Estrutura de Pacotes
- `controller` – Endpoints REST para usuários, tópicos e mensagens.
- `service` – Lógica de negócio.
- `repository` – Repositórios JPA.
- `model` – Entidades do banco de dados.
- `security` – Configuração de segurança e filtros JWT.
- `dto` – Objetos de transferência de dados (para evitar LazyInitializationException).

## Execução
1. Configure o banco de dados MySQL no `application.properties`.
2. Execute as migrações Flyway.
3. Rode a aplicação Spring Boot.
4. Acesse os endpoints via HTTP (Postman, Insomnia ou front-end).