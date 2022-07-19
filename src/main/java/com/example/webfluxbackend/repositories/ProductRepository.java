package com.example.webfluxbackend.repositories;

import com.example.webfluxbackend.models.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findByName(String name);
    Flux<Product> findByNameContaining(String name);
}
