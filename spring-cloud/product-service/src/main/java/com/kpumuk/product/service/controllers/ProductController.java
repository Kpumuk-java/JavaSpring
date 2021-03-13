package com.kpumuk.product.service.controllers;

import com.kpumuk.product.service.dto.ProductDto;
import com.kpumuk.product.service.exceptions_handling.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.kpumuk.product.service.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;


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
    public List<ProductDto> getAllProducts() {
        return productService.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteByID(id);
    }


}
