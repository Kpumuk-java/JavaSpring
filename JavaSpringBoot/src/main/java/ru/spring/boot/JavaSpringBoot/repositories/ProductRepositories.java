package ru.spring.boot.JavaSpringBoot.repositories;

import org.springframework.stereotype.Component;
import ru.spring.boot.JavaSpringBoot.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
* Разобраться с примером проекта на Spring MVC;
Создать класс Товар (Product), с полями id, title, cost;
Товары необходимо хранить в репозитории (класс, в котором в виде List<Product> хранятся товары).
* Репозиторий должен уметь выдавать список всех товаров и товар по id;
Сделать форму для добавления товара в репозиторий и логику работы этой формы;
Сделать страницу, на которой отображаются все товары из репозитория.

*
* */

@Component
public class ProductRepositories {
    private List<Product> products;

    @PostConstruct
    private void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "apple", 102));
        products.add(new Product(2L, "orange", 200));
        products.add(new Product(3L, "banana", 78));
        products.add(new Product(4L, "pineapple", 300));
        products.add(new Product(5L, "kiwi", 88));
        products.add(new Product(6L, "mango", 130));
        products.add(new Product(7L, "pear", 50));
        products.add(new Product(8L, "lemon", 175));
        products.add(new Product(9L, "grapes", 248));
        products.add(new Product(10L, "plum", 198));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Product findProduct(Long id) {
        if (products != null) {
            for (Product p : products) {
                if (p.getId().equals(id)) {
                    return p;
                }
            }
        }
        return null;
    }

    public Product saveOrUpdate(Product product) {
        if (product.getId() != null) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).equals(product.getId())) {
                    products.set(i, product);
                    return product;
                }
            }
        }
        Long newId = products.stream().mapToLong(Product::getId).max().getAsLong() + 1L;
        products.add(product);
        return product;
    }


}
