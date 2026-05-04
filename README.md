# Cinema Reservation System

Simple Spring Boot application for managing cinema screenings, rooms, seats, clients and ticket reservations.

## Features

- Cinema domain model with JPA entities
- Movie screenings assigned to different rooms
- Ticket types and reservation statuses
- Soft-delete style flags for preserving data
- DTO-based API responses
- HQL/JPQL queries with pagination
- Repository tests using H2 database

## Tech stack

- Java 21+
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 for tests
- Maven

## Local configuration

Create a local configuration file:

`src/main/resources/application-local.properties`

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cinema
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

## Main queries

The application includes HQL queries for:

- movies played in a selected room
- screenings in a selected room
- screenings by movie id or title
- participants of a screening
- screenings of a selected client
- occupied seats count
- number of rooms where a movie was played
- number of tickets bought by a client in a date range
## Running tests
```bash
./mvnw test
```
## Note
This project was created as a learning project focused on JPA relationships, DTOs, HQL queries and repository testing.
