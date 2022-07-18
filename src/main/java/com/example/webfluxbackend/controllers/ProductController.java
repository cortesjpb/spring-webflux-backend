package com.example.webfluxbackend.controllers;

import com.example.webfluxbackend.dto.ProductDTO;
import com.example.webfluxbackend.models.Product;
import com.example.webfluxbackend.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/getProducts", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Flux<Product> getProducts() {
        return this.productService.getAll();
    }

    @PostMapping(value = "/createProduct", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_EVENT_STREAM_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Product newProduct = new Product();
        newProduct.setName(productDTO.getName());
        newProduct.setDescription(productDTO.getDescription());
        return this.productService.save(newProduct);
    }
}
