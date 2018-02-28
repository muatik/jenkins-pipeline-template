package com.muatik.productsservice;

import com.muatik.productsservice.entity.Product;
import com.muatik.productsservice.resource.ProductsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ProductsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductsServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductsRepository repository) {
        return strings -> {
            Stream.of("Iphone X", "Android Y")
                    .map(Product::new)
                    .forEach(repository::save);
        };
    }
}
