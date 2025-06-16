package org.example;

import java.util.List;
import java.util.PriorityQueue;

public class AutoSearchComplete {
    private final TrieNode root;
    private final TrieNodeService trieNodeService;
    private final int k;

    public AutoSearchComplete(int k) {
        root = new TrieNode();
        trieNodeService = new TrieNodeService();
        this.k = k;
    }

    public void insert(String key) {
        trieNodeService.insert(key, root);
    }

    public List<Element> fetchRelatedKey(String sentence) {
        return trieNodeService.getTopKSuggestions(sentence, root, k);
    }


}
