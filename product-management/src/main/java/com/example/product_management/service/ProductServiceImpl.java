package com.example.product_management.service;

import com.example.product_management.entity.Product;
import com.example.product_management.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    // ===== Basic CRUD Operations =====
    
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @Override
    public List<Product> getAllProducts(Sort sort) {
        return productRepository.findAll(sort);
    }
    
    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    // ===== Search Operations =====
    
    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }
    
    @Override
    public Page<Product> searchProducts(String keyword, Pageable pageable) {
        return productRepository.findByNameContaining(keyword, pageable);
    }
    
    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    
    // ===== Advanced Search with Multiple Criteria =====
    
    @Override
    public List<Product> advancedSearch(String name, String category, BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.searchProducts(name, category, minPrice, maxPrice);
    }
    
    @Override
    public List<Product> advancedSearch(String name, String category, BigDecimal minPrice, BigDecimal maxPrice, Sort sort) {
        return productRepository.searchProducts(name, category, minPrice, maxPrice, sort);
    }
    
    @Override
    public Page<Product> advancedSearchWithPagination(String name, String category, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return productRepository.searchProductsWithPagination(name, category, minPrice, maxPrice, pageable);
    }
    
    // ===== Category Operations =====
    
    @Override
    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }
    
    // ===== Statistics Operations =====
    
    @Override
    public long getTotalProductCount() {
        return productRepository.count();
    }
    
    @Override
    public long getProductCountByCategory(String category) {
        return productRepository.countByCategory(category);
    }
    
    @Override
    public BigDecimal getTotalInventoryValue() {
        BigDecimal value = productRepository.calculateTotalInventoryValue();
        return value != null ? value : BigDecimal.ZERO;
    }
    
    @Override
    public BigDecimal getAveragePrice() {
        BigDecimal avg = productRepository.calculateAveragePrice();
        return avg != null ? avg : BigDecimal.ZERO;
    }
    
    @Override
    public List<Product> getLowStockProducts(int threshold) {
        return productRepository.findLowStockProducts(threshold);
    }
    
    @Override
    public List<Product> getRecentProducts(int limit) {
        return productRepository.findRecentProducts(PageRequest.of(0, limit));
    }
    
    @Override
    public Long getTotalQuantity() {
        Long total = productRepository.calculateTotalQuantity();
        return total != null ? total : 0L;
    }
    
    @Override
    public BigDecimal getMaxPrice() {
        BigDecimal max = productRepository.findMaxPrice();
        return max != null ? max : BigDecimal.ZERO;
    }
    
    @Override
    public BigDecimal getMinPrice() {
        BigDecimal min = productRepository.findMinPrice();
        return min != null ? min : BigDecimal.ZERO;
    }
    
    @Override
    public Map<String, Long> getProductCountsByCategory() {
        Map<String, Long> counts = new HashMap<>();
        List<String> categories = getAllCategories();
        for (String category : categories) {
            counts.put(category, getProductCountByCategory(category));
        }
        return counts;
    }
}
