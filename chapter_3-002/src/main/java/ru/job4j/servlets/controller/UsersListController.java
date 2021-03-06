package ru.job4j.servlets.controller;

import ru.job4j.servlets.Constants;
import ru.job4j.servlets.Dispatcher;
import ru.job4j.servlets.ValidateService;
import ru.job4j.servlets.dao.exception.DaoSystemException;
import ru.job4j.servlets.dao.exception.NoSuchModelException;
import ru.job4j.servlets.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for user model.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 07/02/2019
 */
public class UsersListController extends HttpServlet {
    /**
     * The logic singleton instance.
     */
    private final ValidateService logic = ValidateService.getSingletonValidateServiceInstance();

    /**
     * Shows the table of all registered users. The GET request. Read method of CRUD service. The GET request.
     *
     * @param req - HTTP request.
     * @param resp - HTTP response.
     * @throws ServletException NOP
     * @throws IOException NOP
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute(Constants.ATTR_USERS_LIST, this.logic.findAll());
        } catch (DaoSystemException e) {
            /*NOP*/
        }
        req.getRequestDispatcher(Constants.PAGE_JSP_LIST).forward(req, resp);
    }

    /**
     * Create, update and delete methods of CRUD service. The POST request.
     *
     * @param req - HTTP request.
     * @param resp - HTTP response.
     * @throws ServletException NOP
     * @throws IOException NOP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Dispatcher dispatcher = new Dispatcher(this.logic.findById(Integer.parseInt(req.getParameter(User.PARAM_ID))));
            dispatcher.sent(Constants.ACTION_DELETE);
            this.doGet(req, resp);
        } catch (DaoSystemException | NoSuchModelException e) {
            /*NOP*/
        }
    }
}