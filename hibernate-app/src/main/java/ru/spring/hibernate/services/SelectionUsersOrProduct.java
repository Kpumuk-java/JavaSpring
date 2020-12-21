package ru.spring.hibernate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.spring.hibernate.model.Product;
import ru.spring.hibernate.model.Users;
import ru.spring.hibernate.repositories.ProductRepository;

import java.util.List;

@Component
@Scope("SINGLETON")
public class SelectionUsersOrProduct {

    private ProductRepository productRepository;

    @Autowired
    private void setProductRepository (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Users> getListUsers (Long idProduct) {
        if (idProduct != null) {
            return productRepository.getProduct(idProduct).getUsers();
        }
        return null;
    }




}
