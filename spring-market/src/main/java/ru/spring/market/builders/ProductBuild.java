package ru.spring.market.builders;

import ru.spring.market.model.Product;

public class ProductBuild {
    private String title;
    private int price;

    public ProductBuild setTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductBuild setPrice(int price) {
        this.price = price;
        return this;
    }

    public Product build () {
        return new Product(title, price);
    }
}
