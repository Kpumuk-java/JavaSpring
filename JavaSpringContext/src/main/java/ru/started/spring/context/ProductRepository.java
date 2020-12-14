package ru.started.spring.context;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> list;

    @PostConstruct
    private void init () {
        list = new ArrayList<>();
        list.add(new Product("apple",  102.20));
        list.add(new Product("orange", 200.00));
        list.add(new Product("banana",  78.00));
        list.add(new Product("pineapple",  300.50));
        list.add(new Product("kiwi",  88.80));
        list.add(new Product("mango",  130.40));
        list.add(new Product("pear",  50.40));
        list.add(new Product("lemon",  175.50));
        list.add(new Product("grapes",  248.80));
        list.add(new Product("plum",  198.40));
    }

    public List<Product> getList () {
        return Collections.unmodifiableList(list); // Добавить неизменяемость листа
    }

    public Product getProduct (Long id) {
        for (Product p : list) {
            if (p.getId() == id) {
                return p;
            }
        }

        return null;
    }

    public void getProductRepository() {

        if (list.isEmpty()) {
            System.out.println("Продуктов нет");
            return;
        }

        for (int i = 0; i <  list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

}

