package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for user model.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 07/02/2019
 */
public class UsersServlet extends HttpServlet {
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
        resp.setContentType("text/html");
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), Constants.PAGE_JSP_LIST));
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
        resp.setContentType("text/html");
        Dispatcher dispatcher = new Dispatcher(this.logic.findById(Integer.parseInt(req.getParameter(Constants.PARAM_ID))));
        dispatcher.sent(Constants.ACTION_DELETE);
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), Constants.PAGE_JSP_LIST));
    }
}