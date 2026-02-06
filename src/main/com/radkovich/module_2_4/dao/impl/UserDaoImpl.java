package com.radkovich.module_2_4.dao.impl;

import com.radkovich.module_2_4.dao.UserDao;
import com.radkovich.module_2_4.exception.EntityNotFoundException;
import com.radkovich.module_2_4.exception.HibernateRepositoryException;
import com.radkovich.module_2_4.model.User;
import com.radkovich.module_2_4.util.HibernateFactory;
import org.hibernate.Session;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public void save(User user) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.persist(user);

            session.getTransaction().commit();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not save User!", e);
        }
    }

    @Override
    public User findById(Integer id) {
        try (Session session = HibernateFactory.openSession()) {
            User user = session.find(User.class, id);

            if (user == null) {
                throw new EntityNotFoundException(String.valueOf(User.class), id);
            }

            return user;
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not find User with ID: " + id + "!", e);
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = HibernateFactory.openSession()) {
            return session.createSelectionQuery("FROM User", User.class).getResultList();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not get all users from DB!", e);
        }
    }

    @Override
    public void update(User user) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.merge(user);

            session.getTransaction().commit();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not update User!", e);
        }
    }

    @Override
    public void delete(User user) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.remove(user);

            session.getTransaction().commit();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not delete User!", e);
        }
    }
}
