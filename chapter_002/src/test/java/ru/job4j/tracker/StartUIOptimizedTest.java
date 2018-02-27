package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * Optimized class for testing emulating users answers, uses @Before & @After.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 18/02/2018
 */
public class StartUIOptimizedTest {
    /**
     * The tracker.
     */
    private final Tracker tracker = new Tracker();

    /**
     * The number of initialized items in tracker.
     */
    private final int initNumber = 50;

    /**
     * The array of initialized items. Needs for assert method.
     */
    private final Item[] items = new Item[initNumber];

    /**
     * The result for assert method.
     */
    private Item[] result;

    /**
     * The expected for assert method.
     */
    private Item[] expected;

    /**
     * Initialize tracker before test.
     */
    @Before
    public void init() {
        for (int i = 0; i < initNumber; i++) {
            this.items[i] = new Item("Test Name", "Test Description" + i);
            this.tracker.add(items[i]);
        }
    }

    /**
     * Verify result.
     */
    @After
    public void verification() {
        assertThat(result, arrayContainingInAnyOrder(expected));
    }

    @Test
    public void whenUserWantsToSeeAllItemsThanTrackerShowThoseItems() {
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, new StubOutput(), this.tracker).init();
        this.result = new Item[this.tracker.findAll().length];
        this.result = this.tracker.findAll();
        this.expected = new Item[this.items.length];
        this.expected = this.items;
    }

    @Test
    public void whenUserWantsToSeeAllItemsAfterEditionThanTrackerShowThoseItems() {
        Input input = new StubInput(new String[]{"2", this.items[0].getId(), "New Name", "New Description", "1", "6"});
        new StartUI(input, new StubOutput(), this.tracker).init();
        this.result = new Item[this.tracker.findAll().length];
        this.result = this.tracker.findAll();
        this.expected = new Item[this.items.length];
        this.expected = this.items;
        this.expected[0] = this.tracker.findById(this.items[0].getId());
    }

    @Test
    public void whenUserWantsToSeeAllItemsAfterDeleteThanTrackerShowThoseItems() {
        Input input = new StubInput(new String[]{"3", this.items[0].getId(), "1", "6"});
        new StartUI(input, new StubOutput(), this.tracker).init();
        this.result = new Item[this.tracker.findAll().length];
        this.result = this.tracker.findAll();
        this.expected = new Item[this.items.length - 1];
        System.arraycopy(this.items, 1, this.expected, 0, this.items.length - 1);
    }

    @Test
    public void whenUserWantsToSeeAllItemsWithDefinedNameThanTrackerShowThoseItems() {
        Input input = new StubInput(new String[]{"5", "Test Name", "6"});
        new StartUI(input, new StubOutput(), this.tracker).init();
        this.result = new Item[this.tracker.findByName("Test Name").length];
        this.result = this.tracker.findAll();
        this.expected = new Item[this.items.length];
        this.expected = this.items;
    }

    @Test
    public void whenUserWantsToSeeAllItemsWithDefinedNameAfterEditionThanTrackerShowThoseItems() {
        Input input = new StubInput(new String[]{"2", this.items[0].getId(), "New Name", "New Description", "5", "New Name", "6"});
        new StartUI(input, new StubOutput(), this.tracker).init();
        this.result = new Item[this.tracker.findByName("New Name").length];
        this.result = this.tracker.findByName("New Name");
        this.expected = new Item[1];
        this.expected[0] = this.tracker.findById(this.items[0].getId());
    }

    @Test
    public void whenUserWantsToSeeAllItemsButEntersNotMenuPointThanTrackerShowThoseItems() {
        Output output = new StubOutput();
        Input input = new ValidateInput(new StubInput(new String[]{"A", "1", "6"}), output);
        new StartUI(input, output, this.tracker).init();
        this.result = new Item[this.tracker.findAll().length];
        this.result = this.tracker.findAll();
        this.expected = new Item[this.items.length];
        this.expected = this.items;
    }

    @Test
    public void whenUserWantsToSeeAllItemsButEntersOutOfRangeMenuPointThanTrackerShowThoseItems() {
        Output output = new StubOutput();
        Input input = new ValidateInput(new StubInput(new String[]{"13", "1", "6"}), output);
        new StartUI(input, output, this.tracker).init();
        this.result = new Item[this.tracker.findAll().length];
        this.result = this.tracker.findAll();
        this.expected = new Item[this.items.length];
        this.expected = this.items;
    }
}