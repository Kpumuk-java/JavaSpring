package ru.spring.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.spring.market.model.Cart;
import ru.spring.market.model.Order;
import ru.spring.market.model.User;
import ru.spring.market.repositories.OrderRepository;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;

    public Order createFromUserCart (User user, String address) {
        Order order = new Order(cart, user, address);
        orderRepository.save(order);
        return order;
    }

    public List<Order> findAllOrdersByOwnerName (String username) {
        return orderRepository.findAllByOwnerUsername(username);
    }

    public Optional<Order> getOrderById (Long id) {
        return orderRepository.findById(id);
    }

}
