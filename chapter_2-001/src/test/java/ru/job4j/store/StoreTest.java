package ru.job4j.store;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Testing class for list's differences. Counts differences between users in previous and current lists.
 * Counts quantity of new users, modified users and deleted users.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/07/2018
 */
public class StoreTest {
    private List<Store.User> previous = new ArrayList<Store.User>();
    private List<Store.User> current = new ArrayList<Store.User>();

    @Before
    public void init() {
        // previous list
        Store.User usr0 = new Store.User(0, "Ivan");
        Store.User usr1 = new Store.User(1, "Petr"); // isn't in new list
        Store.User usr2 = new Store.User(2, "Vasya");
        Store.User usr3 = new Store.User(3, "R2D2");
        Store.User usr4 = new Store.User(4, "Kot");
        Store.User usr5 = new Store.User(5, "User"); // isn't in new list
        Store.User usr6 = new Store.User(6, "Bot");
        Store.User usr7 = new Store.User(7, "Alex");
        // 2 deleted users

        this.previous.add(usr0);
        this.previous.add(usr1);
        this.previous.add(usr2);
        this.previous.add(usr3);
        this.previous.add(usr4);
        this.previous.add(usr5);
        this.previous.add(usr6);
        this.previous.add(usr7);

        // modified users (4 users)
        Store.User usr8 = new Store.User(3, "Bot");
        Store.User usr9 = new Store.User(4, "Alex");
        Store.User usr10 = new Store.User(6, "Jolyon");
        Store.User usr11 = new Store.User(7, "Elicia");

        this.current.add(usr8);
        this.current.add(usr9);
        this.current.add(usr10);
        this.current.add(usr11);

        // not modified users (2 users)
        this.current.add(usr0);
        this.current.add(usr2);

        // new users (10 users)
        Store.User usr12 = new Store.User(8, "Sabrina");
        Store.User usr13 = new Store.User(9, "Priscilla");
        Store.User usr14 = new Store.User(10, "Katherine");
        Store.User usr15 = new Store.User(11, "Elsie");
        Store.User usr16 = new Store.User(12, "Naveed");
        Store.User usr17 = new Store.User(13, "Kellan");
        Store.User usr18 = new Store.User(14, "Ciaron");
        Store.User usr19 = new Store.User(15, "Daniyal");
        Store.User usr20 = new Store.User(16, "Keely");
        Store.User usr21 = new Store.User(17, "Mack");

        this.current.add(usr12);
        this.current.add(usr13);
        this.current.add(usr14);
        this.current.add(usr15);
        this.current.add(usr16);
        this.current.add(usr17);
        this.current.add(usr18);
        this.current.add(usr19);
        this.current.add(usr20);
        this.current.add(usr21);
    }

    @Test
    public void checkDifferences() {
        Store store = new Store();
        store.diff(this.previous, this.current);
        assertThat(store.getNew(), is(10));
        assertThat(store.getMod(), is(4));
        assertThat(store.getDel(), is(2));
    }
}