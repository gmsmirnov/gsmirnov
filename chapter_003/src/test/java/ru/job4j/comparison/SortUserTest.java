package ru.job4j.comparison;


import org.junit.Before;
import org.junit.Test;

import java.util.*;

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
        this.users.add(new User("Serg", 38));
        this.users.add(new User("Oleg", 25));
        this.users.add(new User("Misha", 22));
        this.users.add(new User("Alex", 34));
        this.users.add(new User("Misha", 38));
        this.sortUsers.addUser(this.users.get(0));
        this.sortUsers.addUser(this.users.get(1));
        this.sortUsers.addUser(this.users.get(2));
        this.sortUsers.addUser(this.users.get(3));
        this.sortUsers.addUser(this.users.get(4));
        this.sortUsers.addUser(this.users.get(5));
        this.sortUsers.addUser(this.users.get(6));
        this.sortUsers.addUser(this.users.get(7));
        this.sortUsers.addUser(this.users.get(8));
    }

    @Test
    public void whenSortedUsers() {
        Set<User> result;
        result = this.sortUsers.sort(this.sortUsers.getUsers());
        Set<User> expected = new TreeSet<User>();
        expected.addAll(users);
        assertThat(result.toArray(), is(expected.toArray()));
    }

    @Test
    public void whenSortedUsersByNameLength() {
        List<User> result;
        result = this.sortUsers.sortNameLength(this.sortUsers.getUsers());
        List<User> expected = new ArrayList<User>();
        expected.add(this.users.get(0));
        expected.add(this.users.get(2));
        expected.add(this.users.get(4));
        expected.add(this.users.get(5));
        expected.add(this.users.get(7));
        expected.add(this.users.get(1));
        expected.add(this.users.get(3));
        expected.add(this.users.get(6));
        expected.add(this.users.get(8));
        assertThat(result.toArray(), is(expected.toArray()));
    }

    @Test
    public void whenSortedUsersByAllFields() {
        List<User> result;
        result = this.sortUsers.sortByAllFields(this.sortUsers.getUsers());
        System.out.println(result.toString());
        List<User> expected = new ArrayList<User>();
        expected.add(this.users.get(7));
        expected.add(this.users.get(0));
        expected.add(this.users.get(2));
        expected.add(this.users.get(6));
        expected.add(this.users.get(8));
        expected.add(this.users.get(5));
        expected.add(this.users.get(4));
        expected.add(this.users.get(3));
        expected.add(this.users.get(1));
        System.out.println(expected.toString());
        assertThat(result.toArray(), is(expected.toArray()));
    }
}