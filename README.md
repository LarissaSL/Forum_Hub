# 🗣️ Forum Hub

O projeto Forum Hub é uma aplicação web que permite aos usuários criar, visualizar e interagir em tópicos de discussão. Abaixo estão os tópicos detalhados sobre como configurar e utilizar a aplicação.

## 📌 Primeiros Passos

### 1. Configuração do Banco de Dados

#### 1.1. Configuração do Banco de Dados
1. Abra o MySQL Workbench ou outro cliente MySQL de sua preferência.
2. Crie um novo banco de dados com o nome `forum_hub`:
   ```sql
   CREATE DATABASE forum_hub;
OBS.: As Migrations irão criar as Tabelas necessárias


3. Crie usuarios para você conseguir fazer login

### 1.2. Configuração do Banco na Aplicação

Em `application.properties`, configure as seguintes propriedades com os valores apropriados para a sua instalação do MySQL:

```properties
spring.application.name=forum_hub

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost/forum_hub
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

server.error.include-stacktrace=never

api.security.token.secret=${JWT_SECRET}
```
OBS.: Caso não tenha essas variaveis de ambiente definidas por favor escreve as configurações ex.:
```properties
spring.datasource.username=seuUsername
```

### 🖥️ 2. Utilizando a Aplicação

#### 2.1. Inicialização
Inicie a aplicação Forum Hub pela sua IDE executando a classe `ForumHubApplication`.

#### 2.2. Criação de Usuários
Para inserir usuários manualmente no banco de dados, você pode usar uma instrução SQL como esta, substituindo os valores por dados fictícios:

```sql
INSERT INTO usuarios (email, senha) VALUES ('exemplo@usuario.com', 'senha123');
```

OBS.: Crie a senha no formato bcrypt: [Clique aqui para criar](https://bcrypt.online)

### 2.3. Utilizando a aplicação

Acesse a URL: [http://localhost:8080/login](http://localhost:8080/login) e no corpo (Body) da requisição, insira o login e senha para autenticação.

Por exemplo, no corpo da requisição (Body) do Postman:

```json
{
  "login": "email@example.com",
  "senha": "$2y$10$7GqKyY22w6mcAQssIwNHw.V/1UvEZjq.RfDuNtJkTp8FxE4Sxbohe"
}
```

Habilite nas rotas o Cabeçalho de autenticacao e cole o Token gerado

### 📖 3. Endpoints da API

| Método | Endpoint       | Descrição                      | Dados Esperados                                      |
|--------|----------------|--------------------------------|-------------------------------------------------------|
| POST   | /topicos       | Criação de novos tópicos       | "titulo": "seuTitulo", "mensagem": "suaMensagem", "curso": "seuCurso" |
| GET    | /topicos       | Listagem de todos os tópicos   | N/A |
| GET    | /topicos/{id}  | Detalhamento de um tópico específico | N/A |
| PUT    | /topicos/{id}  | Atualização de um tópico       | "titulo": "seuTitulo", "mensagem": "suaMensagem", "status": (Pode ser: "RESPONDIDO, NAO_RESPONDIDO, EXCLUIDO) |
| DELETE | /topicos/{id}  | Exclusão de um tópico          | N/A                         |
| POST   | /login         | Autenticação de usuários       | "login": "seuLogin", "senha": "suaSenha" |

### ⌨️ **Desenvolvimento**

Passos seguidos para o Desenvolvimento:

1. Planejamento da arquitetura.
   
2. Criação do Controller.
   
3. Implementação do back-end utilizando Spring Boot.
   
4. Testes via Insomnia.
   
   <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postman/postman-original.svg" alt="Postman" width="50"/>

5. Criação de validações e segurança com Spring Security.
   
6. Testes finais e ajustes.

### 📚 **Tecnologias Utilizadas**

- Java 17

   <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" alt="Java" width="50"/>

- Spring Boot 3.x

   <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" alt="Spring Boot" width="50"/>

- MySQL 8.x

   <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" alt="MySQL" width="50"/>



### Materiais de Apoio Utilizados 🎥

Aqui estão alguns vídeos e cursos que utilizei como apoio durante o desenvolvimento:

- [Curso Alura: Spring Boot 3: Desenvolva uma API Rest em Java](https://www.alura.com.br/curso-online-spring-boot-api-rest-java)
- [Curso Alura: Spring Boot: Aplique boas práticas e proteja sua API Rest](https://www.alura.com.br/curso-online-spring-boot-seguranca-api-rest)
- [Curso Alura: Spring Boot 3: Documente, teste e prepare a API para o deploy](https://www.alura.com.br/curso-online-spring-boot-documente-teste-deploy)
