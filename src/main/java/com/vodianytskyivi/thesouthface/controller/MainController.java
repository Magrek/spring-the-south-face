package com.vodianytskyivi.thesouthface.controller;

import com.vodianytskyivi.thesouthface.domain.Product;
import com.vodianytskyivi.thesouthface.domain.User;
import com.vodianytskyivi.thesouthface.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static java.lang.Double.parseDouble;

@Controller
public class MainController {

    private final ProductRepository productRepository;

    @Value("${upload.path}")
    private String uploadPath;

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
            products = productRepository.findByTitleContainingIgnoreCase(filter);
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
            @RequestParam String price,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        Double resultPrice;
        boolean isTitleEmpty = title == null || title.isEmpty();
        boolean isPriceEmpty = price == null || price.isEmpty();

        if (isTitleEmpty || isPriceEmpty) {
            if (isTitleEmpty) {
                model.addAttribute("titleFieldError", "Product title cannot be empty");
            }
            if (isPriceEmpty) {
                model.addAttribute("priceFieldError", "Product price cannot be empty");
            }
            model.addAttribute("enteredTitle", title);
            model.addAttribute("enteredPrice", price);
            return "main";
        }

        try {
            resultPrice = parseDouble(price);
        } catch (NumberFormatException e) {
            model.addAttribute("enteredTitle", title);
            model.addAttribute("priceFieldError", "Please enter valid price in format xx.xx");
            return "main";
        }
        Product product = new Product(title, resultPrice);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            product.setFilename(resultFileName);
        }
        productRepository.save(product);
        products = productRepository.findAll();
        model.addAttribute("products", products);
        return "main";
    }
}