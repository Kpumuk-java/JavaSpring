package ru.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.spring.market.beans.Cart;
import ru.spring.market.dto.CartDto;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;

    @GetMapping
    public CartDto getCart() {
        return new CartDto(cart);
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cart.addToCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.clear();
    }

    @GetMapping("/dec/{id}")
    public void decrementQuantity(@PathVariable Long id) {
        cart.decrementQuantity(id);
    }

    @GetMapping("/inc/{id}")
    public void incrementQuantity(@PathVariable Long id) {
        cart.incrementQuantity(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductInCartById(@PathVariable Long id) {
        cart.deleteById(id);
    }
}
