package corp;

import java.util.ArrayList;
import java.util.List;

/**
 * Input chain parsing.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/05/2019
 */
public class Chain {
    /**
     * The index of the root element.
     */
    private static final int ROOT_INDEX = 0;

    /**
     * Parses input chain into the list of chains. Each of these uses for tree's branch building.
     *
     * @param chain - input chain.
     * @return a list of chains for tree's branches building.
     */
    public static List<List<Integer>> parseChains(List<Integer> chain) {
        List<List<Integer>> chains = new ArrayList<List<Integer>>();
        int root = chain.get(Chain.ROOT_INDEX);
        List<Integer> newChain = null;
        for (int index = 0; index < chain.size(); index++) {
            if (chain.get(index) == root) {
                newChain = new ArrayList<Integer>();
            }
            newChain.add(chain.get(index));
            if (index == chain.size() - 1 || chain.get(index + 1) == root) {
                chains.add(newChain);
            }
        }
        return chains;
    }
}