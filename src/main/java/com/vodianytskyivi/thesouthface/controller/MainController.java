package com.vodianytskyivi.thesouthface.controller;

import com.vodianytskyivi.thesouthface.domain.Product;
import com.vodianytskyivi.thesouthface.domain.User;
import com.vodianytskyivi.thesouthface.repository.ProductRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    private final ProductRepository productRepository;

    public MainController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Product> products = productRepository.findAll();
        model.put("products", products);
        return "main";
    }

    @PostMapping("create")
    public String createProduct(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam Double price,
            Map<String, Object> model
    ) {
        Product product = new Product(title, price);
        productRepository.save(product);
        Iterable<Product> products = productRepository.findAll();
        model.put("products", products);
        return "main";
    }

    @PostMapping("filter")
    public String filterProducts(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Product> filteredProducts;
        if (filter != null && !filter.isEmpty()) {
            filteredProducts = productRepository.findByTitleContaining(filter);
        } else {
            filteredProducts = productRepository.findAll();
        }
        model.put("products", filteredProducts);
        return "main";
    }
}