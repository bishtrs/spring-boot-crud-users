# Spring Boot CRUD Users Application

## 1. Project Overview 

A simple Spring Boot based REST CRUD application, which is used to read, create, update & delete a User resource.
It uses MYSQL DB to save User entity while Junit use H2 DB for testing, uses HTTP Basic authentication mechanism for security.
Also Swagger is configured for API documentation.

## 2. Tech stack

JDK 17  
Maven 4.0  
MYSQL 8.0  
Spring Boot 3.3.0  
Spring Data JPA  
Spring Security (Basic Authentication)  
Swagger/OpenAPI  

## 3. Building and running the application:

### 1) Create employee database and Users table using below sql ddl

CREATE DATABASE employee;  

CREATE TABLE users (  
    id INT AUTO_INCREMENT PRIMARY KEY,  
	first_name VARCHAR(50),  
    last_name VARCHAR(50),  
    email VARCHAR(100) NOT NULL UNIQUE,  
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  
);  

### 2) Clone the repo

git clone https://github.com/bishtrs/spring-boot-crud-users.git

### 3) Building the application

mvn clean package 

### 4) Running the application

mvn spring-boot:run

## 4. Testing

Below API Endpoints can be used for testing APIs using POSTMAN.
Swagger URL: http://localhost:8080/swagger-ui/index.html

### 1) POST API to create a user

http://localhost:8080/api/v1/users  

{  
    "firstName":"John",  
    "lastName":"Bocelli",  
    "email":"john.bocelli@gmail.com"  
}  

### 2) GET API to get list of all users  

http://localhost:8080/api/v1/users

### 3) GET API to get user by id

http://localhost:8080/api/v1/users/1

### 4) PUT API to update user by id

http://localhost:8080/api/v1/users/1

{  
    "firstName":"John",  
    "lastName":"Bocelli",  
    "email":"john.bocelli2@gmail.com"  
}  

### 5) DELETE API to delete a user by id

http://localhost:8080/api/v1/users/1

## 5. Folder structure

<img width="224" alt="image" src="https://github.com/user-attachments/assets/73722432-c3e4-48f4-bb3e-129673ce79d8" />



