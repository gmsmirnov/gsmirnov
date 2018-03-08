package ru.job4j.search;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

public class PhoneDictionaryTest {
    private final PhoneDictionary phones = new PhoneDictionary();
    private final List<Person> persons = new ArrayList<Person>();

    @Before
    public void init() {
        this.persons.add(new Person("Petr", "Arsentev", "543872", "Bryansk"));
        this.persons.add(new Person("Ivan", "Ivanov", "548912", "Uryupinsk"));
        this.persons.add(new Person("Petr", "Petrov", "52457", "Uryupinsk"));
        this.phones.add(this.persons.get(0));
        this.phones.add(this.persons.get(1));
        this.phones.add(this.persons.get(2));
    }

    @Test
    public void whenFindByName() {
        List<Person> result = this.phones.find("Petr");
        assertThat(result.iterator().next().getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindByName2() {
        List<Person> result = this.phones.find("Petr");
        List<Person> expected = new ArrayList<Person>();
        expected.add(this.persons.get(0));
        expected.add(this.persons.get(2));
        assertThat(result.toArray(), is(expected.toArray()));
    }

    @Test
    public void whenFindByAddress() {
        List<Person> result = this.phones.find("Ury");
        List<Person> expected = new ArrayList<Person>();
        expected.add(this.persons.get(1));
        expected.add(this.persons.get(2));
        assertThat(result.toArray(), is(expected.toArray()));
    }

    @Test
    public void whenFindByNumber() {
        List<Person> result = this.phones.find("54");
        List<Person> expected = new ArrayList<Person>();
        expected.add(this.persons.get(0));
        expected.add(this.persons.get(1));
        assertThat(result.toArray(), is(expected.toArray()));
    }
}
