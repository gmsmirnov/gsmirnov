package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tracker test.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 07/02/2018
 */
public class TrackerTest {
    @Test
    public void whenAddNewItemThanTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("Test 1", "Test 1 - Description");
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    @Test
    public void whenAddNewItemsThanFindItemById() {
        Tracker tracker = new Tracker();
        ArrayList<Item> items = new ArrayList<Item>();
        ArrayList<String> key = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            items.add(new Item("Tested item", "Tested item description"));
            tracker.add(items.get(i));
            key.add(items.get(i).getId());
        }
        assertThat(tracker.findById(key.get(2)), is(items.get(2)));
    }

    @Test
    public void whenAddNewItemsByNameThanFindByNameItems() {
        Tracker tracker = new Tracker();
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 10; i++) {
            items.add(new Item("Tested item", "Tested item description"));
            tracker.add(items.get(i));
            tracker.add(new Item("Non tested item", "Non tested item description"));
        }
        assertThat(tracker.findByName("Tested item").toArray(), arrayContainingInAnyOrder(items.toArray()));
    }

    @Test
    public void whenAddNewItemsThanFindAllItems() {
        Tracker tracker = new Tracker();
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 10; i++) {
            items.add(new Item("Tested item", "Tested item description"));
            tracker.add(items.get(i));
        }
        assertThat(tracker.findAll().toArray(), arrayContainingInAnyOrder(items.toArray()));
    }

    @Test
    public void whenReplaceNameThanReturnNewName() {
        Tracker tracker = new Tracker();
        Item prev = new Item("Test 1", "Test 1 - Description");
        tracker.add(prev);
        Item next = new Item("Test 2", "Test 2 - Description");
        tracker.replace(prev.getId(), next);
        assertThat(tracker.findById(prev.getId()).getName(), is("Test 2"));
    }

    @Test
    public void whenDeletingItemThanFindAllWithoutDeletedItem() {
        Tracker tracker = new Tracker();
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 10; i++) {
            items.add(new Item("Tested item", "Tested item description"));
            tracker.add(items.get(i));
        }
        Item deletedItem = new Item("Del", "Deleted item");
        tracker.add(deletedItem);
        String key = deletedItem.getId();
        for (int i = 10; i < 20; i++) {
            items.add(new Item("Tested item", "Tested item description"));
            tracker.add(items.get(i));
        }
        tracker.delete(key);
        assertThat(tracker.findAll().toArray(), arrayContainingInAnyOrder(items.toArray()));
    }
}