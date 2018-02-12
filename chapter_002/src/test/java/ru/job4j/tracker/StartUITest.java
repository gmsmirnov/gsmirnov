package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Class for testing emulating users answers.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 12/02/2018
 */
public class StartUITest {
    @Test
    public void whenUserAddNewItemThanTrackerHasTheItemWithTheSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "test description", "6"});
        new StartUI(input, new StubOutput(), tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUserUpdateItemThanTrackerHasUpdatedItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "test description");
        tracker.add(item);
        Input input = new StubInput(new String[]{"2", item.getId(), "new name", "new description", "6"});
        new StartUI(input, new StubOutput(), tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("new name"));
    }

    @Test
    public void whenItemWasDeletedThanTrackerHasNoItemWithSuchId() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "test description");
        tracker.add(item);
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUI(input, new StubOutput(), tracker).init();
        assertNull(tracker.findById(item.getId()));
    }

    @Test
    public void whenUserAddNewItemThanTrackerHasTheItemWithTheSameId() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "test description");
        tracker.add(item);
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        new StartUI(input, new StubOutput(), tracker).init();
        assertThat(tracker.findById(item.getId()).getId(), is(item.getId()));
    }

    @Test
    public void whenUserAddNewItemsThanTrackerHasAllThisItems() {
        Tracker tracker = new Tracker();
        Item[] items = new Item[20];
        for (int i = 0; i < 20; i++) {
            items[i] = new Item("Item: " + i, "Description " + i);
            tracker.add(items[i]);
        }
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, new StubOutput(), tracker).init();
        assertThat(tracker.findAll(), arrayContainingInAnyOrder(items));
    }

    @Test
    public void whenUserAddNewItemsWithOneNameThanTrackerHasThisItems() {
        Tracker tracker = new Tracker();
        Item[] items = new Item[20];
        for (int i = 0; i < 20; i++) {
            items[i] = new Item("Item", "Description " + i);
            tracker.add(items[i]);
        }
        Input input = new StubInput(new String[]{"5", "Item", "6"});
        new StartUI(input, new StubOutput(), tracker).init();
        assertThat(tracker.findByName("Item"), arrayContainingInAnyOrder(items));
    }
}