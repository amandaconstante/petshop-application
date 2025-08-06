# Petshop Application Documentation

## Data Model

### Database Schema Overview

The application uses a relational database with the following main tables:

#### user_tb (Base User Table)
```
+---------------+------------------+----------+-------------+
| Column        | Type             | Nullable | Constraints |
+---------------+------------------+----------+-------------+
| id            | BIGINT           | NO       | PK, AUTO_INCREMENT |
| name          | VARCHAR          | YES      |             |
| birth_date    | DATE             | YES      |             |
| email         | VARCHAR          | YES      | UNIQUE      |
| password      | VARCHAR          | YES      |             |
| permission    | VARCHAR(enum)    | YES      | ADMIN/COMMOM|
+---------------+------------------+----------+-------------+
```

#### tutor (Extends user_tb via JOINED inheritance)
```
+---------------+------------------+----------+-------------+
| Column        | Type             | Nullable | Constraints |
+---------------+------------------+----------+-------------+
| id            | BIGINT           | NO       | PK, FK(user_tb.id) |
| register_date | DATE             | YES      |             |
+---------------+------------------+----------+-------------+
```

#### pet
```
+---------------+------------------+----------+-------------+
| Column        | Type             | Nullable | Constraints |
+---------------+------------------+----------+-------------+
| id            | BIGINT           | NO       | PK, AUTO_INCREMENT |
| name          | VARCHAR          | YES      |             |
| breed         | VARCHAR          | YES      |             |
| type          | VARCHAR(enum)    | YES      | See PetType enum |
| age           | INTEGER          | YES      |             |
| tutor_id      | BIGINT           | YES      | FK(tutor.id)|
+---------------+------------------+----------+-------------+
```

### Entity Relationships

```
User (user_tb)
    |
    └── Tutor (tutor) [1:1 inheritance]
            |
            └── Pet (pet) [1:many]
```

- **User-Tutor**: One-to-one inheritance relationship using JOINED strategy
- **Tutor-Pet**: One-to-many relationship (one tutor can have multiple pets)

### Enumerations

#### PermissionType
- `ADMIN`
- `COMMOM` (Common user)

#### PetType
- `CACHORRO` (Dog)
- `GATO` (Cat)
- `COELHO` (Rabbit)
- `HAMSTER` (Hamster)
- `PEIXE` (Fish)
- `ESQUILO` (Squirrel)
- `LAGARTO` (Lizard)
- `NAO_IDENTIFICADO` (Not identified)

## High-Level Architecture

### Technology Stack
- **Framework**: Spring Boot 3.5.4
- **Language**: Java 17
- **Database**: H2 (in-memory)
- **ORM**: JPA/Hibernate
- **Security**: Spring Security with BCrypt password hashing
- **Build Tool**: Maven
- **Additional Libraries**: Lombok for boilerplate reduction

### Architecture Pattern
The application follows a **layered architecture** pattern:

```
┌─────────────────────────────────────┐
│          Controller Layer           │  ← REST endpoints
├─────────────────────────────────────┤
│           Service Layer             │  ← Business logic
├─────────────────────────────────────┤
│         Repository Layer            │  ← Data access
├─────────────────────────────────────┤
│           Domain Layer              │  ← Entities & DTOs
└─────────────────────────────────────┘
```

### Key Design Decisions

1. **Inheritance Strategy**: Uses JPA JOINED inheritance for User/Tutor relationship, creating separate tables but sharing common fields
2. **Database**: H2 in-memory database for development/testing purposes
3. **Security**: Implements password hashing with BCrypt for user security
4. **DTOs**: Uses Java records for data transfer objects (UserDto)
5. **Exception Handling**: Global exception handling with RestExceptionHandler

### Package Structure
```
com.app.petshop/
├── controller/     # REST controllers
├── domain/         # Entity classes
├── dto/           # Data transfer objects
├── infra/         # Infrastructure (exception handling)
├── repository/    # Data access interfaces
└── service/       # Business logic services
```

## Application Capabilities

### Current Features

#### User Management
- **User Registration**: Create new users with encrypted passwords
  - Endpoint: `POST /user/create`
  - Accepts: UserDto (name, birthDate, email, password, permission)
  - Security: Passwords are hashed using BCrypt
  - Validation: Email uniqueness (commented out but implemented)

#### Data Management
- **Persistent Storage**: H2 database with JPA/Hibernate
- **Entity Relationships**: Proper foreign key relationships between Tutor and Pet
- **Development Tools**: H2 console available at `/h2-console`

#### Security Features
- **Password Encryption**: BCrypt hashing for user passwords
- **Spring Security Integration**: Framework integrated for future authentication/authorization

### Planned/Potential Features (Based on Structure)

#### Pet Management
- Pet CRUD operations (repository exists but no controller/service yet)
- Pet-Tutor assignment and management
- Pet type categorization

#### Tutor Management
- Tutor registration and profile management
- Pet ownership tracking
- Registration date tracking

#### Advanced Features
- User authentication and authorization
- Permission-based access control (ADMIN vs COMMOM users)
- Pet search and filtering capabilities
- Tutor-Pet relationship management

### Development Features
- **SQL Logging**: Enabled for debugging database operations
- **H2 Console**: Web interface for database inspection
- **Exception Handling**: Centralized error response handling

### Technical Capabilities
- **RESTful API**: Following REST principles
- **JSON Serialization**: Automatic request/response handling
- **Database Migrations**: JPA auto-DDL for schema management
- **Dependency Injection**: Spring IoC container management