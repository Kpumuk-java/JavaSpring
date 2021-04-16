package ru.spring.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.spring.market.model.Cart;
import ru.spring.market.model.CartItem;
import ru.spring.market.model.Product;
import ru.spring.market.repositories.CartRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> findById(UUID id) {
        return cartRepository.findById(id);
    }


    @Transactional
    public void clearCart (UUID cartId) {
        Cart cart = findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cart.clear();
    }

    @Transactional
    public void delete(UUID cartId, Long productId) {
        Cart cart = findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not found. Unable to delete product with id: " + productId + " to cart"));
        cart.delete(productId);
    }
}
