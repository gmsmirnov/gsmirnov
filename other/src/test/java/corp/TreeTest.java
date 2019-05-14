package corp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tree testing.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/05/2019
 */
public class TreeTest {
    @Test
    public void test() {
        List<Integer> input = Arrays.asList(3, 4, 2, 3, 1, 5, 6, 3, 1, 5, 7);
        Tree tree = new Tree(Chain.parseChains(input));
        Map<Integer, List<Integer>> map = tree.pass();
        assertThat(map.get(1), is(Arrays.asList(5, 0)));
        assertThat(map.get(2), is(Arrays.asList(0)));
        assertThat(map.get(3), is(Arrays.asList(4, 1, 0)));
        assertThat(map.get(4), is(Arrays.asList(2, 0)));
        assertThat(map.get(5), is(Arrays.asList(6, 7, 0)));
        assertThat(map.get(6), is(Arrays.asList(0)));
        assertThat(map.get(7), is(Arrays.asList(0)));
    }
}