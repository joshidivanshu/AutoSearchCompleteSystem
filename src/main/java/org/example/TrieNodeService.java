package org.example;

import java.util.ArrayList;
import java.util.List;

public class TrieNodeService {
    public void insert(String key, TrieNode root) {
        TrieNode pCrawl = root;
        int level , length = key.length(), index;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (pCrawl.getChildren()[index] == null) {
                pCrawl.getChildren()[index] = new TrieNode();
            }
            pCrawl = pCrawl.getChildren()[index];
        }
        pCrawl.setEndOfWord(true);
        pCrawl.setFrequency();
    }

    public boolean search(String key, TrieNode root) {
        TrieNode pCrawl = root;
        int level, length = key.length(), index;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (pCrawl.getChildren()[index] == null) {
                return false;
            }
            pCrawl = pCrawl.getChildren()[index];
        }

        return pCrawl.isEndOfWord();
    }

    public boolean prefixSearch(String key, TrieNode root) {
        TrieNode pCrawl = root;
        int level, length = key.length(), index;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (pCrawl.getChildren()[index] == null) {
                return false;
            }
            pCrawl = pCrawl.getChildren()[index];
        }

        return true;
    }

    public List<Element> fetchRelatedKey(String sentence, TrieNode root) {
        List<Element> similarWords = new ArrayList<>();
        TrieNode pCrawl = root;
        int level, length = sentence.length(), index;
        StringBuilder value = new StringBuilder();
        for (level = 0; level < length; level++) {
            index = sentence.charAt(level) - 'a';
            if (pCrawl.getChildren()[index] == null) {
                return null;
            }
            value.append(sentence.charAt(level));
            pCrawl = pCrawl.getChildren()[index];
        }
        getWordsByDfs(pCrawl, new StringBuilder(value), similarWords);
        similarWords.sort(new SortElements());
        return similarWords;
    }

    public void getWordsByDfs(TrieNode pCrawl, StringBuilder word, List<Element> words) {
        if (pCrawl.isEndOfWord()) {
            words.add(new Element(word.toString(), pCrawl.getFrequency()));
            return;
        }
        TrieNode[] arr = pCrawl.getChildren();
        for (int i = 0; i < 26; i++) {
            if (arr[i] != null) {
                word.append((char)(i + 'a'));
                pCrawl = pCrawl.getChildren()[i];
                getWordsByDfs(pCrawl, new StringBuilder(word), words);
            }
        }
    }


}
