package com.radkovich.module_2_4.controller;

import com.radkovich.module_2_4.dto.request.EventCreateRequest;
import com.radkovich.module_2_4.exception.EntityNotFoundException;
import com.radkovich.module_2_4.mapper.EventMapper;
import com.radkovich.module_2_4.model.Event;
import com.radkovich.module_2_4.service.EventServiceImpl;
import com.radkovich.module_2_4.service.impl.EventService;
import com.radkovich.module_2_4.util.JsonUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class EventServlet extends HttpServlet {

    private final EventService eventService = new EventServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getPathInfo();
        String userIdParam = req.getParameter("userId");

        try {
            if (userIdParam != null) {
                Integer userId = Integer.parseInt(userIdParam);
                JsonUtil.writeJson(resp, EventMapper.toDtoList(eventService.getEventsByUserId(userId)));
                return;
            }

            if (path == null || path.equals("/")) {
                JsonUtil.writeJson(resp, EventMapper.toDtoList(eventService.getAllEvents()));
                return;
            }
            Integer eventId = extractId(path);
            JsonUtil.writeJson(resp, EventMapper.toDto(eventService.getEventById(eventId)));

        } catch (EntityNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            JsonUtil.writeError(resp, e.getMessage());

        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtil.writeError(resp, "Invalid event ID or user ID!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            EventCreateRequest dto = JsonUtil.readJson(req, EventCreateRequest.class);

            Event event = eventService.createEvent(dto.getUserId(), dto.getFileId());

            resp.setStatus(HttpServletResponse.SC_CREATED);
            JsonUtil.writeJson(resp, EventMapper.toDto(event));

        } catch (EntityNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            JsonUtil.writeError(resp, e.getMessage());

        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtil.writeError(resp, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        try {
            Integer id = extractId(req.getPathInfo());
            eventService.deleteEvent(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (EntityNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            JsonUtil.writeError(resp, e.getMessage());

        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtil.writeError(resp, "Invalid event ID");
        }
    }

    private Integer extractId(String pathInfo) {
        if (pathInfo == null || pathInfo.equals("/")) {
            throw new IllegalArgumentException("You must enter event ID!");
        }

        try {
            return Integer.parseInt(pathInfo.substring(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid event ID!");
        }
    }
}


