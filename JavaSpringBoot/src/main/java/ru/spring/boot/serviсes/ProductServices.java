package ru.spring.boot.serviсes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.spring.boot.model.Product;
import ru.spring.boot.repositories.ProductRepositories;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServices {
    private final ProductRepositories productRepositories;

    public Optional<Product> findById (Long id) {
        return productRepositories.findById(id);
    }

    public List<Product> findAll () {
        return productRepositories.findAll();
    }

    public void deleteById (Long id) {
        productRepositories.deleteById(id);
    }

    public Product saveOrUpdate (Product product) {
        return productRepositories.saveOrUpdate(product);
    }
}
