package ru.job4j.sqlite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Class StoreSQL - testing.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 21/11/2018
 */
public class StoreSQLTest {

    private StoreSQL sql;

    @Before
    public void init() {
        this.sql = new StoreSQL(new Config());
    }

    @After
    public void clear() throws IOException {
        this.sql.close();
    }

    @Test
    public void whenIsDBStructureThenTrue() {
        this.sql.init(0);
        assertThat(this.sql.isStructure(), is(true));
    }

    @Test
    public void whenAdds10ItemsToDBThenDBHasThem() {
        this.sql.clearEntryTable();
        int quantity = 10;
        this.sql.init(quantity);
        List<StoreXML.Field> list = this.sql.findAll();
        for (int i = 0; i < list.size(); i++) {
            assertThat(list.get(i).getValue(), is(i + 1));
        }
        assertThat(list.size(), is(quantity));
    }

    @Test
    public void whenDeleteTableThenNoStructure() {
        this.sql.clearEntryTable();
        System.out.println(this.sql.findAll().size());
        assertThat(this.sql.isTableEmpty(), is(true));
        int quantity = 10;
        this.sql.init(quantity);
        assertThat(this.sql.isTableEmpty(), is(false));
        this.sql.deleteTable();
        assertThat(this.sql.isTableEmpty(), is(true));
        assertThat(this.sql.isStructure(), is(false));
    }

    @Test
    public void whenCreateTableThenIsStructure() {
        this.sql.deleteTable();
        assertThat(this.sql.isStructure(), is(false));
        this.sql.init(10);
        assertThat(this.sql.isStructure(), is(true));
    }
}