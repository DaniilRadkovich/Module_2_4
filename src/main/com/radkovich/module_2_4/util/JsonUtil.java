package com.radkovich.module_2_4.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JsonUtil {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtil() {

    }

    public static <T> T readJson(HttpServletRequest req, Class<T> clazz) throws IOException {
        return objectMapper.readValue(req.getInputStream(), clazz);
    }

    public static void writeJson(HttpServletResponse resp, Object object) throws IOException {
        resp.getWriter().write(objectMapper.writeValueAsString(object));
    }

    public static void writeError(HttpServletResponse response, String message) throws IOException {
        response.getWriter().write(
                objectMapper.writeValueAsString(new ErrorResponse(message)));
    }

    private static class ErrorResponse {
        public final String error;

        public ErrorResponse(String error) {
            this.error = error;
        }
    }
}
