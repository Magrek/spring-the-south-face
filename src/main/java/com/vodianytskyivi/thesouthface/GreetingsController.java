package com.vodianytskyivi.thesouthface;

import com.vodianytskyivi.thesouthface.domain.Product;
import com.vodianytskyivi.thesouthface.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Product> products = productRepository.findAll();
        model.put("products", products);
        return "main";
    }

    @PostMapping
    public String createProduct(
            @RequestParam String title, @RequestParam Double price, Map<String, Object> model
    ) {
        Product product = new Product(title, price);
        productRepository.save(product);
        Iterable<Product> products = productRepository.findAll();
        model.put("products", products);
        return "main";
    }
}