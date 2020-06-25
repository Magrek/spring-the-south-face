package com.vodianytskyivi.thesouthface.controller;

import com.vodianytskyivi.thesouthface.domain.Product;
import com.vodianytskyivi.thesouthface.domain.User;
import com.vodianytskyivi.thesouthface.repository.ProductRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final ProductRepository productRepository;

    public MainController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model
    ) {
        Iterable<Product> products;
        if (filter != null && !filter.isEmpty()) {
            products = productRepository.findByTitleContaining(filter);
        } else {
            products = productRepository.findAll();
        }
        model.addAttribute("products", products);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("create")
    public String createProduct(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam Double price,
            Model model
    ) {
        Product product = new Product(title, price);
        productRepository.save(product);
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "main";
    }
}