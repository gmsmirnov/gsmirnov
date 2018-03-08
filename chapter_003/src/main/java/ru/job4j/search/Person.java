package ru.job4j.search;

/**
 * Describes person for phone dictionary.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 08/03/2018
 */
public class Person {
    /**
     * The name of person.
     */
    private String name;

    /**
     * The surname of person.
     */
    private String surname;

    /**
     * The phone number of person.
     */
    private String number;

    /**
     * The address of person.
     */
    private String address;

    /**
     * The person constructor.
     *
     * @param name - the name of person.
     * @param surname - the surname of person.
     * @param number - the number of person.
     * @param address - the address of person.
     */
    public Person(String name, String surname, String number, String address) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.address = address;
    }

    /**
     * Gets person name.
     *
     * @return - the name of person.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets person surname.
     *
     * @return - the surname of person.
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Gets person phone number.
     *
     * @return - the phone number of person.
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Gets person address.
     *
     * @return - the address of person.
     */
    public String getAddress() {
        return this.address;
    }
}
