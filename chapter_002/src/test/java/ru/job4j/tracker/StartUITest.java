package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Class for testing emulating users answers.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 12/02/2018
 */
public class StartUITest {
    @Test
    public void whenUserAddNewItemThanTrackerHasTheItemWithTheSameName() {
        Tracker tracker = new Tracker();
        ArrayList<String> inParams = new ArrayList<String>();
        inParams.add("0");
        inParams.add("test name");
        inParams.add("test description");
        inParams.add("6");
        Input input = new StubInput(inParams);
        new StartUI(input, new StubOutput(), tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    @Test
    public void whenUserUpdateItemThanTrackerHasUpdatedItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "test description");
        tracker.add(item);
        ArrayList<String> inParams = new ArrayList<String>();
        inParams.add("2");
        inParams.add(item.getId());
        inParams.add("new name");
        inParams.add("new description");
        inParams.add("6");
        Input input = new StubInput(inParams);
        new StartUI(input, new StubOutput(), tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("new name"));
    }

    @Test
    public void whenItemWasDeletedThanTrackerHasNoItemWithSuchId() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "test description");
        tracker.add(item);
        ArrayList<String> inParams = new ArrayList<String>();
        inParams.add("3");
        inParams.add(item.getId());
        inParams.add("6");
        Input input = new StubInput(inParams);
        new StartUI(input, new StubOutput(), tracker).init();
        assertNull(tracker.findById(item.getId()));
    }

    @Test
    public void whenUserAddNewItemThanTrackerHasTheItemWithTheSameId() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "test description");
        tracker.add(item);
        ArrayList<String> inParams = new ArrayList<String>();
        inParams.add("4");
        inParams.add(item.getId());
        inParams.add("6");
        Input input = new StubInput(inParams);
        new StartUI(input, new StubOutput(), tracker).init();
        assertThat(tracker.findById(item.getId()).getId(), is(item.getId()));
    }

    @Test
    public void whenUserAddNewItemsThanTrackerHasAllThisItems() {
        Tracker tracker = new Tracker();
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 20; i++) {
            items.add(new Item("Item: " + i, "Description " + i));
            tracker.add(items.get(i));
        }
        ArrayList<String> inParams = new ArrayList<String>();
        inParams.add("1");
        inParams.add("6");
        Input input = new StubInput(inParams);
        new StartUI(input, new StubOutput(), tracker).init();
        assertThat(tracker.findAll().toArray(), arrayContainingInAnyOrder(items.toArray()));
    }

    @Test
    public void whenUserAddNewItemsWithOneNameThanTrackerHasThisItems() {
        Tracker tracker = new Tracker();
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 20; i++) {
            items.add(new Item("Item", "Description " + i));
            tracker.add(items.get(i));
        }
        ArrayList<String> inParams = new ArrayList<String>();
        inParams.add("5");
        inParams.add("Item");
        inParams.add("6");
        Input input = new StubInput(inParams);
        new StartUI(input, new StubOutput(), tracker).init();
        assertThat(tracker.findByName("Item").toArray(), arrayContainingInAnyOrder(items.toArray()));
    }
}