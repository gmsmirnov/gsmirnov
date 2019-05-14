package corp;

import java.util.*;

/**
 * The work demonstration.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 13/05/2019
 */
public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree(Chain.parseChains(Arrays.asList(3, 4, 2, 3, 1, 5, 6, 3, 1, 5, 7)));
        System.out.println(String.format("Input chain: %s", Arrays.asList(3, 4, 2, 3, 1, 5, 6, 3, 1, 5, 7)));
        Map<Integer, List<Integer>> map = tree.pass();
        List<Integer> keys = new ArrayList<Integer>(map.keySet());
        keys.sort((o1, o2) -> {
            int result = 0;
            if (o1 > o2) {
                result = 1;
            } else if (o1 < o2) {
                result = -1;
            }
            return result;
        });
        for (Integer key : keys) {
            System.out.println(String.format("Node: %d: subordinates: %s", key, map.get(key)));
        }
    }
}