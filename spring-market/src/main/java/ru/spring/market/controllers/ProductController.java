package ru.spring.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.spring.market.dto.ProductDto;
import ru.spring.market.model.Product;
import ru.spring.market.services.ProductService;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public Optional<Product> getProductByID(@RequestParam Long id) {
        return productService.findProductById(id);
    }

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
                                           @RequestParam(name = "max_price", required = false) Integer maxPrice,
                                           @RequestParam(name = "title", required = false) String title,
                                           @RequestParam(name = "p", defaultValue = "1") Integer page ) {

        if (maxPrice == null) {
            maxPrice = Integer.MAX_VALUE;
        }

        if (page < 1) {
            page = 1;
        }

        return productService.findAll(page);
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
