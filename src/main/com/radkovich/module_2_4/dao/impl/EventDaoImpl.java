package com.radkovich.module_2_4.dao.impl;

import com.radkovich.module_2_4.dao.EventDao;
import com.radkovich.module_2_4.exception.EntityNotFoundException;
import com.radkovich.module_2_4.exception.HibernateRepositoryException;
import com.radkovich.module_2_4.model.Event;
import com.radkovich.module_2_4.util.HibernateFactory;
import org.hibernate.Session;

import java.util.List;

public class EventDaoImpl implements EventDao {
    @Override
    public void save(Event event) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.persist(event);

            session.getTransaction().commit();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not save event!", e);
        }
    }

    @Override
    public Event findById(Integer id) {
        try (Session session = HibernateFactory.openSession()) {
            Event event = session.find(Event.class, id);

            if (event == null) {
                throw new EntityNotFoundException(String.valueOf(Event.class), id);
            }

            return event;
        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not find event with ID: " + id + "!", e);
        }
    }

    @Override
    public List<Event> findByUserId(Integer userId) {
        try (Session session = HibernateFactory.openSession()) {
            return session.createSelectionQuery("FROM Event e WHERE e.user.id = :userId", Event.class).setParameter("userId", userId).list();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not get all events from DB!", e);
        }
    }

    @Override
    public List<Event> findAll() {
        try (Session session = HibernateFactory.openSession()) {
            return session.createSelectionQuery("FROM Event ", Event.class).getResultList();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not get all events from DB!", e);
        }
    }

    @Override
    public void delete(Event event) {
        try (Session session = HibernateFactory.openSession()) {
            session.beginTransaction();

            session.remove(event);

            session.getTransaction().commit();

        } catch (HibernateRepositoryException e) {
            throw new HibernateRepositoryException("Could not delete event!", e);
        }
    }
}
