package ru.job4j.trie;

import java.util.ArrayList;

/**
 * The description of a trie node.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 24/07/2018
 */
public class TrieNode {
    private TrieNode parent;
    private TrieNode[] children;
    private boolean isWord;
    private char character;
    private ArrayList<Integer> inIndex = new ArrayList<Integer>();

    /**
     * The constructor for root. The root doesn't contain any character. It contains only 26 children
     * (one for ech alphabet letter).
     */
    public TrieNode() {
        this.parent = null;
        this.children = new TrieNode[26];
        this.isWord = false;
    }

    /**
     * The constructor for children.
     *
     * @param character - the character, stored in this node.
     */
    public TrieNode(char character) {
        this();
        this.character = character;
    }

    /**
     * Adds new word with all characters to the structure. The method calls recursively and appends any new node with
     * a new letter to the structure. In the end it adds the input index to the last node and set isWord flag.
     *
     * @param word - the specified word.
     * @param index - the specified word's index.
     */
    public void addWord(String word, int index) {
        int charIndex = word.charAt(0) - 'a';
        if (this.children[charIndex] == null) {
            this.children[charIndex] = new TrieNode(word.charAt(0));
            this.children[charIndex].parent = this;
        }
        if (word.length() > 1) {
            this.children[charIndex].addWord(word.substring(1), index);
        } else {
            this.children[charIndex].isWord = true;
            this.children[charIndex].inIndex.add(index);
        }
    }

    /**
     * Gets the child node, specified with the character.
     *
     * @param character - the specified character.
     * @return - the child node, specified with the character.
     */
    public TrieNode getChildNode(char character) {
        return this.children[character - 'a'];
    }

    /**
     * Gets the list of input indexes.
     *
     * @return - the list of input indexes.
     */
    public ArrayList<Integer> getInIndex() {
        return this.inIndex;
    }
}