package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for user model.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 07/02/2019
 */
public class UserServlet extends HttpServlet {
    /**
     * The logic singleton instance.
     */
    private final ValidateService logic = ValidateService.getSingletonValidateServiceInstance();

    /**
     * Read method of CRUD service. The GET request.
     *
     * @param req - HTTP request.
     * @param resp - HTTP response.
     * @throws ServletException NOP
     * @throws IOException NOP
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        try {
            writer.print(this.logic.findAll());
        } finally {
            writer.close();
        }
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
        Dispatcher dispatcher = new Dispatcher(new User(
                req.getParameter(Constants.PARAM_ID),
                req.getParameter(Constants.PARAM_NAME),
                req.getParameter(Constants.PARAM_LOGIN),
                req.getParameter(Constants.PARAM_EMAIL)));
        dispatcher.sent(req.getParameter(Constants.PARAM_ACTION));
    }
}