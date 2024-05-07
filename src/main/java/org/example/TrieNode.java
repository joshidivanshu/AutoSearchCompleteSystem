package org.example;

import java.io.Serializable;

public class TrieNode implements Serializable {
    private TrieNode[] children;
    private boolean isEndOfWord;
    private int frequency;

    TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
        frequency = 0;
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public void setChildren(TrieNode[] children) {
        this.children = children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency() {
        frequency++;
    }
}
