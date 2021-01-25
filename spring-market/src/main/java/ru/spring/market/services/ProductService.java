package ru.spring.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.spring.market.dto.ProductDto;
import ru.spring.market.model.Product;
import ru.spring.market.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService  {
    private final ProductRepository productRepository;

    public ProductDto findProductById (Long id) {
        return new ProductDto(productRepository.findById(id).get());
    }

    public List<ProductDto> findAll () {
        return  productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public Page<ProductDto> findAll(int page) {
        Page<Product> originalPage = productRepository.findAll(PageRequest.of(page - 1, 5));
        return new PageImpl<>(originalPage.getContent().stream().map(ProductDto::new).collect(Collectors.toList()),
                originalPage.getPageable(), originalPage.getTotalElements());
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
