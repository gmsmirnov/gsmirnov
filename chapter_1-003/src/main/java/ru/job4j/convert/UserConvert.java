package ru.job4j.convert;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Converts user list into user hash map.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 10/03/2018
 */
public class UserConvert {
    /**
     * Converts user list into user hash map. The user ID becomes the unique key.
     *
     * @param list - the list of users.
     * @return - the hash map of users.
     */
    public HashMap<UUID, User> process(List<User> list) {
        HashMap<UUID, User> result = new HashMap<UUID, User>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
