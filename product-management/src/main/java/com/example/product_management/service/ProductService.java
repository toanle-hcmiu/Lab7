package com.example.product_management.service;

import com.example.product_management.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    
    // ===== Basic CRUD Operations =====
    List<Product> getAllProducts();
    
    List<Product> getAllProducts(Sort sort);
    
    Optional<Product> getProductById(Long id);
    
    Product saveProduct(Product product);
    
    void deleteProduct(Long id);
    
    // ===== Search Operations =====
    List<Product> searchProducts(String keyword);
    
    Page<Product> searchProducts(String keyword, Pageable pageable);
    
    List<Product> getProductsByCategory(String category);
    
    // ===== Advanced Search with Multiple Criteria =====
    List<Product> advancedSearch(String name, String category, BigDecimal minPrice, BigDecimal maxPrice);
    
    List<Product> advancedSearch(String name, String category, BigDecimal minPrice, BigDecimal maxPrice, Sort sort);
    
    Page<Product> advancedSearchWithPagination(String name, String category, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
    
    // ===== Category Operations =====
    List<String> getAllCategories();
    
    // ===== Statistics Operations =====
    long getTotalProductCount();
    
    long getProductCountByCategory(String category);
    
    BigDecimal getTotalInventoryValue();
    
    BigDecimal getAveragePrice();
    
    List<Product> getLowStockProducts(int threshold);
    
    List<Product> getRecentProducts(int limit);
    
    Long getTotalQuantity();
    
    BigDecimal getMaxPrice();
    
    BigDecimal getMinPrice();
    
    Map<String, Long> getProductCountsByCategory();
}
