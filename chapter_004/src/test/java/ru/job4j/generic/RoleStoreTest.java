package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddNewRoleThenContainerHasIt() {
        RoleStore<Role> store = new RoleStore<Role>(10);
        Role petro = new Role("petro_01", "admin");
        store.add(petro);
        assertThat(store.findById("petro_01"), is(petro));
    }

    @Test
    public void whenReplaceRoleThenContainerHasNewRole() {
        RoleStore<Role> store = new RoleStore<Role>(10);
        Role petro = new Role("petro_01", "admin");
        Role timon = new Role("timon_01", "user");
        store.add(petro);
        assertThat(store.findById("petro_01"), is(petro));
        store.replace("petro_01", timon);
        assertThat(store.findById("petro_01"), is((User) null));
        assertThat(store.findById("timon_01"), is(timon));
    }

    @Test
    public void whenDeleteRoleThenContainerHasNotThatRole() {
        RoleStore<Role> store = new RoleStore<Role>(10);
        Role petro = new Role("petro_01", "admin");
        store.add(petro);
        assertThat(store.findById("petro_01"), is(petro));
        store.delete("petro_01");
        assertThat(store.findById("petro_01"), is((User) null));
    }
}