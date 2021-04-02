package ru.spring.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spring.market.model.Order;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private String username;
    private int totalPrice;
    private String address;
    private String creationDateTime;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.username = order.getOwner().getUsername();
        this.totalPrice = order.getPrice();
        this.address = order.getAddress();
        this.creationDateTime = order.getCreatedAt().toString();
    }
}
