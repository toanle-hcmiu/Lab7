# Product Management System - Spring Boot

A simple CRUD application built with Spring Boot, JPA, and Thymeleaf.

## Prerequisites

- Java 17+
- MySQL 8.0+
- Maven (or use included wrapper)

## Setup

### 1. Create Database

Run the SQL file:
```bash
mysql -u root -p < database-setup.sql
```

### 2. Configure Database

Edit `src/main/resources/application.properties`:
```properties
spring.datasource.password=your_mysql_password
```

### 3. Run Application

**Windows:**
```bash
mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

### 4. Access Application

```
http://localhost:8080/products
```

## Features

- ✅ Create new products
- ✅ List all products
- ✅ Update existing products
- ✅ Delete products
- ✅ Search products by name

## Project Structure

```
src/main/java/com/example/product_management/
├── entity/Product.java              # JPA Entity
├── repository/ProductRepository.java # Spring Data JPA Repository
├── service/
│   ├── ProductService.java          # Service Interface
│   └── ProductServiceImpl.java      # Service Implementation
└── controller/
    ├── ProductController.java       # CRUD Controller
    └── HomeController.java          # Root Redirect

src/main/resources/
├── application.properties           # Configuration
└── templates/
    ├── product-list.html           # List View
    └── product-form.html           # Create/Edit Form
```

## Endpoints

| URL | Method | Description |
|-----|--------|-------------|
| `/products` | GET | List all products |
| `/products/new` | GET | Show add form |
| `/products/save` | POST | Create/Update product |
| `/products/edit/{id}` | GET | Show edit form |
| `/products/delete/{id}` | GET | Delete product |
| `/products/search?keyword=` | GET | Search products |

## Troubleshooting

**Port 8080 already in use:**
```properties
# Add to application.properties
server.port=8081
```

**Can't connect to MySQL:**
- Check MySQL is running
- Verify username/password in `application.properties`

**Application won't start:**
```bash
mvnw.cmd clean install
```

## Technologies

- Spring Boot 3.4.12
- Spring Data JPA
- Thymeleaf
- MySQL 8.0
- Maven

---

**LAB 7: Spring Boot & JPA CRUD**
