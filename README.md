# Movies Service

A simple Spring Boot RESTful API for managing a movie database, demonstrating best practices with DTOs, validation, exception handling, OpenAPI documentation, and testing.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Validation](#validation)
- [Error Handling](#error-handling)
- [Testing](#testing)
- [Swagger/OpenAPI](#swaggeropenapi)

## Features

- CRUD operations for movies using RESTful endpoints
- DTO pattern for API request/response separation
- Validation with Jakarta Bean Validation (`@Valid`, `@NotBlank`, `@NotNull`)
- Global exception handling with `@ControllerAdvice`
- OpenAPI/Swagger integration for interactive API docs
- Unit and integration tests using Spring Boot Test and MockMvc

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- Git

### Clone and Run

```bash
git clone https://github.com/tckelly/movies-service.git
cd movies-service
./mvnw spring-boot:run
```

The application will start on [http://localhost:8080](http://localhost:8080).

## API Endpoints

| Method | Path           | Description              | Request Body       | Response           | HTTP Status Codes        |
|--------|----------------|--------------------------|--------------------|--------------------|-------------------------|
| POST   | /movies        | Create a new movie       | MovieRequest DTO   | MovieResponse DTO  | 201 Created             |
| GET    | /movies/{id}   | Get movie by ID          | N/A                | MovieResponse DTO  | 200 OK, 404 Not Found   |
| PUT    | /movies/{id}   | Update existing movie    | MovieRequest DTO   | MovieResponse DTO  | 200 OK, 404 Not Found   |
| DELETE | /movies/{id}   | Delete movie by ID       | N/A                | N/A                | 204 No Content, 404 Not Found |

## Technologies

- Java 17  
- Spring Boot 3.4.3  
- Spring Data JPA with Hibernate 6  
- H2 in-memory database (runtime)  
- Springdoc OpenAPI 2.8.6 for Swagger UI  
- Jakarta Bean Validation  
- JUnit 5 and Mockito for testing

## Project Structure

- **controller:** REST controllers with OpenAPI annotations  
- **service:** Business logic, transactional boundaries  
- **repository:** JPA repositories  
- **dto:** Data Transfer Objects for API requests and responses  
- **translator:** Converts between entity and DTO objects  
- **exception:** Custom exceptions  
- **config:** Application configuration (e.g., exception handlers)  
- **integration tests:** Full-stack Spring Boot tests including DB and REST layers

## Validation

The application uses Jakarta Bean Validation annotations to validate incoming API requests. For example, `@NotBlank` and `@NotNull` are used in `MovieRequest` to ensure valid input.

Validation errors will return a descriptive 400 Bad Request response.

## Error Handling

A global `@ControllerAdvice` handles exceptions such as `MovieNotFoundException` and validation errors. This ensures consistent and meaningful HTTP responses.

## Testing

- Unit tests mock the service layer to isolate controller logic.  
- Integration tests run the full Spring context with an embedded H2 database and MockMvc to simulate HTTP requests and responses.  

Use `./mvnw test` to run all tests.

## Swagger/OpenAPI

The project integrates Springdoc OpenAPI to generate interactive API documentation automatically.

Access the Swagger UI at:  
`http://localhost:8080/swagger-ui.html`