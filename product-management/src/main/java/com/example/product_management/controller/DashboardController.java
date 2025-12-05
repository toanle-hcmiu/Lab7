package com.example.product_management.controller;

import com.example.product_management.entity.Product;
import com.example.product_management.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    
    private final ProductService productService;
    
    public DashboardController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    public String showDashboard(Model model) {
        // Total product count
        long totalProducts = productService.getTotalProductCount();
        model.addAttribute("totalProducts", totalProducts);
        
        // Products by category
        Map<String, Long> productsByCategory = productService.getProductCountsByCategory();
        model.addAttribute("productsByCategory", productsByCategory);
        
        // Total inventory value
        BigDecimal totalValue = productService.getTotalInventoryValue();
        model.addAttribute("totalInventoryValue", totalValue != null ? totalValue.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO);
        
        // Average product price
        BigDecimal avgPrice = productService.getAveragePrice();
        model.addAttribute("averagePrice", avgPrice != null ? avgPrice.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO);
        
        // Low stock alerts (quantity < 10)
        List<Product> lowStockProducts = productService.getLowStockProducts(10);
        model.addAttribute("lowStockProducts", lowStockProducts);
        model.addAttribute("lowStockCount", lowStockProducts.size());
        
        // Recent products (last 5 added)
        List<Product> recentProducts = productService.getRecentProducts(5);
        model.addAttribute("recentProducts", recentProducts);
        
        // Total quantity in stock
        Long totalQuantity = productService.getTotalQuantity();
        model.addAttribute("totalQuantity", totalQuantity != null ? totalQuantity : 0L);
        
        // Price range
        BigDecimal maxPrice = productService.getMaxPrice();
        BigDecimal minPrice = productService.getMinPrice();
        model.addAttribute("maxPrice", maxPrice != null ? maxPrice.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO);
        model.addAttribute("minPrice", minPrice != null ? minPrice.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO);
        
        // Categories list
        List<String> categories = productService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("totalCategories", categories.size());
        
        return "dashboard";
    }
}

