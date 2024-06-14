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

