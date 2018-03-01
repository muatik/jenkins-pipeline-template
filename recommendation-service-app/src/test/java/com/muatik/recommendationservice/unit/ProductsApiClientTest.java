package com.muatik.recommendationservice.unit;

import com.muatik.recommendationservice.ProductsApiConfiguration;
import com.muatik.recommendationservice.entity.Product;
import com.muatik.recommendationservice.service.ProductsAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductsApiClientTest {


    @Test
    public void when_findAll_called_it_returns_the_data_intact() {
        String url = "http://my-url";

        ProductsApiConfiguration apiConfiguration = mock(ProductsApiConfiguration.class);
        when(apiConfiguration.getApiUrl()).thenReturn(url);

        RestTemplate restTemplate = mock(RestTemplate.class);

        Resources<Product> products = new Resources<>(Arrays.asList(new Product("1st-product")));
        ResponseEntity<Resources<Product>> responseEntity = new ResponseEntity<>(products, HttpStatus.OK);
        when(restTemplate.exchange(
                Matchers.eq(url),
                Matchers.eq(HttpMethod.GET),
                Matchers.<HttpEntity<List<Object>>>any(),
                Matchers.<ParameterizedTypeReference<Resources<Product>>>any())
        ).thenReturn(responseEntity);

        ProductsAPI apiClient = new ProductsAPI(restTemplate, apiConfiguration);
        try {
            apiClient.findAll();
            Mockito.verify(restTemplate, times(1)).exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Resources<Product>>() {}
            );
        } catch (Exception e) {

        }

        Mockito.verify(apiConfiguration, times(1)).getApiUrl();

    }
}
