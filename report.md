# LAB 7 REPORT: SPRING BOOT & JPA CRUD APPLICATION

**Course:** Web Application Development  
**Student:** Le Canh Toan - ITCSIU23060 - Group 2

---

## Project Overview

A Product Management System built with Spring Boot, Spring Data JPA, MySQL, and Thymeleaf. The application provides CRUD operations for managing products with advanced features including search, filtering, sorting, validation, and a statistics dashboard.

---

## Completed Features

### Basic CRUD Operations
- **Create**: Add new products via form
- **Read**: List all products in a table
- **Update**: Edit existing products
- **Delete**: Remove products with confirmation

### Advanced Search
Multi-criteria search allowing users to filter by:
- Product name (contains match)
- Category (exact match)
- Price range (min/max)

```java
@Query("SELECT p FROM Product p WHERE " +
       "(:name IS NULL OR p.name LIKE %:name%) AND " +
       "(:category IS NULL OR p.category = :category) AND " +
       "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
       "(:maxPrice IS NULL OR p.price <= :maxPrice)")
List<Product> searchProducts(...);
```

### Category Filter
- Filter pills at the top of product list
- Quick filtering by clicking category badges
- Distinct categories query from database

### Pagination
- Page-based navigation for search results
- Shows current page and total pages
- Maintains search parameters across pages

### Validation
Server-side validation with error display:

```java
@NotBlank(message = "Product code is required")
@Pattern(regexp = "^P\\d{3,}$", message = "Must start with P followed by 3+ numbers")
private String productCode;

@NotNull(message = "Price is required")
@DecimalMin(value = "0.01", message = "Price must be greater than 0")
private BigDecimal price;
```

Validation errors displayed inline on form fields.

### Sorting
- Clickable column headers (Name, Price, Quantity)
- Toggle between ascending/descending
- Sort indicators (▲/▼) shown on active column

### Statistics Dashboard
Dashboard showing:
- Total products count
- Total inventory value (price × quantity)
- Average product price
- Products by category distribution
- Low stock alerts (quantity < 10)
- Recent products added

---

## Project Structure

```
product-management/
├── controller/
│   ├── ProductController.java    # CRUD, search, sort endpoints
│   └── DashboardController.java  # Statistics dashboard
├── entity/
│   └── Product.java              # JPA entity with validation
├── repository/
│   └── ProductRepository.java    # Data access with custom queries
├── service/
│   ├── ProductService.java       # Service interface
│   └── ProductServiceImpl.java   # Business logic implementation
└── templates/
    ├── product-list.html         # Product listing with filters
    ├── product-form.html         # Add/Edit form with validation
    └── dashboard.html            # Statistics dashboard
```

---

## Technologies

- Spring Boot 3.4
- Spring Data JPA
- Spring Validation
- MySQL 8.0
- Thymeleaf
- Maven

---

## Key Implementation Notes

**Repository Pattern**: Using Spring Data JPA's `JpaRepository` to eliminate boilerplate SQL. Custom queries defined with `@Query` annotation.

**Service Layer**: Interface-based design separating business logic from controllers. Constructor injection for dependencies.

**Validation Flow**: `@Valid` annotation triggers validation, `BindingResult` captures errors, Thymeleaf displays field-specific messages.

**Sorting/Filtering**: Uses Spring's `Sort` and `Pageable` objects. URL parameters maintain state across requests.
