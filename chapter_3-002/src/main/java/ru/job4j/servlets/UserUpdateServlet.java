package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for updating a user.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 16/02/2019
 */
public class UserUpdateServlet extends HttpServlet {
    /**
     * The logic singleton instance.
     */
    private final ValidateService logic = ValidateService.getSingletonValidateServiceInstance();

    /**
     * Shows filled form to update the specified user. The GET request.
     *
     * @param req - HTTP request.
     * @param resp - HTTP response.
     * @throws ServletException NOP
     * @throws IOException OP
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.sendRedirect(String.format("%s%s?id=%d", req.getContextPath(), Constants.PAGE_JSP_UPDATE, Integer.parseInt(req.getParameter(Constants.PARAM_ID))));
    }

    /**
     * Update a user request. The POST request.
     *
     * @param req - HTTP request.
     * @param resp - HTTP response.
     * @throws ServletException NOP
     * @throws IOException NOP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Dispatcher dispatcher = new Dispatcher(new User(
                req.getParameter(Constants.PARAM_ID),
                req.getParameter(Constants.PARAM_NAME),
                req.getParameter(Constants.PARAM_LOGIN),
                req.getParameter(Constants.PARAM_EMAIL)
        ));
        dispatcher.sent(Constants.ACTION_UPDATE);
        resp.sendRedirect(String.format("%s%s?id=%d", req.getContextPath(), Constants.PAGE_JSP_UPDATE, Integer.parseInt(req.getParameter(Constants.PARAM_ID))));
    }
}