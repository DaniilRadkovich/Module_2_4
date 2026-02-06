package com.radkovich.module_2_4.controller;

import com.radkovich.module_2_4.dto.request.FileCreateRequest;
import com.radkovich.module_2_4.dto.request.FileUpdateRequest;
import com.radkovich.module_2_4.exception.EntityNotFoundException;
import com.radkovich.module_2_4.mapper.FileMapper;
import com.radkovich.module_2_4.model.FileEntity;
import com.radkovich.module_2_4.service.FileServiceImpl;
import com.radkovich.module_2_4.service.impl.FileService;
import com.radkovich.module_2_4.util.JsonUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class FileServlet extends HttpServlet {

    private final FileService fileService = new FileServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String pathInfo = req.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<FileEntity> files = fileService.getAllFiles();
                JsonUtil.writeJson(resp, FileMapper.toDtoList(files));
                return;
            }

            Integer id = extractId(pathInfo);
            FileEntity file = fileService.getFileById(id);
            JsonUtil.writeJson(resp, FileMapper.toDto(file));

        } catch (EntityNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            JsonUtil.writeError(resp, e.getMessage());

        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtil.writeError(resp, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            FileCreateRequest dto = JsonUtil.readJson(req, FileCreateRequest.class);

            FileEntity file = fileService.createFile(dto.getName(), dto.getFilePath());

            resp.setStatus(HttpServletResponse.SC_CREATED);
            JsonUtil.writeJson(resp, FileMapper.toDto(file));

        } catch (EntityNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            JsonUtil.writeError(resp, e.getMessage());

        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonUtil.writeError(resp, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            Integer id = extractId(req.getPathInfo());
            FileUpdateRequest dto = JsonUtil.readJson(req, FileUpdateRequest.class);

            FileEntity updated = fileService.updateFile(id, dto.getName(), dto.getFilePath());

            JsonUtil.writeJson(resp, FileMapper.toDto(updated));

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
            fileService.deleteFile(id);
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
            throw new IllegalArgumentException("Invalid file ID!");
        }
    }
}
