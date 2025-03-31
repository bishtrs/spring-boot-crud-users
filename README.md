# Spring Boot CRUD Users Application

## 1. Project Overview 

- A Spring Boot-based REST CRUD application for managing user resources.
- Uses MySQL for database operations and H2 for testing.
- Implements HTTP Basic authentication and Swagger for API documentation.

## 2. Tech stack

- JDK 17  
- Maven 4.0  
- MYSQL 8.0  
- Spring Boot 3.3.0  
- Spring Data JPA  
- Spring Security (Basic Authentication)  
- Swagger/OpenAPI  

## 3. Building and running the application

### 1) Create employee database and users table using provided SQL DDL.

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

mvn spring-boot:run<br/><br/>  

## 4. Swagger URL:  

http://localhost:8080/swagger-ui/index.html <br/>

<img width="498" alt="image" src="https://github.com/user-attachments/assets/fc47fc02-c409-475b-9a5b-906658ee0728" /><br/><br/>  

### 5. Testing  

Below API Endpoints can be used for testing APIs using POSTMAN.</br></br>    

-  POST API to create a user

   http://localhost:8080/api/v1/users  

   {  
      "firstName":"John",  
      "lastName":"Bocelli",  
      "email":"john.bocelli@gmail.com"  
   }    


-  GET API to get list of all users  

   http://localhost:8080/api/v1/users  

-  GET API to get user by id

   http://localhost:8080/api/v1/users/1  

-  PUT API to update user by id

   http://localhost:8080/api/v1/users/1

   {  
      "firstName":"John",  
      "lastName":"Bocelli",  
      "email":"john.bocelli2@gmail.com"  
   }    


-  DELETE API to delete a user by id

   http://localhost:8080/api/v1/users/1  


## 6. Folder structure

<img width="224" alt="image" src="https://github.com/user-attachments/assets/73722432-c3e4-48f4-bb3e-129673ce79d8" />



