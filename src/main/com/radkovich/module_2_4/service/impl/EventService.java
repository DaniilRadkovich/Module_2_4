package com.radkovich.module_2_4.service.impl;

import com.radkovich.module_2_4.model.Event;

import java.util.List;

public interface EventService {

    Event createEvent(Integer userId, Integer fileId);

    Event getEventById(Integer id);

    List<Event> getAllEvents();

    List<Event> getEventsByUserId(Integer userId);

    void deleteEvent(Integer id);
}
