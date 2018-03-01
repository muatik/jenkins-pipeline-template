package com.muatik.recommendationservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductsApiConfiguration {
    @Value("${products.api.url}")
    private String apiUrl;

    public String getApiUrl() {
        return apiUrl;
    }
}
