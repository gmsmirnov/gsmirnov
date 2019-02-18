package ru.job4j.servlets;

/**
 * Constants for CRUD Servlet.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
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
     * Param "id" in in the POST request.
     */
    public static final String PARAM_ID = "id";

    /**
     * Param "name" in in the POST request.
     */
    public static final String PARAM_NAME = "name";

    /**
     * Param "login" in in the POST request.
     */
    public static final String PARAM_LOGIN = "login";

    /**
     * Param "email" in in the POST request.
     */
    public static final String PARAM_EMAIL = "email";

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
    public static final String PAGE_LIST = "/list";

    /**
     * ../edit - the link for update user page.
     */
    public static final String PAGE_UPDATE = "/edit";
}
