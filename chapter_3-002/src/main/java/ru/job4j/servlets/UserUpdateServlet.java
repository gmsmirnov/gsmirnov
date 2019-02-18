package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for updating a user.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
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
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Create new user</title>"
                + "</head>"
                + "<body>"
                + "Update user:"
                + this.createUserForm(req.getContextPath(), Integer.parseInt(req.getParameter(Constants.PARAM_ID)))
                + this.listButton(req.getContextPath())
                + "</body>"
                + "</html>");
        writer.flush();
    }

    /**
     * Deletes a user request. The POST request.
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
     * Creates a filled HTML-form for update the specified user.
     *
     * @param context - the web-app context (e.g. http://localhost:8081).
     * @param id - the id of user to update.
     * @return - HTML code of update user form.
     */
    private String createUserForm(String context, int id) {
        User user = this.logic.findById(id);
        StringBuilder form = new StringBuilder("Create new user:");
        form.append("<form action='" + context + Constants.PAGE_UPDATE + "' method='post'>"
                + "<table>"
                + "<tr><td>ID:</td><td><input type='text' name='id' value='" + user.getId() +  "'/></td></tr>"
                + "<tr><td>Name:</td><td><input type='text' name='name' value='" + user.getName() +  "'/></td></tr>"
                + "<tr><td>Login:</td><td><input type='text' name='login' value='" + user.getLogin() +  "'/></td></tr>"
                + "<tr><td>E-mail:</td><td><input type='text' name='email' value='" + user.getEmail() +  "'/></td></tr>"
                + "<tr><td><input type='submit' value='Edit'></td></tr>"
                + "</table>"
                + "</form>"
        );
        return form.toString();
    }
}