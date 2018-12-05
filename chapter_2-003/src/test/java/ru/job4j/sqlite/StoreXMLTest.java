package ru.job4j.sqlite;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreXMLTest {
    @Test
    public void whenCreateNewFieldThenItsValueIs0() {
        StoreXML.Field field = new StoreXML.Field();
        assertThat(field.getValue(), is(0));
    }

    @Test
    public void whenCreateNewSpecifiedFieldThenItsValueIsSpecified() {
        StoreXML.Field field = new StoreXML.Field(10);
        assertThat(field.getValue(), is(10));
    }

    @Test
    public void whenSetFieldValueThenGetThatValue() {
        StoreXML.Field field = new StoreXML.Field();
        field.setValue(10);
        assertThat(field.getValue(), is(10));
    }

    @Test
    public void whenCreateEntryWithFieldsThenItContainsThoseFields() {
        List<StoreXML.Field> list = new LinkedList<StoreXML.Field>();
        StoreXML.Field field0 = new StoreXML.Field(0);
        StoreXML.Field field1 = new StoreXML.Field(1);
        StoreXML.Field field2 = new StoreXML.Field(2);
        list.add(field0);
        list.add(field1);
        list.add(field2);
        StoreXML.Entries entries = new StoreXML.Entries();
        entries.setEntry(list);
        List<StoreXML.Field> result = entries.getEntry();
        assertThat(result, is(list));
        assertThat(result.get(0), is(field0));
        assertThat(result.get(1), is(field1));
        assertThat(result.get(2), is(field2));
    }

    @Test
    public void whenCreateSpecifiedEntryWithFieldsThenItContainsThoseFields() {
        List<StoreXML.Field> list = new LinkedList<StoreXML.Field>();
        StoreXML.Field field0 = new StoreXML.Field(0);
        StoreXML.Field field1 = new StoreXML.Field(1);
        StoreXML.Field field2 = new StoreXML.Field(2);
        list.add(field0);
        list.add(field1);
        list.add(field2);
        StoreXML.Entries entries = new StoreXML.Entries(list);
        List<StoreXML.Field> result = entries.getEntry();
        assertThat(result, is(list));
        assertThat(result.get(0), is(field0));
        assertThat(result.get(1), is(field1));
        assertThat(result.get(2), is(field2));
    }

    @Test
    public void whenMarshalThenOutputFileHasRightStructure() throws JAXBException, IOException {
        File output = new File("testStoreXML.xml");
        StoreXML store = new StoreXML(output);
        List<StoreXML.Field> list = new LinkedList<StoreXML.Field>();
        list.add(new StoreXML.Field(0));
        list.add(new StoreXML.Field(1));
        list.add(new StoreXML.Field(2));
        StoreXML.Entries entries = new StoreXML.Entries(list);
        store.save(list);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<entries>\n"
                + "    <entry>\n"
                + "        <value>0</value>\n"
                + "    </entry>\n"
                + "    <entry>\n"
                + "        <value>1</value>\n"
                + "    </entry>\n"
                + "    <entry>\n"
                + "        <value>2</value>\n"
                + "    </entry>\n"
                + "</entries>\n";
        String result = new String(Files.readAllBytes(output.toPath()));
        assertThat(result, is(expected));
    }
}