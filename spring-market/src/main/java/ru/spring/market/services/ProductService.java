package ru.spring.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.spring.market.dto.ProductDto;
import ru.spring.market.model.Product;
import ru.spring.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService  {
    private final ProductRepository productRepository;

    public Optional<ProductDto> findProductDtoById (Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }

    public static final Function<Product, ru.spring.market.ws.products.Product> functionEntityToSoap = se -> {
        ru.spring.market.ws.products.Product p = new ru.spring.market.ws.products.Product();
        p.setId(se.getId());
        p.setTitle(se.getTitle());
        p.setPrice(se.getPrice());
        return p;
    };

    public Optional<Product> findProductById (Long id) {
        return productRepository.findById(id);
    }

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        Page<Product> originalPage = productRepository.findAll(spec, PageRequest.of(page - 1, pageSize));
        return originalPage.map(ProductDto::new);
    }


    public List<ru.spring.market.ws.products.Product> getAllProducts () {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public ProductDto saveOrUpdate(ProductDto productDto) {
        if (productRepository.existsById(productDto.getId())) {
            Product product = productRepository.save(productRepository.findById(productDto.getId()).get());
            return new ProductDto(product);
        }
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product = productRepository.save(product);
        return new ProductDto(product);
    }

    public void deleteByID (Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }

}
