package ru.spring.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.spring.market.model.Product;
import ru.spring.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService  {
    private final ProductRepository productRepository;

    public Optional<Product> findProductById (Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAllByPrice(int min, int max) {
        return productRepository.findAllByPriceBetween(min, max);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public boolean deleteByID (Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
