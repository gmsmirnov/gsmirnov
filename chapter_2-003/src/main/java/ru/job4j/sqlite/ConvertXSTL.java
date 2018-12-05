package ru.job4j.sqlite;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * The converter to XSL format.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 27/11/2018
 */
public class ConvertXSTL {
    /**
     * Converts the XML document.
     *
     * @param source - the source file.
     * @param dest - the destination file.
     * @param scheme - the scheme file.
     * @throws IOException if error occurs while reading the file.
     * @throws TransformerException if an unrecoverable error occurs during transformation.
     */
    public void convert(File source, File dest, File scheme) throws IOException, TransformerException {
        byte[] xml = Files.readAllBytes(source.toPath());
        byte[] xsl = Files.readAllBytes(scheme.toPath());

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
                new StreamSource(
                        new ByteArrayInputStream(xsl))
        );
        transformer.transform(new StreamSource(
                        new ByteArrayInputStream(xml)),
                new StreamResult(dest)
        );
    }
}
