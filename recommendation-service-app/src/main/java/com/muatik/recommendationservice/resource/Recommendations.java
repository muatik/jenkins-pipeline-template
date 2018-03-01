package com.muatik.recommendationservice.resource;

import com.muatik.recommendationservice.entity.Product;
import com.muatik.recommendationservice.service.ProductsAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class Recommendations {

    private ProductsAPI productsAPI;

    public Recommendations(ProductsAPI productsAPI) {
        this.productsAPI = productsAPI;
    }

    @GetMapping
    public Collection<Product> get() {
        // lets recommended what we get, just for the shake of simplicity
        return productsAPI.findAll();
    }

}
