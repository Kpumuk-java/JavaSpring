package ru.spring.market.policy;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.spring.market.model.Cart;
import ru.spring.market.model.Order;
import ru.spring.market.model.User;
import ru.spring.market.services.CartService;
import ru.spring.market.services.OrderService;
import ru.spring.market.services.UserService;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderCartUserPolicy {
    private final UserService userService;
    private final OrderService orderService;
    private final CartService cartService;


    @Transactional
    public Order createOrderFromCart(String userName, UUID cartUuid, String address) {
        User user = userService.findByUsername(userName).orElseThrow(() -> new ResourceNotFoundException("User not found from create order"));
        Cart cart = cartService.findById(cartUuid).orElseThrow(() -> new ResourceNotFoundException("Cart not found from create order"));
        Order order = orderService.createFromUserCart(cart, user, address);
        cartService.clearCart(cartUuid);
        return order;
    }

}
