package com.example.webfluxbackend.services.impl;

import com.example.webfluxbackend.models.Product;
import com.example.webfluxbackend.repositories.ProductRepository;
import com.example.webfluxbackend.services.ProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    public Flux<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Flux<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }
    public Flux<Product> findByNameContaining(String name) {
        return this.productRepository.findByNameContaining(name);
    }
}
