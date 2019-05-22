package nok;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class NodTest {
    @Test
    public void nodCount() {
        assertThat(Nod.countNod(64, 48), is(16));
        assertThat(Nod.countNod(111, 432), is(3));
        assertThat(Nod.countNod(661, 113), is(1));
        assertThat(Nod.countNod(72, 96), is(24));
    }
}