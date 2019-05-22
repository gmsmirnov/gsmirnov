package nok;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class NokTest {
    @Test
    public void nokCount() {
        assertThat(Nok.countNok(126, 70), is(630));
        assertThat(Nok.countNok(68, 34), is(68));
        assertThat(Nok.countNok(441, 100), is(44100));
        assertThat(Nok.countNok(84, 648), is(4536));
    }

    @Test
    public void nokArrayCount() {
        int[] array = {140, 9, 54, 250};
        assertThat(Nok.countNok(array), is(94500));
    }
}