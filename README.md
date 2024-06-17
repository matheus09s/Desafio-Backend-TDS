# Desafio-Backend-TDS
Desafio para vaga de desenvolvedor back-end

# API de Encurtamento de URL

Esta API fornece funcionalidades para encurtar URLs e obter estatísticas sobre as URLs encurtadas.

Ao executar o projeto, a API poderá ser acessada em localhost:8080.

O Swagger poderá ser visualizado em localhost:8080/swagger-ui.html

## Tecnologias
Spring Boot

Spring MVC

Spring Data JPA

SpringDoc OpenAPI 3

Mysql

MockMvc

JUnit 5


## Endpoints

### POST /shorten-url

Recebe uma URL longa como entrada e retorna uma URL encurtada.

#### Request Body

```json
{
    "longUrl": "http://exemplo.com"
}
```
#### Response Body


```json
{
    "shortUrl": "xxx:8080/{id}"
}
```

### GET /{id}

Redireciona o usuário para a **URL longa original** associada ao ID fornecido. Se a URL não for encontrada, retorna um status **404 NOT FOUND**.

### GET  /stats/{id}

Retorna estatísticas sobre a URL encurtada associada ao ID fornecido. As estatísticas incluem o **número total de acessos e a média de acessos por dia**. Se a URL não for encontrada, retorna um status **404 NOT FOUND**.

#### Response Body

```json
{
    "totalAccesses": "totalDeAcessos",
    "averageAccessesPerDay": "mediadeAcessosPorDia"
}
```

# Testes da API

Os testes foram implementados usando o framework JUnit 5 e a biblioteca MockMvc do Spring Boot. Aqui estão os detalhes de cada teste:

## 1. Teste de Cadastro de URL (cadastrarUrl)

Este teste verifica se a API é capaz de encurtar uma URL. Ele envia uma requisição POST para a rota `/shorten-url` com um JSON contendo uma URL longa. O teste então verifica se a resposta tem status 200 (OK) e se a URL encurtada retornada começa com `http://localhost/`.

## 2. Teste de Direcionamento (verificarDirecionamento)

Este teste verifica se a API redireciona corretamente uma URL encurtada para a URL longa original. Ele envia uma requisição GET para a rota `/{shortUrlId}` e verifica se a resposta tem status 302 (Found) e se o cabeçalho "Location" corresponde à URL longa original.

## 3. Teste de Verificação de Estatísticas (verificarTotalAcessosMedia)

Este teste verifica se a API retorna corretamente as estatísticas de uma URL encurtada. Ele envia uma requisição GET para a rota `/stats/{shortUrlId}` e verifica se a resposta tem status 200 (OK) e se os campos `totalAccesses` e `averageAccessesPerDay` estão corretos.

## 4. Teste de Verificação de ID (verificarId)

Este teste verifica se a API retorna corretamente um erro quando uma URL encurtada não existente é solicitada. Ele envia uma requisição GET para a rota `/{nonExistentShortUrlId}` e verifica se a resposta tem status 404 (Not Found).



