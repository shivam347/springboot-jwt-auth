# springboot-jwt-auth
This project demonstrates JWT-based authentication in Spring Boot for microservices. Users can register and login, receiving a Bearer token to access secured endpoints. Passwords are hashed, and requests are validated via JWT, providing secure, stateless authentication across services.

## Features
- **User Registration:** Stores users with hashed passwords using Spring Security's `PasswordEncoder`.
- **User Login:** Generates JWT (Bearer) tokens for authenticated users.
- **Protected Endpoints:** Secured endpoints require a valid JWT token in the `Authorization` header.
- **Stateless Authentication:** No server-side session storage; all authentication is handled via JWT.
- **H2 Database:** In-memory database for easy testing; can be replaced with MySQL/PostgreSQL.

## API Endpoints

## Register a User
- POST /auth/register
- Content-Type: application/json

- Body:
 {
  "username": "your_username",
  "password": "your_password"
 }

![alt text](<register jwt.png>)

