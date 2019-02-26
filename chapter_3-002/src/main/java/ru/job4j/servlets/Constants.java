package ru.job4j.servlets;

/**
 * Constants for CRUD Servlet.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.2
 * @since 14/02/2019
 */
public class Constants {
    /**
     * Action type "add" in the POST request.
     */
    public static final String ACTION_CREATE = "add";

    /**
     * Action type "update" in the POST request.
     */
    public static final String ACTION_UPDATE = "update";

    /**
     * Action type "delete" in the POST request.
     */
    public static final String ACTION_DELETE = "delete";

    /**
     * Param "action" in in the POST request.
     */
    public static final String PARAM_ACTION = "action";

    /**
     * ../create - the link for create user page.
     */
    public static final String PAGE_CREATE = "/create";

    /**
     * ../list - the link for the list of users page.
     */
    public static final String PAGE_LIST = "/";

    /**
     * ../edit - the link for update user page.
     */
    public static final String PAGE_UPDATE = "/edit";

    /**
     * ../user - the link for user profile page.
     */
    public static final String PAGE_USER = "/user";

    /**
     * ../error.jsp - the link for 404 page not found jsp page.
     */
    public static final String PAGE_JSP_ERROR = "/error.jsp";

    /**
     * ../user.jsp - the link for user jsp page.
     */
    public static final String PAGE_JSP_USER = "/WEB-INF/views/user.jsp";

    /**
     * ../create.jsp - the link for create user JSP-page.
     */
    public static final String PAGE_JSP_CREATE = "/WEB-INF/views/create.jsp";

    /**
     * ../list.jsp - the link for the list of users JSP-page.
     */
    public static final String PAGE_JSP_LIST = "/WEB-INF/views/list.jsp";

    /**
     * ../edit.jsp - the link for update user JSP-page.
     */
    public static final String PAGE_JSP_UPDATE = "/WEB-INF/views/edit.jsp";

    /**
     * Attribute for model.
     */
    public static final String ATTR_USER = "user";

    /**
     * Attribute for users list.
     */
    public static final String ATTR_USERS_LIST = "users";

    /**
     * Attribute for users id.
     */
    public static final String ATTR_USER_ID = "id";
}
