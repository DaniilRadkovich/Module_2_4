package com.radkovich.module_2_4.service;

import com.radkovich.module_2_4.dao.EventDao;
import com.radkovich.module_2_4.dao.FileDao;
import com.radkovich.module_2_4.dao.UserDao;
import com.radkovich.module_2_4.dao.impl.EventDaoImpl;
import com.radkovich.module_2_4.dao.impl.FileDaoImpl;
import com.radkovich.module_2_4.dao.impl.UserDaoImpl;
import com.radkovich.module_2_4.exception.EntityNotFoundException;
import com.radkovich.module_2_4.model.Event;
import com.radkovich.module_2_4.model.FileEntity;
import com.radkovich.module_2_4.model.User;

import java.util.List;

public class EventServiceImpl implements com.radkovich.module_2_4.service.impl.EventService {

    private final UserDao userDao = new UserDaoImpl();
    private final FileDao fileDao = new FileDaoImpl();
    private final EventDao eventDao = new EventDaoImpl();

    @Override
    public Event createEvent(Integer userId, Integer fileId) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with ID: " + userId + " not found!");
        }

        FileEntity file = fileDao.findById(fileId);
        if (file == null) {
            throw new IllegalArgumentException("File with ID: " + fileId + " not found!");
        }

        Event event = new Event(user, file);
        eventDao.save(event);

        return event;
    }

    @Override
    public Event getEventById(Integer id) {
        Event event = eventDao.findById(id);
        if (event == null) {
            throw new IllegalArgumentException("Event with ID: " + id + " not found!");
        }

        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventDao.findAll();
    }

    @Override
    public List<Event> getEventsByUserId(Integer userId) {
        User user = userDao.findById(userId);

        if (user == null) {
            throw new EntityNotFoundException(String.valueOf(User.class), userId);
        }

        return eventDao.findByUserId(userId);
    }

    @Override
    public void deleteEvent(Integer id) {
        Event event = eventDao.findById(id);
        eventDao.delete(event);
    }
}
