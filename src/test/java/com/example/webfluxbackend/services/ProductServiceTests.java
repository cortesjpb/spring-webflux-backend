package com.example.webfluxbackend.services;

import com.example.webfluxbackend.controllers.ProductController;
import com.example.webfluxbackend.models.Product;
import com.example.webfluxbackend.repositories.ProductRepository;
import com.example.webfluxbackend.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ProductController.class)
@Import(ProductServiceImpl.class)
public class ProductServiceTests {
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setName("Product 1");
        product.setDescription("The first Product");

        Mockito.when(productRepository.save(product)).thenReturn(Mono.just(product));

        webTestClient.post()
                .uri("/api/v1/products/createProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(product))
                .exchange()
                .expectStatus().isCreated();

        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    void testGetProducts() {
        Product product = new Product();
        product.setName("Product 1");
        product.setDescription("The first Product");

        List<Product> list = new ArrayList<>();
        list.add(product);

        Flux<Product> productFlux = Flux.fromIterable(list);

        Mockito
                .when(productRepository.findAll())
                .thenReturn(productFlux);

        webTestClient.get().uri("/api/v1/products/getProducts")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class);

        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }
}
