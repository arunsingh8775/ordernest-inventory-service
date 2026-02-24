package com.ordernest.inventory.config;

import com.ordernest.inventory.entity.Product;
import com.ordernest.inventory.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@ConditionalOnProperty(value = "app.seed.enabled", havingValue = "true", matchIfMissing = true)
public class ProductDataInitializer implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(ProductDataInitializer.class);

    private final ProductRepository productRepository;

    public ProductDataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        List<SeedProduct> seedProducts = List.of(
                new SeedProduct("Laptop", new BigDecimal("75000"), "INR", 10, "High-performance laptop suitable for development and gaming."),
                new SeedProduct("Smartphone", new BigDecimal("25000"), "INR", 20, "5G enabled smartphone with AMOLED display and long battery life."),
                new SeedProduct("Headphones", new BigDecimal("3000"), "INR", 50, "Noise-cancelling over-ear headphones with deep bass."),
                new SeedProduct("Smartwatch", new BigDecimal("12000"), "INR", 15, "Fitness tracking smartwatch with heart-rate monitor."),
                new SeedProduct("Tablet", new BigDecimal("40000"), "INR", 8, "10-inch tablet perfect for reading, streaming, and browsing."),
                new SeedProduct("Gaming Mouse", new BigDecimal("1500"), "INR", 30, "Ergonomic gaming mouse with RGB lighting and adjustable DPI."),
                new SeedProduct("Mechanical Keyboard", new BigDecimal("4500"), "INR", 25, "Mechanical keyboard with tactile switches and backlight."),
                new SeedProduct("Monitor 24inch", new BigDecimal("14000"), "INR", 12, "24-inch Full HD IPS monitor with thin bezels."),
                new SeedProduct("External SSD 1TB", new BigDecimal("8500"), "INR", 18, "Portable 1TB SSD with ultra-fast read/write speeds."),
                new SeedProduct("Bluetooth Speaker", new BigDecimal("5000"), "INR", 22, "Portable waterproof Bluetooth speaker with powerful sound.")
        );

        int inserted = 0;
        for (SeedProduct seedProduct : seedProducts) {
            if (productRepository.existsByNameIgnoreCase(seedProduct.name())) {
                continue;
            }

            Product product = new Product();
            product.setName(seedProduct.name());
            product.setPrice(seedProduct.price());
            product.setCurrency(seedProduct.currency());
            product.setAvailableQuantity(seedProduct.availableQuantity());
            product.setDescription(seedProduct.description());
            productRepository.save(product);
            inserted++;
        }

        logger.info("Product seed completed. inserted={}, skipped={}", inserted, seedProducts.size() - inserted);
    }

    private record SeedProduct(
            String name,
            BigDecimal price,
            String currency,
            Integer availableQuantity,
            String description
    ) {
    }
}
