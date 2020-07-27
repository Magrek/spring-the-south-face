package com.vodianytskyivi.thesouthface.repository;

import com.vodianytskyivi.thesouthface.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByTitleContainingIgnoreCase(String title);
}
