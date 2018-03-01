package com.muatik.recommendationservice.service;

import com.muatik.recommendationservice.ProductsApiConfiguration;
import com.muatik.recommendationservice.entity.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Service
public class ProductsAPI {

    private RestTemplate restTemplate;
    private ProductsApiConfiguration apiConfiguration;

    public ProductsAPI(RestTemplate restTemplate, ProductsApiConfiguration apiConfiguration) {
        this.restTemplate = restTemplate;
        this.apiConfiguration = apiConfiguration;
    }

    public Collection<Product> findAll() {
        ResponseEntity<Resources<Product>> responseEntity = restTemplate.exchange(
                apiConfiguration.getApiUrl() + "/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Resources<Product>>() {
                });
        return responseEntity.getBody().getContent();
    }

}
