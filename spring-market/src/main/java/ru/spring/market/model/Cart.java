package ru.spring.market.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.spring.market.dto.CartDto;
import ru.spring.market.repositories.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@RequiredArgsConstructor
public class Cart {
    private Map<Product, Integer> cart;
    private final ProductRepository productRepository;

    @PostConstruct
    private void initialization () {
        cart = new HashMap();
    }

    public void addProduct (Long idProduct, int count) {
        if (productRepository.existsById(idProduct)) {
            cart.put(productRepository.findById(idProduct).get(), count);
        }
    }

    public void deleteProduct (Long idProduct) {
        Iterator<Map.Entry<Product, Integer>> iterator = cart.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Product, Integer> buffer = iterator.next();
            if (buffer.getKey().getId().equals(idProduct)) {
                iterator.remove();
            }
        }
    }

    public void addCountProduct (Product product, int count) {
        cart.replace(product, count);
    }

    public List<CartDto> getAllProduct () {
        List<CartDto> cartDtoList = new ArrayList<>(cart.size());
        Iterator<Map.Entry<Product, Integer>> iterator = cart.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Product, Integer> buffer = iterator.next();
            cartDtoList.add(new CartDto(buffer));
        }
        return cartDtoList;
    }



}
