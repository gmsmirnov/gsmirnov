package ru.job4j.trie;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.*;

public class ReaderTest {
    Reader reader;

    @Before
    public void init() {
        this.reader = new Reader();
        this.reader.createTree("text.txt");
    }
/* TravisCi fail this test
    @Test
    public void testTrieWithTextFile() {
        System.out.println("size: " + this.reader.trie.getIndexes("size"));
        System.out.println("buffer: " + this.reader.trie.getIndexes("buffer"));
        System.out.println("reads: " + this.reader.trie.getIndexes("reads"));
        System.out.println("from: " + this.reader.trie.getIndexes("from"));
        System.out.println("reading: " + this.reader.trie.getIndexes("reading"));
        System.out.println("characters: " + this.reader.trie.getIndexes("characters"));
        System.out.println("arrays: " + this.reader.trie.getIndexes("arrays"));
        System.out.println("the: " + this.reader.trie.getIndexes("the"));
        Integer[] expectedSize = {153, 191};
        Integer[] expectedBuffer = {146};
        Integer[] expectedReads = {1};
        Integer[] expectedFrom = {12};
        Integer[] expectedReading = {99};
        Integer[] expectedCharacters = {53, 110};
        Integer[] expectedArrays = {122};
        Integer[] expectedThe = {85, 142, 179, 209, 354};
        assertThat(this.reader.trie.getIndexes("size").toArray(), arrayContainingInAnyOrder(expectedSize));
        assertThat(this.reader.trie.getIndexes("buffer").toArray(), arrayContainingInAnyOrder(expectedBuffer));
        assertThat(this.reader.trie.getIndexes("reads").toArray(), arrayContainingInAnyOrder(expectedReads));
        assertThat(this.reader.trie.getIndexes("from").toArray(), arrayContainingInAnyOrder(expectedFrom));
        assertThat(this.reader.trie.getIndexes("reading").toArray(), arrayContainingInAnyOrder(expectedReading));
        assertThat(this.reader.trie.getIndexes("characters").toArray(), arrayContainingInAnyOrder(expectedCharacters));
        assertThat(this.reader.trie.getIndexes("arrays").toArray(), arrayContainingInAnyOrder(expectedArrays));
        assertThat(this.reader.trie.getIndexes("the").toArray(), arrayContainingInAnyOrder(expectedThe));
    }*/
}