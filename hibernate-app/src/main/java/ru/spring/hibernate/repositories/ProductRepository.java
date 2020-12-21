package ru.spring.hibernate.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spring.hibernate.dao.ProductDAO;
import ru.spring.hibernate.model.Product;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> list;
    private ProductDAO productDAO;

    @Autowired
    private void setProductDAO (ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getList () {
        list = productDAO.findAll();
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

}

