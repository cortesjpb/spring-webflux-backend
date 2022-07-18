package com.example.webfluxbackend.services;

import com.example.webfluxbackend.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> save(Product product);
    Flux<Product> getAll();
}
