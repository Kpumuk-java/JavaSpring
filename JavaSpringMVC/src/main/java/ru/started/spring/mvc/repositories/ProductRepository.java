package ru.started.spring.mvc.repositories;

import ru.started.spring.mvc.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> list;

    @PostConstruct
    public void init () {
        list = new ArrayList<>();
        list.add(new Product(1L,"apple",  102.20));
        list.add(new Product(2L,"orange", 200.00));
        list.add(new Product(3L,"banana",  78.00));
        list.add(new Product(4L,"pineapple",  300.50));
        list.add(new Product(5L,"kiwi",  88.80));
        list.add(new Product(6L,"mango",  130.40));
        list.add(new Product(7L,"pear",  50.40));
        list.add(new Product(8L,"lemon",  175.50));
        list.add(new Product(9L,"grapes",  248.80));
        list.add(new Product(10L,"plum",  198.40));
    }

    public List<Product> getList () {
        return Collections.unmodifiableList(list);
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

    public void addProduct(Product product) {
        if (product != null) {
            list.add(product);
        }
    }

    public void deleteById (Long id) {
        list.removeIf(p -> p.getId() == id);
    }



}

