package ru.job4j.sqlite;

import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConvertXSTLTest {
    @Test
    public void whenConvertInputThenOutputHasRightFormat() {
        ConvertXSTL xsqt = new ConvertXSTL();
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><entries><entry href=\"1\"/><entry href=\"2\"/></entries>";
        try {
            File input = new File("testConvertXSTLinput.xml");
            File output = new File("testConvertXSTLoutput.xml");
            xsqt.convert(input, output, new File("scheme.xsl"));
            String result = new String(Files.readAllBytes(output.toPath()));
            assertThat(result, is(expected));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}