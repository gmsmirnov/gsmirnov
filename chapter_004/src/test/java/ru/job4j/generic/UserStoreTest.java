package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserStoreTest {
    @Test
    public void whenAddNewUserThenContainerHasIt() {
        UserStore<User> store = new UserStore<User>(10);
        User petro = new User("petro_01", "petrovich", 50);
        store.add(petro);
        assertThat(store.findById("petro_01"), is(petro));
    }

    @Test
    public void whenReplaceUserThenContainerHasNewUser() {
        UserStore<User> store = new UserStore<User>(10);
        User petro = new User("petro_01", "petrovich", 50);
        User timon = new User("timon_01", "timon", 45);
        store.add(petro);
        assertThat(store.findById("petro_01"), is(petro));
        store.replace("petro_01", timon);
        assertThat(store.findById("petro_01"), is((User) null));
        assertThat(store.findById("timon_01"), is(timon));
    }

    @Test
    public void whenDeleteUserThenContainerHasNotThatUser() {
        UserStore<User> store = new UserStore<User>(10);
        User petro = new User("petro_01", "petrovich", 50);
        store.add(petro);
        assertThat(store.findById("petro_01"), is(petro));
        store.delete("petro_01");
        assertThat(store.findById("petro_01"), is((User) null));
    }
}