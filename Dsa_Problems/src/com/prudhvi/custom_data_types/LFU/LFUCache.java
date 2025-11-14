package com.prudhvi.custom_data_types.LFU;

import java.util.*;

public class LFUCache {

    // Maps a key to its (value, frequency) pair
    private Map<Integer, Integer> valueMap;
    
    // Maps a key to its frequency
    private Map<Integer, Integer> frequencyMap;
    
    // Maps frequency to a list of keys with that frequency
    private Map<Integer, LinkedHashSet<Integer>> freqListMap;
    
    // Keeps track of the minimum frequency in the cache
    private int minFreq;
    
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.valueMap = new HashMap<>();
        this.frequencyMap = new HashMap<>();
        this.freqListMap = new HashMap<>();
        this.minFreq = 0;
    }

    public int get(int key) {
        if (!valueMap.containsKey(key)) {
            return -1;
        }
        
        // Update the frequency of the key
        int freq = frequencyMap.get(key);
        frequencyMap.put(key, freq + 1);
        
        // Move the key to the next higher frequency list
        freqListMap.get(freq).remove(key);
        if (freqListMap.get(freq).isEmpty()) {
            if (freq == minFreq) {
                minFreq++;
            }
            freqListMap.remove(freq);
        }
        
        // Add the key to the next frequency list
        freqListMap.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>()).add(key);
        
        return valueMap.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) return;

        // If the key already exists, update its value and frequency
        if (valueMap.containsKey(key)) {
            valueMap.put(key, value);
            get(key);  // We will automatically update the frequency after calling get()
            return;
        }

        // If cache is at capacity, evict the least frequently used key
        if (valueMap.size() >= capacity) {
            evict();
        }

        // Add new key-value pair with frequency 1
        valueMap.put(key, value);
        frequencyMap.put(key, 1);
        minFreq = 1;
        
        // Add the key to the frequency 1 list
        freqListMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
    }

    private void evict() {
        // Find the list of keys with the minimum frequency
        LinkedHashSet<Integer> leastFreqList = freqListMap.get(minFreq);
        
        // Remove the least recently used key (first element in the list)
        Integer keyToEvict = leastFreqList.iterator().next();
        leastFreqList.remove(keyToEvict);
        
        if (leastFreqList.isEmpty()) {
            freqListMap.remove(minFreq);
        }
        
        // Remove the evicted key from valueMap and frequencyMap
        valueMap.remove(keyToEvict);
        frequencyMap.remove(keyToEvict);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
