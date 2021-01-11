package ru.spring.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.spring.market.model.Product;
import ru.spring.market.services.ProductService;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public Optional<Product> getProductByID(@RequestParam Long id) {
        return productService.findProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
                                        @RequestParam(name = "max_price", required = false) Integer maxPrice) {

        if (maxPrice == null) {
            maxPrice = Integer.MAX_VALUE;
        }
        return productService.findAllByPrice(minPrice, maxPrice);
    }

    @PostMapping
    public Product saveOrUpdate(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @GetMapping("/delete/")
    public boolean deleteProductById(@RequestParam(name = "id") Long id) {
        if (productService.deleteByID(id)) {
            return true;
        }
        return false;
    }


}
