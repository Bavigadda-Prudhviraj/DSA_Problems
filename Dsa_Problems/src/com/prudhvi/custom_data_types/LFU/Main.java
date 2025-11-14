package com.prudhvi.custom_data_types.LFU;
public class Main {

    public static void main(String[] args) {
        // Create an LFUCache with a capacity of 3
        LFUCache lfuCache = new LFUCache(3);

        // Add some key-value pairs to the cache
        lfuCache.put(1, 1); // Cache: {1=1}
        lfuCache.put(2, 2); // Cache: {1=1, 2=2}
        lfuCache.put(3, 3); // Cache: {1=1, 2=2, 3=3}

        // Get values from the cache
        System.out.println(lfuCache.get(1)); // Returns 1, Cache: {1=1, 2=2, 3=3}
        System.out.println(lfuCache.get(2)); // Returns 2, Cache: {1=1, 2=2, 3=3}

        // Add another key-value pair, this will evict the least frequently used key (3)
        lfuCache.put(4, 4); // Cache: {1=1, 2=2, 4=4}

        // Get values again
        System.out.println(lfuCache.get(3)); // Returns -1 (not found), Cache: {1=1, 2=2, 4=4}
        System.out.println(lfuCache.get(4)); // Returns 4, Cache: {1=1, 2=2, 4=4}

        // Add more keys to test eviction based on frequency
        lfuCache.put(2, 5); // Cache: {1=1, 2=5, 4=4}
        System.out.println(lfuCache.get(2)); // Returns 5, Cache: {1=1, 2=5, 4=4}

        lfuCache.put(5, 5); // Cache: {2=5, 4=4, 5=5}

        // Now, key 1 should be evicted because it has the lowest frequency
        System.out.println(lfuCache.get(1)); // Returns -1 (not found)
        System.out.println(lfuCache.get(4)); // Returns 4
        System.out.println(lfuCache.get(5)); // Returns 5
    }
}
