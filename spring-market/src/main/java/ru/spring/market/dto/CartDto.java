package ru.spring.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.web.context.SaveContextOnUpdateOrErrorResponseWrapper;
import ru.spring.market.beans.Cart;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class CartDto {
    private List<OrderItemDto> items;
    private int totalPrice;

    public CartDto(Cart cart) {
        this.totalPrice = cart.getTotalPrice();
        this.items = cart.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
