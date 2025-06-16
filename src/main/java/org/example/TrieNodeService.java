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

    public List<Element> fetchRelatedKey(String prefix, TrieNode root) {
        List<Element> similarWords = new ArrayList<>();
        TrieNode pCrawl = root;
        int level, length = prefix.length(), index;
        StringBuilder value = new StringBuilder();
        for (level = 0; level < length; level++) {
            index = prefix.charAt(level) - 'a';
            if (pCrawl.getChildren()[index] == null) {
                return null;
            }
            value.append(prefix.charAt(level));
            pCrawl = pCrawl.getChildren()[index];
        }
        getWordsByDfs(pCrawl, new StringBuilder(value), similarWords);
        similarWords.sort(new SortElements());
        return similarWords;
    }

    public void getWordsByDfs(TrieNode pCrawl, StringBuilder word, List<Element> words) {
        if (pCrawl.isEndOfWord()) {
            words.add(new Element(word.toString(), pCrawl.getFrequency()));
        }
        TrieNode[] arr = pCrawl.getChildren();
        for (int i = 0; i < 26; i++) {
            TrieNode childNode = arr[i];
            if (childNode != null) {
                word.append((char)(i + 'a'));

                // recur
                getWordsByDfs(childNode, word, words);

                word.deleteCharAt(word.length() - 1);
            }
        }
    }


}
