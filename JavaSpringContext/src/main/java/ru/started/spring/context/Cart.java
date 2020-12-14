package ru.started.spring.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {

    private List<Product> cartList;
    private ProductRepository productRepository;

    @Autowired
    private void setProductRepository (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    private void setCartList () {
        cartList = new ArrayList<>();
    }

    public boolean add (long id) {

        if (cartList != null) {

            for (int i = 0; i < cartList.size(); i++) {
                if (cartList.get(i).getId() == id) {
                    return false;
                }
            }
            cartList.add(productRepository.getProduct(id));
            return true;
        }

        return false;

    }

    public boolean delete (long id) {

        Iterator<Product> iter = cartList.listIterator();
        while (iter.hasNext()) {
            Product p = iter.next();
            if ( p.getId() == id) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    public void getCart() {

        if (cartList.isEmpty()) {
            System.out.println("Корзина пуста");
            return;
        }

        for (int i = 0; i <  cartList.size(); i++) {
            System.out.println(cartList.get(i).toString());
        }
    }
}


