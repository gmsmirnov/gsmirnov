package ru.job4j.servlets.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.servlets.Constants;
import ru.job4j.servlets.Validate;
import ru.job4j.servlets.ValidateService;
import ru.job4j.servlets.dao.exception.DaoSystemException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON controller. Used for update the list od cities matched to the specified country.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 17/04/2019
 */
public class JsonController extends HttpServlet {
    /**
     * The logger.
     */
    private static final Logger LOG = LogManager.getLogger(JsonController.class.getName());

    /**
     * The logic singleton instance.
     */
    private final Validate logic = ValidateService.getSingletonValidateServiceInstance();

    /**
     * Appends new user to the storage..
     *
     * @param req - HTTP request.
     * @param resp - HTTP response.
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            this.prepareResponse(this.logic.findCitiesByCountry(req.getParameter(Constants.ATTR_COUNTRY)), resp);
        } catch (DaoSystemException e) {
            JsonController.LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Prepares http-response, puts into it json-array of cities.
     *
     * @param resp  resp - HTTP response.
     * @throws IOException
     */
    private void prepareResponse(List<String> cities, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder result = new StringBuilder("[");
        int index = 0;
        for (String city : cities) {
            result.append(mapper.writeValueAsString(city));
            if (cities.size() - index > 1) {
                result.append(",");
            }
            index++;
        }
        result.append("]");
        writer.append(result.toString());
        writer.flush();
    }
}