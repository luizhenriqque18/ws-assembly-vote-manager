# ws-assembly-vote-manager

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=luizhenriqque18_ws-assembly-vote-manager&metric=coverage)](https://sonarcloud.io/summary/new_code?id=luizhenriqque18_ws-assembly-vote-manager) [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=luizhenriqque18_ws-assembly-vote-manager&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=luizhenriqque18_ws-assembly-vote-manager) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=luizhenriqque18_ws-assembly-vote-manager&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=luizhenriqque18_ws-assembly-vote-manager) [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=luizhenriqque18_ws-assembly-vote-manager&metric=bugs)](https://sonarcloud.io/summary/new_code?id=luizhenriqque18_ws-assembly-vote-manager) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=luizhenriqque18_ws-assembly-vote-manager&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=luizhenriqque18_ws-assembly-vote-manager) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=luizhenriqque18_ws-assembly-vote-manager&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=luizhenriqque18_ws-assembly-vote-manager)

## Sobre a API
E uma API para gerir decisões tomadas por votação em assembleias. É construído com Java, Spring Boot, e Spring Framework. O URL principal da API `/ws-assembly-vote-manager`.

## Características

Esta API fornece endpoints HTTP e ferramentas para o seguinte:

* Pauta
  * Criar : `POST/ws-assembly-vote-manager/pauta`
  * Atualizar : `PUT/ws-assembly-vote-manager/pauta`
  * Encontrar Por Id: `GET/ws-assembly-vote-manager/pauta/{id}`
  * Listar Paginada: `GET/ws-assembly-vote-manager/pauta`
* Sessão Votação
  * Criar : `POST/ws-assembly-vote-manager/sessao-votacao`
  * Atualizar : `PUT/ws-assembly-vote-manager/sessao-votacao`
  * Encontrar Por Id: `GET/ws-assembly-vote-manager/sessao-votacao/{id}`
  * Listar Paginada: `GET/ws-assembly-vote-manager/pauta`
* Voto
  * Criar : `POST/ws-assembly-vote-manager/voto`
* Associado
    * Criar : `POST/ws-assembly-vote-manager/associado`
    * Atualizar : `PUT/ws-assembly-vote-manager/associado`
    * Encontrar Por Id: `GET/ws-assembly-vote-manager/associado/{id}`
    * Listar Paginada: `GET/ws-assembly-vote-manager/associado`

### Detalhes
#### Sessão Votação

- `POST/ws-assembly-vote-manager/sessao-votacao`

Este end-point é chamado para criar uma Sessão Votação, caso duracao não seja definida sera registrado um minuto na mesma.

**Body:**

```json
{
  "titulo": "Assembleia 1", 
  "duracao": "00:10:00"
}
```

#### Pauta 

- `POST/ws-assembly-vote-manager/pauta`

Este end-point é chamado para criar uma Pauta.

**Body:**

```json
{
  "titulo": "Troca de cadeiras 1",
  "descricao": "Já",
  "sessaoVotacaoId": "2053d1e8-225a-46b5-bbab-fa4d175c809a"
}
```

#### Sessão Votação

- `PUT/ws-assembly-vote-manager/sessao-votacao`

Este end-point é chamado para atualizar uma Sessão Votação, end-point também usado para mudar a duração da sessão e inicializar a mesma com o status `OPEN`.

**Body:**

```json
{
  "titulo": "Assembleia 1",
  "duracao": "00:00:20",
  "status": "OPEN",
  "pautaId": "f82293f9-ed93-45f0-8473-c2e13f9eb9e3"
}
```

#### Voto

- `POST/ws-assembly-vote-manager/voto`

Este end-point é chamado para criar um Voto, será permitido um voto por associado.

**Body:**

```json
{
  "pautaId": "f82293f9-ed93-45f0-8473-c2e13f9eb9e3",
  "associadoId": "acf5286f-14f2-4503-b9d0-5b36c89262a4",
  "situacao": "SIM"
}
```

#### Associado

- `POST/ws-assembly-vote-manager/associado`

Este end-point é chamado para criar um Associado, será permitido um cpf por associado.

**Body:**

```json
{
  "nome": "Associado X",
  "cpf": "08223584093"
}
```

### Tecnologias utilizadas

Este projeto foi desenvolvido com:

* **Java 8 (Java Development Kit - JDK: 8)**
* **Spring Boot 2.7.9**
* **Maven**
* **JUnit 5**
* **PostgreSQL 13**
* **Flyway**
* **H2**
* **AWS — Elastic Beanstalk** [Endereço de Acesso](http://ws-assembly-vote-manager.eba-9q7mtrwr.us-east-1.elasticbeanstalk.com/ws-assembly-vote-manager)

### Compilação e empacotamento

A API também foi desenvolvido para funcionar com um `jar`. Para gerar este `jar`, deve correr:

```bash
mvn package
```

Limpará, compilará e gerará um `jar` no diretório de destino, por exemplo `ws-assembly-vote-manager-0.0.1-SNAPSHOT.jar`.

### Execução

Quando a aplicação estiver em execução **Flyway*** criará as tabelas necessárias para a criação das palavras e a execução da comparação entre os pontos finais. No perfil de dev, a aplicação utiliza **H2*** base de dados (dados em memória).

### Test

* Para a fase de teste da unidade, pode correr:

```bash
mvn test
```

### Run

Para executar o API, executar o `jar` simplesmente como se segue:

```bash
java -jar ws-assembly-vote-manager-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

or

```bash
mvn spring-boot:run -Dspring.profiles.active=dev
```

* **Docker-compose**

Inicializar Container

```bash
docker-compose up -d
```

* **Dockerhub**

```bash
docker run --rm -p 8082:8082 -e "JAVA_OPTS=-Dspring.profiles.active=dev" luiz0s/ws-assembly-vote-manager:latest --server.port=8082
```

Por padrão, a API estará disponível em [http://localhost:8082/ws-assembly-vote-manager](http://localhost:8082/ws-assembly-vote-manager)








