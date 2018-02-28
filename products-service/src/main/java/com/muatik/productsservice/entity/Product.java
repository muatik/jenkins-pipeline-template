package com.muatik.productsservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Product {

    @Id
    @GeneratedValue
    public Long id;

    public String title;
    public Product() {}
    public Product(String title) {
        this.title = title;
    }
}
