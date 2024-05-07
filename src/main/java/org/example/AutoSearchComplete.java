package org.example;

import java.util.List;

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
        List<Element> result = trieNodeService.fetchRelatedKey(sentence, root);
        return result.stream().limit(k).toList();
    }


}
