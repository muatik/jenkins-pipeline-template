package com.muatik.productsservice.resource;

import com.muatik.productsservice.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductsRepository extends CrudRepository<Product, Long>{
}
