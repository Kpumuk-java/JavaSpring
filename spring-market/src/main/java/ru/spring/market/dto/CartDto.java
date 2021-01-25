package ru.spring.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spring.market.model.Product;

import java.util.Map;

@Data
@NoArgsConstructor
public class CartDto {
    private long id;
    private String title;
    private int price;
    private int count;

    public CartDto(Map.Entry<Product, Integer> map) {
        this.id = map.getKey().getId();
        this.title = map.getKey().getTitle();
        this.price = map.getKey().getPrice();
        this.count = map.getValue();
    }

}
