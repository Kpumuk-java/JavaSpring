package ru.spring.market.beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.spring.market.exceptions_handling.ResourceNotFoundException;
import ru.spring.market.model.OrderItem;
import ru.spring.market.model.Product;
import ru.spring.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
@Data
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private int totalPrice;

    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
    }

    public void addToCart(Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product p = productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id + " (add to cart)"));
        OrderItem orderItem = new OrderItem(p);
        items.add(orderItem);
        recalculate();
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    public void recalculate() {
        totalPrice = 0;
        for (OrderItem o : items) {
            totalPrice += o.getPrice();
        }
    }

    public void deleteById (Long id) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            if (iter.next().getProduct().getId().equals(id)) {
                iter.remove();
                return;
            }
        }
    }

    public void decrementQuantity (Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.decrementQuantity();
                recalculate();
                return;
            }
        }
    }

    public void incrementQuantity (Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
    }
}
