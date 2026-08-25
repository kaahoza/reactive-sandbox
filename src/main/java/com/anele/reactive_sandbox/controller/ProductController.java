package com.anele.reactive_sandbox.controller;


import com.anele.reactive_sandbox.model.Product;
import com.anele.reactive_sandbox.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public Mono<Product> create(@RequestBody Product product) {
        return service.save(product);
    }

    @GetMapping(value = "/stream/{category}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> streamByCategory(@PathVariable String category) {
        return service.streamProductsByCategory(category);
    }
}
