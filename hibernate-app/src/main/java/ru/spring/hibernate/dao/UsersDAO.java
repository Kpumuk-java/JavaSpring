package ru.spring.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spring.hibernate.model.Users;

import java.util.Optional;

@Component
public class UsersDAO {
    private SessionFactory factory;

    @Autowired
    public void setFactory (SessionFactory factory) {
        this.factory = factory;
    }

    public Optional<Users> findById (Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Users u = session.createQuery("select u from users u where u.id = " + id, Users.class).getSingleResult();
            session.getTransaction().commit();
            return Optional.ofNullable(u);
        }
    }

    public void deleteById (Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Users u = session.get(Users.class, id);
            session.delete(u);
            session.getTransaction().commit();
        }
    }

    public Users saveOrUpdate (Users user) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            return user;
        }
    }
}
