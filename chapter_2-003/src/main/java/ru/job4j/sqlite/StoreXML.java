package ru.job4j.sqlite;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;

/**
 * Generates XML document from database requests.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 23/11/2018
 */
public class StoreXML {
    /**
     * The XML output file.
     */
    private final File outputXML;

    /**
     * The constructor.
     *
     * @param target - target output XML file.
     */
    public StoreXML(File target) {
        this.outputXML = target;
    }

    /**
     * Saves (marshalling) the list of values to the target XML file.
     *
     * @param list - the list of all values received from database.
     * @throws JAXBException when marshal throws it.
     */
    public void save(List<Field> list) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(StoreXML.Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(new Entries(list), this.outputXML);
    }

    /**
     * Describes structure of "entry"-tag.
     */
    @XmlType(propOrder = { "entry" }, name = "entries")
    @XmlRootElement
    public static class Entries {
        private List<Field> entry;

        public Entries() {
        }

        public Entries(List<Field> values) {
            this.entry = values;
        }

        @XmlElement(name = "entry")
        public List<Field> getEntry() {
            return this.entry;
        }

        public void setEntry(List<Field> entry) {
            this.entry = entry;
        }
    }

    /**
     * Describes the structure of "value"-tag.
     */
    @XmlType(propOrder = { "value" }, name = "field")
    @XmlRootElement
    public static class Field {
        private int value;

        public Field() {
        }

        public Field(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}