package ru.spring.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.spring.market.dto.OrderDto;
import ru.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.spring.market.policy.OrderCartUserPolicy;
import ru.spring.market.services.OrderService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
//@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final OrderCartUserPolicy orderCartUserPolicy;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrderFromCart(Principal principal, @RequestParam UUID cartUuid, @RequestParam String address) {
        return new OrderDto(orderCartUserPolicy.createOrderFromCart(principal.getName(), cartUuid, address));
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
