package ru.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.spring.market.dto.ProductDto;
import ru.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.spring.market.repositories.specifications.ProductSpecifications;
import ru.spring.market.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductDto getProductByID(@RequestParam Long id) {
        return productService.findProductDtoById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exist"));
    }

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam MultiValueMap<String, String > params,
            @RequestParam(name = "p", defaultValue = "1") Integer page ) {

        if (page < 1) {
            page = 1;
        }
        return productService.findAll(ProductSpecifications.build(params), page, 5);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveOrUpdate(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return productService.saveOrUpdate(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteByID(id);
    }


}
