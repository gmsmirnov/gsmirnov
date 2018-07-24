package ru.job4j.trie;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.*;

public class TrieTest {
    @Test
    public void whenWordIsInTrieThanReturnListEntries() {
        Trie trie = new Trie();
        trie.addWord("cat", 2);
        trie.addWord("cat", 30);
        trie.addWord("dog", 15);
        System.out.println("cat: " + trie.getIndexes("cat"));
        System.out.println("dog: " + trie.getIndexes("dog"));
        System.out.println("monkey: " + trie.getIndexes("monkey"));
        Integer[] expectedCat = {30, 2};
        Integer[] expectedDog = {15};
        Integer[] expectedMonkey = {};
        assertThat(trie.getIndexes("cat").toArray(), arrayContainingInAnyOrder(expectedCat));
        assertThat(trie.getIndexes("dog").toArray(), arrayContainingInAnyOrder(expectedDog));
        assertThat(trie.getIndexes("monkey").toArray(), arrayContainingInAnyOrder(expectedMonkey));
    }
}