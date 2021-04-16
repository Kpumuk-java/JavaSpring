package ru.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.spring.market.dto.CartDto;
import ru.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.spring.market.model.Cart;
import ru.spring.market.policy.CartProductPolicy;
import ru.spring.market.services.CartService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartProductPolicy cartProductPolicy;

    @PostMapping
    public UUID createNewCart() {
        Cart cart = cartService.save(new Cart());
        return cart.getId();
    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@PathVariable UUID uuid) {
        Cart cart = cartService.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Unable to find cart with id: " + uuid));;
        return new CartDto(cart);
    }

    @PostMapping("/add")
    public void addProductToCart(@RequestParam UUID uuid, @RequestParam(name = "product_id") Long productId) {
        cartProductPolicy.addToCart(uuid, productId);
    }

    @PostMapping("/add")
    public void incrementProductToCart(@RequestParam UUID uuid, @RequestParam(name = "product_id") Long productId) {
        cartProductPolicy.addToCart(uuid, productId);
    }

    @PostMapping("/clear")
    public void clearCart (@RequestParam UUID uuid) {
        cartService.clearCart(uuid);
    }

    @DeleteMapping
    public void deleteProductInCartById (@RequestParam UUID uuid, @RequestParam(name = "product_id") Long productId) {
        cartService.delete(uuid, productId);
    }
}
