package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Describes phone dictionary.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 08/03/2018
 */
public class PhoneDictionary {
    /**
     * The phone dictionary storage.
     */
    private List<Person> persons = new ArrayList<Person>();

    /**
     * Adds new person to the phone dictionary.
     *
     * @param person - new person.
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Returns the list of persons which contain the key word in any field.
     *
     * @param key - the key word.
     * @return - the list of persons which contain the key word in any field.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<Person>();
        for (Person person : this.persons) {
            if (person.getName().contains(key) || person.getSurname().contains(key)
                    || person.getNumber().contains(key) || person.getAddress().contains(key)) {
                result.add(person);
            }
        }
        return result;
    }
}
