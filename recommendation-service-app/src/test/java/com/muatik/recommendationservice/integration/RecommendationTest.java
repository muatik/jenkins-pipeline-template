package com.muatik.recommendationservice.integration;

import com.muatik.recommendationservice.ProductsApiConfiguration;
import com.muatik.recommendationservice.RecommendationServiceApplication;
import com.muatik.recommendationservice.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = {RecommendationServiceApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class RecommendationTest {

    @Autowired
    ProductsApiConfiguration productsApiConfiguration;

    @Test
    public void get_same_products_as_recommendation() {
        RestTemplate restTemplate = new RestTemplate();

        Product newProduct = new Product("Windows Phone");
        restTemplate.postForEntity(
                productsApiConfiguration.getApiUrl() + "/products",
                newProduct,
                Object.class);

        restTemplate.exchange(
                productsApiConfiguration.getApiUrl() + "/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Resources<Product>>() {
                });

        ResponseEntity<Product[]> recommendationResponse = restTemplate.exchange(
                "http://localhost:8080" + "/recommendations",
                HttpMethod.GET,
                null,
                Product[].class);

        Assert.assertEquals(recommendationResponse.getStatusCode(), HttpStatus.OK);

        /**
         * there are already 2 products in products-service, we added one another, 'windows phone'
         * now it adds up to 3 products.
         */
        Assert.assertEquals(recommendationResponse.getBody().length, 3);
    }

}
