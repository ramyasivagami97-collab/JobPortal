Job Portal Backend

A RESTful Job Portal backend application developed using Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL.

The project provides APIs for managing users, companies, jobs, skills, and job applications.

Technologies Used
Java
Spring Boot
Spring Data JPA
Hibernate
MySQL
REST APIs
Maven
Git & GitHub
Features
User management
Company management
Job management
Skills management
Job applications
Job search
Job filtering
JPA entity relationships
CRUD operations
RESTful APIs
Project Structure
src/main/java/com/example/JobPortal
│
├── Controller
│   ├── ApplicationController
│   ├── CompanyController
│   ├── JobController
│   ├── SkillsController
│   └── UsersController
│
├── Entity
│   ├── Application
│   ├── Company
│   ├── Job
│   ├── Skills
│   └── Users
│
├── Repository
│   ├── ApplicationRepository
│   ├── CompanyRepository
│   ├── JobRepository
│   ├── SkillsRepository
│   └── UsersRepository
│
└── Service
├── ApplicationService
├── CompanyService
├── JobService
├── SkillsService
└── UsersService
Main Entities
Users

Stores job portal user information and user roles.

Company

Stores company information and its associated jobs.

Job

Stores job details such as:

Title
Description
Location
Salary
Department
Company
Skills
Skills

Stores skills associated with jobs.

Application

Stores job application information and application status.

API Endpoints
Jobs
POST   /api/v1/jobs
GET    /api/v1/jobs
GET    /api/v1/jobs/{id}
PUT    /api/v1/jobs/{id}
DELETE /api/v1/jobs/{id}
GET    /api/v1/jobs/search
Companies
POST   /api/v1/companies
GET    /api/v1/companies
GET    /api/v1/companies/{id}
PUT    /api/v1/companies/{id}
DELETE /api/v1/companies/{id}
Applications
POST   /api/v1/applications
GET    /api/v1/applications
GET    /api/v1/applications/{id}
PUT    /api/v1/applications/{id}
DELETE /api/v1/applications/{id}
Skills
POST   /api/v1/skills
GET    /api/v1/skills
GET    /api/v1/skills/{id}
PUT    /api/v1/skills/{id}
DELETE /api/v1/skills/{id}
Users
POST   /api/v1/users
GET    /api/v1/users
GET    /api/v1/users/{id}
PUT    /api/v1/users/{id}
DELETE /api/v1/users/{id}
Database

The application uses MySQL as the database.

Database credentials are provided through environment variables rather than being stored directly in the source code.

spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
How to Run
Clone the repository.
Configure MySQL and create the jobportal database.
Configure the following environment variables:
DB_USERNAME
DB_PASSWORD
Start the Spring Boot application.
Test the APIs using Postman, Swagger, or another REST API client.
Future Enhancements
JWT authentication and authorization
Role-based access control
Advanced job search and filtering
Job application validation
Prevent duplicate applications
Pagination and sorting
React frontend
Docker deployment
Cloud deployment
Author

Ramya Sivagami

GitHub: RamyaSivagami97