package com.radkovich.module_2_4.dao;

import com.radkovich.module_2_4.model.Event;

import java.util.List;

public interface EventDao {
    void save(Event event);

    Event findById(Integer id);

    List<Event> findByUserId(Integer userId);

    List<Event> findAll();

    void delete(Event event);
}
