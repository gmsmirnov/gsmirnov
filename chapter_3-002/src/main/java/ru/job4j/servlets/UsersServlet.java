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
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Title</title>"
                + "</head>"
                + "<body>"
                + "</br>"
                + this.createUsersList(req.getContextPath())
                + this.createButton(req.getContextPath())
                + "</body>"
                + "</html>");
        writer.flush();
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
        doGet(req, resp);
    }

    /**
     * Creates HTML-table - the list of user.
     *
     * @param context - the web-app context (e.g. http://localhost:8081)
     * @return - HTML code of the list of users.
     */
    private String createUsersList(String context) {
        StringBuilder list = new StringBuilder("<table>");
        list.append("<tr>"
                + "<td>ID:</td>"
                + "<td>Name:</td>"
                + "<td>Login:</td>"
                + "<td>E-mail:</td>"
                + "</tr>"
        );
        for (User user : this.logic.findAll()) {
            list.append("<tr>"
                    + "<td>" + user.getId() + "</td>"
                    + "<td>" + user.getName() + "</td>"
                    + "<td>" + user.getLogin() + "</td>"
                    + "<td>" + user.getEmail() + "</td>"
                    + "<td>"
                    + this.updateButton(context, user.getId())
                    + "</td>"
                    + "<td>"
                    + this.deleteButton(context, user.getId())
                    + "</td>"
            );
            list.append("</tr>");
        }
        list.append("</table>");
        return list.toString();
    }

    /**
     * Creates the HTML code of create button.
     *
     * @param context - the web-app context (e.g. http://localhost:8081)
     * @return the HTML code of create button.
     */
    private String createButton(String context) {
        StringBuilder create = new StringBuilder("</br>");
        return create.append("<form action='" + context + Constants.PAGE_CREATE
                + "' method='get'>"
                + "<input type='submit' value='Create new user'>"
                + "</form>").toString();
    }

    /**
     * Creates the HTML code of update button.
     *
     * @param context - the web-app context (e.g. http://localhost:8081)
     * @param id - the id of user to update.
     * @return the HTML code of update button.
     */
    private String updateButton(String context, int id) {
        StringBuilder create = new StringBuilder("</br>");
        return create.append("<form action='" + context + Constants.PAGE_UPDATE
                + "' method='get'>"
                + "<input type='hidden' name='id' value='" + id + "'>"
                + "<input type='submit' value='Update'>"
                + "</form>").toString();
    }

    /**
     * Creates the HTML code of delete button.
     *
     * @param context - the web-app context (e.g. http://localhost:8081)
     * @param id - the id of user to delete.
     * @return the HTML code of delete button.
     */
    private String deleteButton(String context, int id) {
        StringBuilder create = new StringBuilder("</br>");
        return create.append("<form action='" + context + Constants.PAGE_LIST
                + "' method='post'>"
                + "<input type='hidden' name='id' value='" + id + "'>"
                + "<input type='submit' value='Delete'>"
                + "</form>").toString();
    }
}