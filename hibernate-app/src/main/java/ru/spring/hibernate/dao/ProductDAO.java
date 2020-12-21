package ru.spring.hibernate.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spring.hibernate.model.Product;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDAO {

    private SessionFactory factory;

    @Autowired
    public void setFactory (SessionFactory factory) {
        this.factory = factory;
    }

    public Optional<Product> findById (Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = session.createQuery("select p from products p where p.id = " + id, Product.class).getSingleResult();
            session.getTransaction().commit();
            return Optional.ofNullable(p);
        }
    }

    public List<Product> findAll () {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> list = session.createQuery("select p from products p", Product.class).getResultList();
            session.getTransaction().commit();
            return list;
        }
    }

    public void deleteById (Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            session.delete(p);
            session.getTransaction().commit();
        }
    }

    public Product saveOrUpdate (Product product) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return product;
        }
    }

}
