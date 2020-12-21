package ru.spring.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("SINGLETON")
public class FactorySession {

    private SessionFactory factory;

    @PostConstruct
    private void init() {
        factory = new Configuration()
                .configure("configs/hibernet/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void shutDown () {
        factory.close();
    }
}
