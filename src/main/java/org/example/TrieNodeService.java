package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

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

    public List<Element> getTopKSuggestions(String prefix, TrieNode root, int k) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>((e1, e2) -> {
            int freqCompare = Integer.compare(e1.getFrequency(), e2.getFrequency());
            if (freqCompare == 0) {
                // For a min-heap, the "larger" word ("cat") should be considered smaller to be kicked out
                return e2.getWord().compareTo(e1.getWord());
            }
            return freqCompare;
        });

        TrieNode prefixNode = findPrefixNode(prefix, root);
        if (prefixNode == null) {
            return new ArrayList<>(); // No results
        }

        // --- FIX: Integrate heap logic into the DFS traversal ---
        getWordsAndFillHeap(prefixNode, new StringBuilder(prefix), minHeap, k);

        // --- FIX: Reverse the results to get descending order ---
        LinkedList<Element> topKResults = new LinkedList<>();
        while (!minHeap.isEmpty()) {
            topKResults.addFirst(minHeap.poll());
        }
        return topKResults;
    }

    // Helper to find the starting node
    private TrieNode findPrefixNode(String prefix, TrieNode root) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            // WARNING: This will crash if input is not a-z. Consider adding input validation.
            int index = prefix.charAt(i) - 'a';
            if (current.getChildren()[index] == null) {
                return null;
            }
            current = current.getChildren()[index];
        }
        return current;
    }

    // New DFS method that works with the heap
    private void getWordsAndFillHeap(TrieNode node, StringBuilder word, PriorityQueue<Element> minHeap, int k) {
        if (node.isEndOfWord()) {
            Element currentElement = new Element(word.toString(), node.getFrequency());
            if (minHeap.size() < k) {
                minHeap.add(currentElement);
            } else if (minHeap.peek().getFrequency() < currentElement.getFrequency() ||
                    (minHeap.peek().getFrequency() == currentElement.getFrequency() &&
                            minHeap.peek().getWord().compareTo(currentElement.getWord()) > 0)) {
                minHeap.poll();
                minHeap.add(currentElement);
            }
        }

        for (int i = 0; i < 26; i++) {
            TrieNode childNode = node.getChildren()[i];
            if (childNode != null) {
                word.append((char)(i + 'a'));
                getWordsAndFillHeap(childNode, word, minHeap, k);
                word.deleteCharAt(word.length() - 1); // Backtrack
            }
        }
    }


}
