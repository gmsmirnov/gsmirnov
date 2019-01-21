package ru.job4j.convert;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Tests conversion of users list into users hash map.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 10/03/2018
 */
public class UserConvertTest {
    private ArrayList<User> users = new ArrayList<User>();

    @Before
    public void init() {
        users.add(new User("Pupkin Vasya", "Uriupinsk"));
        users.add(new User("Ivanov Ivan", "Karaganda"));
        users.add(new User("Petrov Petr", "Uriupinsk"));
    }

    @Test
    public void whenUserListConvertsIntoUserHashMapThenHashMapContainsUserWithSameName() {
        HashMap<UUID, User> usersMap;
        usersMap = new UserConvert().process(users);
        User user1 = users.get(0);
        String result = usersMap.get(user1.getId()).getName();
        assertThat(result, is("Pupkin Vasya"));
    }

    @Test
    public void whenUserListConvertsIntoUserHashMapThenHashMapContainsUserWithSameCity() {
        HashMap<UUID, User> usersMap;
        usersMap = new UserConvert().process(users);
        User user2 = users.get(1);
        String result = usersMap.get(user2.getId()).getCity();
        assertThat(result, is("Karaganda"));
    }
}
