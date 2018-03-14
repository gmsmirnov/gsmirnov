package ru.job4j.comparison;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests sorting users.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/03/2018
 */
public class SortUserTest {
    private final SortUser sortUsers = new SortUser();
    private final List<User> users = new ArrayList<User>();

    @Before
    public void init() {
        this.users.add(new User("Ivan", 33));
        this.users.add(new User("Vasya", 33));
        this.users.add(new User("Ivan", 34));
        this.users.add(new User("Vasya", 32));
    }

    @Test
    public void whenSortedUsers() {
        Set<User> result;
        this.sortUsers.addUser(this.users.get(0));
        this.sortUsers.addUser(this.users.get(1));
        this.sortUsers.addUser(this.users.get(2));
        this.sortUsers.addUser(this.users.get(3));
        result = this.sortUsers.sort(this.sortUsers.getUsers());
        System.out.println(result.toString());
        Set<User> expected = new HashSet<User>();
        expected.add(this.users.get(3));
        expected.add(this.users.get(0));
        expected.add(this.users.get(1));
        expected.add(this.users.get(2));
        assertThat(result.toArray(), is(expected.toArray()));
    }
}