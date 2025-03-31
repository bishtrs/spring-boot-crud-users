# Spring Boot CRUD Users Application

## 1. Project Overview 

This is a sample Spring Boot based REST CRUD User API application.

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
