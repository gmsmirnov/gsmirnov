package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
    private final ArrayList<Item> items = new ArrayList<Item>();

    /**
     * The result for assert method.
     */
    private ArrayList<Item> result;

    /**
     * The expected for assert method.
     */
    private ArrayList<Item> expected;

    /**
     * Initialize tracker before test.
     */
    @Before
    public void init() {
        for (int i = 0; i < initNumber; i++) {
            this.items.add(new Item("Test Name", "Test Description" + i));
            this.tracker.add(items.get(i));
        }
    }

    /**
     * Verify result.
     */
    @After
    public void verification() {
        assertThat(result.toArray(), arrayContainingInAnyOrder(expected.toArray()));
    }

    @Test
    public void whenUserWantsToSeeAllItemsThanTrackerShowThoseItems() {
        ArrayList<String> inParams = new ArrayList<String>();
        inParams.add("1");
        inParams.add("6");
        Input input = new StubInput(inParams);
        new StartUI(input, new StubOutput(), this.tracker).init();
        this.result = this.tracker.findAll();
        this.expected = this.items;
    }

    @Test
    public void whenUserWantsToSeeAllItemsAfterEditionThanTrackerShowThoseItems() {
        ArrayList<String> inParams = new ArrayList<String>();
        inParams.add("2");
        inParams.add(this.items.get(0).getId());
        inParams.add("New Name");
        inParams.add("New Description");
        inParams.add("1");
        inParams.add("6");
        Input input = new StubInput(inParams);
        new StartUI(input, new StubOutput(), this.tracker).init();
        this.result = this.tracker.findAll();
        this.expected = this.items;
        this.expected.set(0, this.tracker.findById(this.items.get(0).getId()));
    }

    @Test
    public void whenUserWantsToSeeAllItemsAfterDeleteThanTrackerShowThoseItems() {
        ArrayList<String> inParams = new ArrayList<String>();
        inParams.add("3");
        inParams.add(this.items.get(0).getId());
        inParams.add("1");
        inParams.add("6");
        Input input = new StubInput(inParams);
        new StartUI(input, new StubOutput(), this.tracker).init();
        this.result = this.tracker.findAll();
        this.items.remove(0);
        this.expected = this.items;
    }

    @Test
    public void whenUserWantsToSeeAllItemsWithDefinedNameThanTrackerShowThoseItems() {
        ArrayList<String> inParams = new ArrayList<String>();
        inParams.add("5");
        inParams.add("Test Name");
        inParams.add("6");
        Input input = new StubInput(inParams);
        new StartUI(input, new StubOutput(), this.tracker).init();
        this.result = this.tracker.findByName("Test Name");
        this.expected = this.items;
    }

    @Test
    public void whenUserWantsToSeeAllItemsWithDefinedNameAfterEditionThanTrackerShowThoseItems() {
        ArrayList<String> inParams = new ArrayList<String>();
        inParams.add("2");
        inParams.add(this.items.get(0).getId());
        inParams.add("New Name");
        inParams.add("New Description");
        inParams.add("5");
        inParams.add("New Name");
        inParams.add("6");
        Input input = new StubInput(inParams);
        new StartUI(input, new StubOutput(), this.tracker).init();
        this.result = this.tracker.findByName("New Name");
        this.expected = new ArrayList<Item>();
        this.expected.add(this.tracker.findById(this.items.get(0).getId()));
    }

    @Test
    public void whenUserWantsToSeeAllItemsButEntersNotMenuPointThanTrackerShowThoseItems() {
        Output output = new StubOutput();
        ArrayList<String> inParams = new ArrayList<String>();
        inParams.add("A");
        inParams.add("1");
        inParams.add("6");
        Input input = new ValidateInput(new StubInput(inParams), output);
        new StartUI(input, output, this.tracker).init();
        this.result = this.tracker.findAll();
        this.expected = this.items;
    }

    @Test
    public void whenUserWantsToSeeAllItemsButEntersOutOfRangeMenuPointThanTrackerShowThoseItems() {
        Output output = new StubOutput();
        ArrayList<String> inParams = new ArrayList<String>();
        inParams.add("13");
        inParams.add("1");
        inParams.add("6");
        Input input = new ValidateInput(new StubInput(inParams), output);
        new StartUI(input, output, this.tracker).init();
        this.result = this.tracker.findAll();
        this.expected = this.items;
    }
}