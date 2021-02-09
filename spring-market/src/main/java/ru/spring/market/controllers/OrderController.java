package ru.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.spring.market.beans.Cart;
import ru.spring.market.dto.CartDto;
import ru.spring.market.dto.OrderDto;
import ru.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.spring.market.model.User;
import ru.spring.market.services.OrderService;
import ru.spring.market.services.UserService;

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

    @GetMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrderFromCart(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createFromUserCart(user);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders (Principal principal) {
        return orderService.findAllOrdersByOwnerName(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

}
