package ru.job4j.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple prefix tree. The tree structure representing words. Each node represent a character.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 24/07/2018
 */
public class Trie {
    /**
     * The trie's root.
     */
    private final TrieNode root;

    /**
     * Creates new trie.
     */
    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * Adds new word to the trie structure and it's input index. If the word already exists, appends new input index
     * to the list.
     *
     * @param word - the specified word.
     * @param index - the word's index.
     */
    public void addWord(String word, int index) {
        this.root.addWord(word.toLowerCase(), index);
    }

    /**
     * Gets input indexes of the specified word.
     *
     * @param word - the specified word.
     * @return - input indexes of the specified word, or empty list if the specified word not exists.
     */
    public List<Integer> getIndexes(String word) {
        String wordFix = word.toLowerCase();
        List<Integer> result = new ArrayList<Integer>();
        if (this.checkWord(wordFix)) {
            TrieNode lastNode = this.root;
            for (int index = 0; index < wordFix.length(); index++) {
                lastNode = lastNode.getChildNode(wordFix.charAt(index));
                if (lastNode == null) { // the word not exists
                    break;
                }
            }
            if (lastNode != null) {
                result = lastNode.getInIndex();
            }
        }
        return result;
    }

    /**
     * Checks the specified word. If word contains any non-letter symbols, than false.
     *
     * @param word - the specified word.
     * @return - true if the specified word doesn't contain any non-letter symbols, false either.
     */
    private boolean checkWord(String word) {
        boolean result = true;
        for (int index = 0; index < word.length(); index++) {
            if (!Character.isLetter(word.charAt(index))) {
                result = false;
            }
        }
        return result;
    }
}