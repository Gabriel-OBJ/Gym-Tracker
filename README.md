# GymTracker API

API RESTful para rastreamento de sessões de treino, desenvolvida com foco nas melhores práticas de design de APIs.

## Sobre o Projeto

O **GymTracker** é uma API que permite aos usuários registrar, monitorar e gerenciar suas atividades físicas. O diferencial técnico deste projeto é a implementação de HATEOAs, permitindo que a API seja navegável através de hipermídia.

Este projeto foi desenvolvido como parte do meu portfólio de Engenharia de Software.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 4.0.2**
- **Spring HATEOAS**
- **Spring Data JPA**
- **H2 Database**
- **Maven**

## Como Executar

1. Clone o repositório:
   ```
     git clone [https://github.com/SEU-USUARIO/gym-tracker.git](https://github.com/SEU-USUARIO/gym-tracker.git)
2. Execute via Maven
   ```
     mvn spring-boot:run
3. Teste os Endpoints facilmente em:
   ```
      http://localhost:8080/swagger-ui/index.html#/

## Endpoints da API
| Método | Rota | Descrição |
| :--- | :--- | :--- |
| **GET** | `/gym-sessions` | Lista todos os treinos |
| **GET** | `/gym-sessions/{id}` | Busca detalhes de um treino específico |
| **POST** | `/gym-sessions` | Cria um novo treino |
| **PUT** | `/gym-sessions/{id}` | Atualiza (ou cria) um treino |
| **DELETE** | `/gym-sessions/{id}` | Remove um treino |

## Criar um Treino

Cria uma nova sessão de treino. O sistema verifica automaticamente se os nomes dos exercícios já existem; se não existirem, são criados na hora.

- **Rota:** `/gym-sessions`
- **Método:** `POST`
- **Exemplo de Corpo (JSON):**

```json
{
  "date": "2026-01-30T15:00:00",
  "workoutSetList": [
    {
      "weight": 20,
      "reps": 12,
      "exercise": {
        "name": "Triceps Pushdown"
      }
    },
    {
      "weight": 10,
      "reps": 100,
      "exercise": {
        "name": "Hammer Curls"
      }
    }
  ],
  "weighIn": 99
}
```

## Requisitar um Treino
- **Rota:** `/gym-sessions/{id}`
- **Método:** `GET`
- **Exemplo de resposta (JSON)**

```json
{
  "_links": {
    "self": {
      "href": "http://localhost:8080/gym-sessions/1"
    },
    "gym-sessions": {
      "href": "http://localhost:8080/gym-sessions"
    }
  },
  "date": "2026-01-30T15:00:00",
  "id": 1,
  "weighIn": 99.0,
  "workoutSetList": [
    {
      "exercise": {
        "name": "Biceps Curl",
        "targetedMuscle": "Biceps",
        "id": 3
      },
      "weight": 20.0,
      "reps": 12,
      "id": 1
    },
    {
      "exercise": {
        "name": "Triceps Pushdown",
        "targetedMuscle": "Triceps",
        "id": 4
      },
      "weight": 10.0,
      "reps": 100,
      "id": 2
    }
  ]
}
```

Desenvolvido por Gabriel - Estudante de Análise e Desenvolvimento de Sistemas (IFCE).
