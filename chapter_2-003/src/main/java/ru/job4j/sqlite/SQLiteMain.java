package ru.job4j.sqlite;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

/**
 * The application entry point.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 21/11/2018
 */
public class SQLiteMain {
    private Config config;

    private StoreSQL storeSQL;

    private StoreXML storeXML;

    private ConvertXSTL convert;

    private SAXParserFactory factory;

    private final static int QUANTITY = 1_000;

    /**
     * Initializes database.
     */
    private void init() {
        this.config = new Config();
        this.storeSQL = new StoreSQL(this.config);
        storeSQL.init(SQLiteMain.QUANTITY);
    }

    /**
     * Creates XML.
     *
     * @param pathname - output path.
     * @throws JAXBException
     */
    private void createXML(String pathname) throws JAXBException {
        File target = new File("output.xml");
        this.storeXML = new StoreXML(target);
        this.storeXML.save(this.storeSQL.findAll());
    }

    /**
     * Converts XML to another format.
     *
     * @param input - input path.
     * @param output - output path.
     * @param schema - path to the schema format.
     */
    private void convertXML(String input, String output, String schema) {
        this.convert = new ConvertXSTL();
        try {
            this.convert.convert(new File(input), new File(output), new File(schema));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses XML and counts sum.
     *
     * @param pathname - input path ti the XML file.
     * @return sum.
     */
    private int parseAndSum(String pathname) {
        int result = 0;
        this.factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            XMLHandler handler = new XMLHandler();
            parser.parse(new File(pathname), handler);
            result = handler.sum();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Entry point.
     *
     * @param args - not used.
     * @throws JAXBException
     */
    public static void main(String[] args) throws JAXBException {
        long start = System.currentTimeMillis();
        SQLiteMain main = new SQLiteMain();
        main.init();
        main.createXML("output.xml");
        main.convertXML("output.xml", "output2.xml", "scheme.xsl");
        System.out.printf("The result is: %d%n", main.parseAndSum("output2.xml"));
        long end = System.currentTimeMillis();
        System.out.printf("Total time is: %dms%n", end - start);
    }
}