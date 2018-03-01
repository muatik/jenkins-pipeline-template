package com.muatik.recommendationservice.entity;

public class Product {
    private String title;

    public Product() {}
    public Product(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
