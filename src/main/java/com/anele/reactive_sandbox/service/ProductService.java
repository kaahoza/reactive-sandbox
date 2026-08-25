package com.anele.reactive_sandbox.service;

import com.anele.reactive_sandbox.config.SandboxProperties;
import com.anele.reactive_sandbox.model.Product;
import com.anele.reactive_sandbox.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SandboxProperties sandboxProperties;

    public ProductService(ProductRepository productRepository, SandboxProperties sandboxProperties) {
        this.productRepository = productRepository;
        this.sandboxProperties = sandboxProperties;
    }

    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    public Flux<Product> streamProductByCategory(String category) {
        return productRepository.findByCategory(category)
                .delayElements(Duration.ofMillis(sandboxProperties.getStreamDelayMs()));
    }

    public Flux<Product> getSystemThrottledStream() {
        return productRepository.findAll()
                // Explicitly manages traffic if downstream processing runs behind
                .onBackpressureBuffer(sandboxProperties.getMaxBufferSize(),
                        droppedItem -> System.err.println("Downstream full! Dropped item: " + droppedItem.getName()));
    }
}
