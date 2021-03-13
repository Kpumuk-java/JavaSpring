package com.kpumuk.product.service.services;


import com.kpumuk.product.service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import com.kpumuk.product.service.model.Product;
import org.springframework.stereotype.Service;
import com.kpumuk.product.service.repositories.ProductRepository;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService  {
    private final ProductRepository productRepository;

    public Optional<Product> findProductById (Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll () {
        return productRepository.findAll();
    }

    public void deleteByID (Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }

    public Optional<ProductDto> findProductDtoById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }
}
