package ru.lesson7.spring.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lesson7.spring.data.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBetween(int min, int max);
    List<Product> findAllByPriceIsGreaterThanEqual(int min);
    List<Product> findAllByPriceIsLessThanEqual(int max);
}
