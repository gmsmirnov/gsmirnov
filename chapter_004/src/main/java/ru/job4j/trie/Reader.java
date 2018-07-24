package ru.job4j.trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads the specified text file and puts all words with input indexes to prefix tree.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 24/07/2018
 */
public class Reader {
    /**
     * The prefix tree.
     */
    Trie trie = new Trie();

    /**
     * Creates prefix tree with input indexes.
     *
     * @param file - the specified file.
     */
    public void createTree(String file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(file)));
            ArrayList<Character> word = new ArrayList<Character>();
            int ch;
            int counter = 0;
            int wordBeginning;
            while ((ch = br.read()) != -1) {
                counter++;
                if (Character.isLetter((char) ch)) {
                    word.add((char) ch);
                } else {
                    wordBeginning = counter - word.size();
                    if (word.size() > 0) {
                        trie.addWord(word.toString().replaceAll("\\W", ""), wordBeginning);
                    }
                    word.clear();
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getStackTrace());
        }
    }
}