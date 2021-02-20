package ru.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.spring.market.beans.Cart;
import ru.spring.market.dto.CartDto;
import ru.spring.market.dto.OrderDto;
import ru.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.spring.market.model.Order;
import ru.spring.market.model.User;
import ru.spring.market.services.OrderService;
import ru.spring.market.services.UserService;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrderFromCart(Principal principal, @RequestParam String address) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Order order = orderService.createFromUserCart(user, address);
        return new OrderDto(order);
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById (@PathVariable Long id) {
        return new OrderDto(orderService.getOrderById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find order with id: " + id)));
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders (Principal principal) {
        return orderService.findAllOrdersByOwnerName(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

}
