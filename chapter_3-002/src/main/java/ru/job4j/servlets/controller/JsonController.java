package ru.job4j.servlets.controller;

import ru.job4j.servlets.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON controller.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 17/04/2019
 */
public class JsonController extends HttpServlet {
    /**
     * Users' storage.
     */
    private final Map<String, User> storage = new ConcurrentHashMap<String, User>();

    /**
     * Forms JSON users table and sends it to "index.html".
     *
     * @param req - HTTP request.
     * @param resp - HTTP response.
     * @throws IOException - if getOutputStream or writeValueAsString throws it.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        Collection<User> users = storage.values();
        StringBuilder result = new StringBuilder("[");
        int index = 0;
        for (User user : users) {
            result.append(mapper.writeValueAsString(user));
            if (users.size() - index > 1) {
                result.append(",");
            }
            index++;
        }
        result.append("]");
        writer.append(result.toString());
        writer.flush();
    }

    /**
     * Appends new user to the storage..
     *
     * @param req - HTTP request.
     * @param resp - HTTP response.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        this.storage.put(req.getParameter("login"), new User(
                req.getParameter("login"),
                req.getParameter("email"),
                req.getParameter("password"),
                req.getParameter("country"),
                req.getParameter("city")));
    }
}