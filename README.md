# Secure Online Banking Application

A robust online banking system built with Spring Boot, implementing role-based security, JSP frontend, and multiple database access strategies.

## Features

- User account management (CRUD operations)
- Role-based authentication and authorization
- Secure transaction processing
- Account balance monitoring
- Transaction history tracking
- Admin dashboard for user management

## Technology Stack

### Backend
- **Spring Boot** - Core framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence
- **Hibernate** - ORM tool
- **JDBC Template** - Direct database access
- **Named JDBC Template** - SQL operations with named parameters
- **REST API** - RESTful web services

### Frontend
- **JSP (JavaServer Pages)** - View templates
- **JSTL** - JSP tag library
- **Bootstrap** - Responsive styling
- **jQuery** - DOM manipulation and AJAX calls

### Database
- Multiple database access strategies implemented:
  - JPA/Hibernate with EntityManagerFactory
  - Hibernate SessionFactory
  - DataSource direct connection
  - JDBC Template
  - Named JDBC Template

## Architecture

The application follows a layered architecture with the following structure:

- **Config Layer**: Contains all configuration classes for database, security, and web settings
- **Controller Layer**: Houses both REST and MVC controllers for handling requests
- **Model Layer**: Contains entity classes representing database tables
- **Repository Layer**: Manages data access using various database strategies
- **Service Layer**: Implements business logic and transaction management
- **Security Layer**: Handles authentication and authorization
- **Views**: JSP files for the frontend interface

## Security Features

The application implements comprehensive security using Spring Security with:

- Role-based access control (ADMIN and USER roles)
- Custom login page and authentication
- Session management
- CSRF protection
- Password encryption
- Secure REST endpoints
- Authorization checks at controller and service levels

## Database Integration

The application demonstrates multiple database access approaches:

- **JPA/Hibernate**: For complex entity relationships and ORM capabilities
- **SessionFactory**: Direct Hibernate session management
- **EntityManagerFactory**: JPA-based entity management
- **DataSource**: Direct database connection management
- **JDBC Template**: For simple SQL operations
- **Named JDBC Template**: For parameterized SQL queries

## Getting Started

1. Clone the repository from the provided GitHub link
2. Configure your database properties in the application.properties file
3. Build the project using Maven
4. Run the application using Spring Boot
5. Access the application through localhost:8080

## API Endpoints

### User Management
- `GET /api/users` - Retrieve all users (ADMIN only)
- `GET /api/users/{id}` - Retrieve specific user
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user (ADMIN only)

### Account Operations
- `GET /api/accounts/{id}` - Get account details
- `POST /api/accounts/{id}/transfer` - Transfer funds
- `GET /api/accounts/{id}/transactions` - Get transaction history

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.

## Contact

Your Name - stacyjones12335@gmail.com
Project Link: https://github.com/StacyJ1201/BankingApp
