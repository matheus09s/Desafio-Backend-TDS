# Desafio-Backend-TDS
Desafio para vaga de desenvolvedor back-end

# API de Encurtamento de URL

Esta API fornece funcionalidades para encurtar URLs e obter estatísticas sobre as URLs encurtadas.

O Swagger poderá ser visualizado em localhost:8080/swagger-ui.html

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
    "shortUrl": "xxx:8080/xxx"
}
```

### GET /{id}

Redireciona o usuário para a URL longa original associada ao ID fornecido. Se a URL não for encontrada, retorna um status 404 NOT FOUND.

### GET  /stats/{id}

Retorna estatísticas sobre a URL encurtada associada ao ID fornecido. As estatísticas incluem o número total de acessos e a média de acessos por dia. Se a URL não for encontrada, retorna um status 404 NOT FOUND.

#### Response Body

```json
{
    "totalAccesses": "totalDeAcessos",
    "averageAccessesPerDay": "mediadeAcessosPorDia"
}
```


