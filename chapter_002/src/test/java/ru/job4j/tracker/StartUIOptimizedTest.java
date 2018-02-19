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
 * @version 1.0
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
    public void whenUserWantsToSeeAllItemsWithDefinedNameThanTrackerShowThoseItems() {
        Input input = new StubInput(new String[]{"5", "Test Name", "6"});
        new StartUI(input, new StubOutput(), this.tracker).init();
        this.result = new Item[this.tracker.findByName("Test Name").length];
        this.result = this.tracker.findAll();
        this.expected = new Item[this.items.length];
        this.expected = this.items;
    }
}