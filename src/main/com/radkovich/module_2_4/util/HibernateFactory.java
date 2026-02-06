package com.radkovich.module_2_4.util;

import com.radkovich.module_2_4.model.Event;
import com.radkovich.module_2_4.model.FileEntity;
import com.radkovich.module_2_4.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
    private static SessionFactory sessionFactory;

    static {
        try {
            org.hibernate.cfg.Configuration configuration = new Configuration()
                    .addAnnotatedClass(Event.class)
                    .addAnnotatedClass(FileEntity.class)
                    .addAnnotatedClass(User.class);

            configuration.configure("hibernate.cfg.xml");

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError("SessionFactory was not created!" + e);
        }
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
