package ru.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.spring.market.dto.CartDto;
import ru.spring.market.model.Cart;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;

    @GetMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addProduct(@RequestParam(name = "id") Long id, @RequestParam(name = "count", defaultValue = "1") int count) {
        cart.addProduct(id, count);
    }

    @GetMapping("/delete/{id}")
    public void deleteProduct (@PathVariable Long id) {
        cart.deleteProduct(id);
    }

    @GetMapping("/")
    public List<CartDto> getAllProducts() {
        return cart.getAllProduct();
    }
}
