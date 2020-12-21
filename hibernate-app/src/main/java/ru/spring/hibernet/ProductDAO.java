package ru.spring.hibernet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class ProductDAO {

    private static SessionFactory factory;

    private static void init() {
        factory = new Configuration()
                .configure("configs/hibernet/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public Optional<Product> findById (Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product p = session.createQuery("select p from Product p where p.id = " + id, Product.class).getSingleResult();
            session.getTransaction().commit();
            return Optional.ofNullable(p);
        }
    }

    public List<Product> findAll () {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> list = session.createQuery("select p from Product p", Product.class).getResultList();
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

    public static void shutDown () {
        factory.close();
    }
}
