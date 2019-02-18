package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for creating a new user.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 16/02/2019
 */
public class UserCreateServlet extends HttpServlet {
    /**
     * Shows empty form to create a new user. The GET request.
     *
     * @param req - HTTP request.
     * @param resp - HTTP response.
     * @throws ServletException NOP
     * @throws IOException NOP
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Create new user</title>"
                + "</head>"
                + "<body>"
                + this.createUserForm(req.getContextPath())
                + this.listButton(req.getContextPath())
                + "</body>"
                + "</html>");
        writer.flush();
    }

    /**
     * Creates a new user request. The POST request.
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
        dispatcher.sent(Constants.ACTION_CREATE);
        doGet(req, resp);
    }

    /**
     * Creates the HTML code of list of users button.
     *
     * @param context - the web-app context (e.g. http://localhost:8081)
     * @return the HTML code of list of users button.
     */
    private String listButton(String context) {
        StringBuilder create = new StringBuilder("</br>");
        return create.append("<form action='" + context + Constants.PAGE_LIST
                + "' method='get'>"
                + "<input type='submit' value='List'>"
                + "</form>").toString();
    }

    /**
     * Creates HTML-form for creating a new user.
     *
     * @param context - the web-app context (e.g. http://localhost:8081)
     * @return - HTML code of the creating a new user form.
     */
    private String createUserForm(String context) {
        StringBuilder form = new StringBuilder("Create new user:");
        form.append("<form action='" + context + Constants.PAGE_CREATE + "' method='post'>"
                + "ID: <input type='text' name='id'/>"
                + "</br>"
                + "Name: <input type='text' name='name'/>"
                + "</br>"
                + "Login: <input type='text' name='login'/>"
                + "</br>"
                + "E-mail: <input type='text' name='email'/>"
                + "</br>"
                + "<input type='submit' value='Create'>"
                + "</form>"
        );
        return form.toString();
    }
}