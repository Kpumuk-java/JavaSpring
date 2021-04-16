package ru.spring.market.policy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.spring.market.model.*;
import ru.spring.market.services.CartService;
import ru.spring.market.services.ProductService;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartProductPolicy {
    private final CartService cartService;
    private final ProductService productService;

    @Transactional
    public void addToCart(UUID cartId, Long productId) {
        Cart cart = cartService.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Unable to find cart with id: " + cartId));;
        CartItem cartItem = cart.getItemByProductId(productId);
        if (cartItem != null) {
            cartItem.incrementQuantity();
            cart.recalculate();
            return;
        }
        Product p = productService.findProductById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to add product with id: " + productId + " to cart. Product doesn't exist"));
        cart.add(new CartItem(p));
    }
}
