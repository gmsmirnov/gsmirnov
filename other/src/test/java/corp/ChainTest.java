package corp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Chain testing.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/05/2019
 */
public class ChainTest {
    @Test
    public void whenParseChainsThenTrue() {
        List<List<Integer>> chains = Chain.parseChains(Arrays.asList(3, 4, 2, 3, 1));
        assertThat(chains.get(0), is(Arrays.asList(3, 4, 2)));
        assertThat(chains.get(1), is(Arrays.asList(3, 1)));
    }
}