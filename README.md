# Order Service

Spring Boot microservice for order lifecycle and shipping tracking.

## Service Scope
- Manage orders
- Manage shipping

## Tech Stack
- Java 25
- Spring Boot 4
- Spring Data JPA
- H2 / PostgreSQL
- OpenAPI (Swagger)

## Default Port
- `8082`

## Architecture Flow
```mermaid
flowchart LR
    C[Client] --> API[Order Service API]
    API --> CT[Controllers]
    CT --> SV[Services]
    SV --> RP[Repositories]
    RP --> DB[(H2 / PostgreSQL)]
    SV --> EH[Global Exception Handler]

    CT --> ORD[Order Module]
    CT --> SHP[Shipping Module]
    SHP --> ORD
```

## Sequence Diagram
```mermaid
sequenceDiagram
    participant U as API Client
    participant C as Controller
    participant S as Service
    participant R as Repository
    participant D as Order DB
    participant E as Exception Handler

    U->>C: POST /orders
    C->>S: validate request
    S->>R: save order
    R->>D: insert orders row
    D-->>R: persisted order
    R-->>S: entity
    S-->>C: OrderResponse
    C-->>U: HTTP 201

    U->>C: POST /shipping
    C->>S: validate + resolve order by orderNumber
    S->>R: save shipping
    R->>D: insert shipping row
    D-->>R: persisted shipping
    R-->>S: entity
    S-->>C: ShippingResponse
    C-->>U: HTTP 201

    alt order/shipping not found or validation error
      S-->>E: throw exception
      E-->>U: HTTP 4xx/5xx
    end
```

## Database Schema
- `orders`
- `shipping` (FK -> `orders`, unique `order_id`)

### ER Diagram
```mermaid
flowchart TD
  ORD[orders] -->|1..1| SHP[shipping]
```

## Key APIs
- `GET /api/v1/orders`
- `GET /api/v1/orders/{orderNumber}`
- `POST /api/v1/orders`
- `PUT /api/v1/orders/{orderNumber}`
- `DELETE /api/v1/orders/{orderNumber}`
- `GET /api/v1/shipping`
- `GET /api/v1/shipping/{orderNumber}`
- `POST /api/v1/shipping`
- `PUT /api/v1/shipping/{orderNumber}`
- `DELETE /api/v1/shipping/{orderNumber}`

## Build and Run
```bash
./gradlew clean build
./gradlew bootRun
```

Run with PostgreSQL profile:
```bash
./gradlew bootRun --args='--spring.profiles.active=postgres'
```

## API Docs
- Swagger: `http://localhost:8082/swagger-ui.html`
- OpenAPI: `http://localhost:8082/api-docs`
