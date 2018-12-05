package ru.job4j.sqlite;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * The XML Handler which parses XML document by SAX.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 29/11/2018
 */
public class XMLHandler extends DefaultHandler {
    /**
     * The list of all elements located by <entry href="">.
     */
    List<Integer> elements = new LinkedList<Integer>();

    /**
     * Summarizes all elements in the list.
     *
     * @return the sum of all elements.
     */
    public int sum() {
        int result = 0;
        for (int element : this.elements) {
            result += element;
        }
        return result;
    }

    /**
     * Receive notification of the start of an element. Adds the value of attribute "href" of tag "qName" to the
     * list.
     *
     * @param uri - not used.
     * @param localName - not used.
     * @param qName - tag's name.
     * @param attributes - the attributes, in this case - "href".
     * @throws SAXException not used.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("entry")) {
            this.elements.add(Integer.parseInt(attributes.getValue("href")));
        }
    }
}