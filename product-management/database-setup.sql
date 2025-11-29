-- =============================================
-- LAB 7: SPRING BOOT & JPA CRUD
-- Database Setup Script
-- =============================================

-- Create Database
CREATE DATABASE IF NOT EXISTS product_management;
USE product_management;

-- Drop existing table if exists (for fresh start)
DROP TABLE IF EXISTS products;

-- Create products table
CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_code VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT DEFAULT 0,
    category VARCHAR(50),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO products (product_code, name, price, quantity, category, description) VALUES
('P001', 'Laptop Dell XPS 13', 1299.99, 10, 'Electronics', 'High-performance laptop with Intel Core i7, 16GB RAM, 512GB SSD'),
('P002', 'iPhone 15 Pro', 999.99, 25, 'Electronics', 'Latest iPhone model with A17 Pro chip, 256GB storage'),
('P003', 'Office Chair', 199.99, 50, 'Furniture', 'Ergonomic office chair with lumbar support and adjustable height'),
('P004', 'Wireless Mouse', 29.99, 100, 'Electronics', 'Logitech wireless mouse with precision tracking'),
('P005', 'Standing Desk', 399.99, 15, 'Furniture', 'Electric height-adjustable standing desk, 60x30 inches'),
('P006', 'Monitor 27 inch', 349.99, 30, 'Electronics', '4K UHD monitor with IPS panel and HDR support'),
('P007', 'Mechanical Keyboard', 89.99, 45, 'Electronics', 'RGB mechanical gaming keyboard with cherry MX switches'),
('P008', 'Bookshelf', 149.99, 20, 'Furniture', 'Modern 5-tier wooden bookshelf, walnut finish'),
('P009', 'LED Desk Lamp', 45.99, 60, 'Electronics', 'Dimmable LED desk lamp with USB charging port'),
('P010', 'Java Programming Book', 59.99, 75, 'Books', 'Complete guide to Java programming, 5th edition');

-- Verify data
SELECT * FROM products;

-- Display summary
SELECT 
    category, 
    COUNT(*) as product_count,
    SUM(quantity) as total_quantity,
    AVG(price) as avg_price
FROM products
GROUP BY category;

-- =============================================
-- End of Database Setup Script
-- =============================================

