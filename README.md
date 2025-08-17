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


## Login a User
- POST /auth/login
- Content-Type: application/json

- Body:
 {
  "username": "your_username",
  "password": "your_password"
 }

![alt text](<Screenshot 2025-08-17 201223.png>)

## Access Protected Endpoints

- GET /auth/hello
- Headers:
- Authorization: Bearer <JWT_TOKEN>

![alt text](<Screenshot 2025-08-17 201714.png>)


# Security
- Passwords are hashed using BCryptPassword Encoder in Spring Security
- Jwt tokens are generated and validated for every request
- Stateless Authentication ensures scalability in microservices

# Notes
- H2 database is used for simplicity but can be replaced with other relational database
- This project is intended for learning purpose and can be extended with roles and permissions

# Author
- Shivam Yadav