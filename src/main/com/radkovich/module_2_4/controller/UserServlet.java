package com.radkovich.module_2_4.controller;

import com.radkovich.module_2_4.dto.request.UserCreateRequest;
import com.radkovich.module_2_4.dto.request.UserUpdateRequest;
import com.radkovich.module_2_4.exception.EntityNotFoundException;
import com.radkovich.module_2_4.mapper.UserMapper;
import com.radkovich.module_2_4.model.User;
import com.radkovich.module_2_4.service.UserServiceImpl;
import com.radkovich.module_2_4.service.impl.UserService;
import com.radkovich.module_2_4.util.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getPathInfo();

        if (path != null && path.matches("/\\d+/events")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            if (path == null || path.equals("/")) {
                List<User> users = userService.getAllUsers();
                JsonUtil.writeJson(resp, UserMapper.toDtoList(users));
            } else {
                Integer id = extractId(path);
                User user = userService.getUserById(id);
                JsonUtil.writeJson(resp, UserMapper.toDto(user));
            }
        } catch (EntityNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            JsonUtil.writeError(resp, e.getMessage());

        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtil.writeError(resp, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            UserCreateRequest dto = JsonUtil.readJson(req, UserCreateRequest.class);

            User user = userService.createUser(dto.getName());

            resp.setStatus(HttpServletResponse.SC_CREATED);
            JsonUtil.writeJson(resp, UserMapper.toDto(user));
        } catch (EntityNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            JsonUtil.writeError(resp, e.getMessage());

        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtil.writeError(resp, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            Integer id = extractId(req.getPathInfo());
            UserUpdateRequest dto = JsonUtil.readJson(req, UserUpdateRequest.class);

            User updatedUser = userService.updateUser(id, dto.getName());

            JsonUtil.writeJson(resp, UserMapper.toDto(updatedUser));
        } catch (EntityNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            JsonUtil.writeError(resp, e.getMessage());

        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtil.writeError(resp, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            Integer id = extractId(req.getPathInfo());
            userService.deleteUser(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (EntityNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            JsonUtil.writeError(resp, e.getMessage());

        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtil.writeError(resp, e.getMessage());
        }
    }

    private Integer extractId(String pathInfo) {
        try {
            return Integer.parseInt(pathInfo.substring(1));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid user ID!");
        }
    }
}
