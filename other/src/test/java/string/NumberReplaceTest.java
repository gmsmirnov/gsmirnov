package string;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class NumberReplaceTest {
    @Test
    public void replaceNumbersTest() {
        Long start = System.currentTimeMillis();
        assertThat(NumberReplace.replaceNumbers("abc5xabc15yabc"), is("abcxxxxxabcyyyyyyyyyyyyyyyabc"));
        assertThat(NumberReplace.replaceNumbers("566"), is(""));
        assertThat(NumberReplace.replaceNumbers("9t"), is("ttttttttt"));
        assertThat(NumberReplace.replaceNumbers("9t8888"), is("ttttttttt"));
        Long end = System.currentTimeMillis();
        System.out.printf("Elapsed %d ms\n", end - start);
    }
}