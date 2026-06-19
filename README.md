PerfumeHub
Overview

PerfumeHub is a Spring Boot web application for browsing and ordering perfumes online.

The application allows users to register, log in, browse perfumes, place orders, and track their purchases. Administrators can manage perfumes and process customer orders.

This project was developed as part of the SoftUni Spring Fundamentals course.

Features
Guest Users
View homepage
Browse perfume catalog
View perfume details
Register account
Login
Registered Users
Place perfume orders
View personal order history
Cancel pending orders
Logout
Administrators
Add perfumes
Edit perfumes
Delete perfumes
View all orders
Complete orders
Cancel orders
Technologies Used
Backend
Java 21
Spring Boot
Spring MVC
Spring Data JPA
Spring Security
Hibernate
MySQL
Frontend
Thymeleaf
HTML5
CSS3
Bootstrap 5
Additional Libraries
Lombok
ModelMapper
Jakarta Validation
Architecture

The application follows a layered architecture:

Controller Layer
↓
Service Layer
↓
Repository Layer
↓
MySQL Database
Database Model
UserEntity
Field	Type
id	UUID
username	String
email	String
password	String
role	UserRole
Perfume
Field	Type
id	UUID
name	String
brand	String
description	String
price	BigDecimal
imageUrl	String
gender	Gender
volumeMl	Integer
stockQuantity	Integer
createdAt	LocalDateTime
Order
Field	Type
id	UUID
user	UserEntity
perfume	Perfume
quantity	Integer
totalPrice	BigDecimal
status	OrderStatus
createdAt	LocalDateTime
Entity Relationships
UserEntity
|
| 1:N
↓
Order
↑
| N:1
|
Perfume
Validation
User Registration
Username must be between 3 and 30 characters
Email must be valid
Password must be between 5 and 50 characters
Perfume Creation
Name is required
Brand is required
Description must be between 10 and 500 characters
Price must be greater than 0
Volume must be between 10ml and 500ml
Stock quantity cannot be negative
Orders
Quantity must be at least 1
Cannot order more items than available in stock
Security

Authentication is session-based.

User Roles
ADMIN
USER
Access Control

Guests can access:

/
/perfumes
/login
/register

Users can access:

/orders/**

Admins can access:

/admin/**

Access control is implemented using:

AuthInterceptor
AdminInterceptor
Search and Filtering

The catalog supports:

Search by perfume name
Filter by brand
Filter by gender
Sort by price ascending
Sort by price descending
Sort by newest perfumes
Exception Handling

Custom exceptions:

PerfumeNotFoundException
OrderNotFoundException
UserNotFoundException
DuplicateUserException
InsufficientStockException
UnauthorizedActionException

Global exception handling is implemented using:

@ControllerAdvice
Installation
Clone Repository
git clone https://github.com/YOUR_USERNAME/PerfumeHub.git
Configure Database

Create a MySQL database:

CREATE DATABASE perfumehub;

Configure:

spring.datasource.url=jdbc:mysql://localhost:3306/perfumehub
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
Run Application
mvn clean install
mvn spring-boot:run

Open:

http://localhost:8080

Future Improvements
Shopping cart
Product reviews
Wishlist
Email notifications
Stripe payment integration
Advanced search and filtering
Author

Denis Arnaudov

SoftUni Spring Fundamentals Project

License

This project is intended for educational purposes.