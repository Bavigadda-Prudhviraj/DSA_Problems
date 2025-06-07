package com.prudhvi.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LexicographicallyMinimumStringAfterRemovingStars_3170 is a class that solves the problem of
 * removing stars from a string to produce a lexicographically minimum string. The string contains
 * lowercase letters and '*' characters. For each '*', the leftmost character that is lexicographically
 * smallest among all characters to its left (not yet removed) is removed, and the '*' itself is removed.
 * Two solutions are provided: one using a PriorityQueue (min-heap) and an optimized one using a Map.
 */
public class LexicographicallyMinimumStringAfterRemovingStars_3170 {

    /**
     * Main method to test the two implementations of the clearStars functionality.
     * It tests both the PriorityQueue-based and optimized Map-based solutions with sample inputs.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        String s = "aaba*";  // Test case 1: Expected output "aab"
        String s1 = "d*";    // Test case 2: Expected output ""
        System.out.println("Testing clearStars (PriorityQueue): " + clearStars(s));
        System.out.println("Testing clearStarsOptimized (Map): " + clearStarsOptimized(s));
        // Uncomment to test additional cases
        // System.out.println(clearStars(s1));
    }

    /**
     * Optimized solution to remove stars and produce a lexicographically minimum string.
     * Uses a Map to store characters and their indices, removing the smallest lexicographical
     * character (latest occurrence) for each star encountered.
     *
     * @param input The input string containing lowercase letters and '*' characters
     * @return The lexicographically minimum string after removing stars
     */
    public static String clearStarsOptimized(String input) {
        int length = input.length();
        // Convert the input string to a char array for in-place modifications
        char[] charArray = input.toCharArray();

        // Map to store each character and a list of its indices in the string
        Map<Character, List<Integer>> charIndexMap = new HashMap<>();

        // Iterate through the string to process each character
        for (int i = 0; i < length; i++) {
            char currentChar = input.charAt(i);

            if (currentChar == '*') {
                // If a star is encountered, find the smallest lexicographical character to remove
                if (!charIndexMap.isEmpty()) {
                    // Iterate through characters 'a' to 'z' to find the smallest one
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (charIndexMap.containsKey(ch) && !charIndexMap.get(ch).isEmpty()) {
                            List<Integer> indices = charIndexMap.get(ch);
                            // Remove the latest occurrence of the smallest character by marking it as '*'
                            int lastIndex = indices.remove(indices.size() - 1);
                            charArray[lastIndex] = '*';
                            break; // Stop after removing one character
                        }
                    }
                }
            } else {
                // If the character is not a star, add its index to the map
                charIndexMap.computeIfAbsent(currentChar, key -> new ArrayList<>()).add(i);
            }
        }

        // Build the final string by skipping all '*' characters
        StringBuilder resultBuilder = new StringBuilder();
        for (char c : charArray) {
            if (c != '*') {
                resultBuilder.append(c);
            }
        }

        return resultBuilder.toString();
    }

    /**
     * Solution to remove stars and produce a lexicographically minimum string using a PriorityQueue.
     * For each star, removes the leftmost occurrence of the lexicographically smallest character
     * among the characters not yet removed.
     *
     * @param s The input string containing lowercase letters and '*' characters
     * @return The lexicographically minimum string after removing stars
     */
    public static String clearStars(String s) {
        int len = s.length();
        // Convert the input string to a char array for in-place modifications
        char[] sArr = s.toCharArray();

        // Min-heap to store pairs of (character, index)
        // Primary sorting: by character in ascending order (lexicographically)
        // Secondary sorting: by index in descending order (to remove leftmost occurrence first)
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(
            (p1, p2) -> {
                if (p1.ch == p2.ch) {
                    return Integer.compare(p2.index, p1.index); // Higher index first
                }
                return Character.compare(p1.ch, p2.ch); // Smaller character first
            }
        );

        // Iterate through the string to process each character
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);

            if (currentChar == '*') {
                // If a star is encountered, remove the smallest lexicographical character
                if (!minHeap.isEmpty()) {
                    // Get the leftmost occurrence of the smallest character
                    Pair leftMostMinCharPair = minHeap.poll();
                    // Mark the character's position as '*' to indicate removal
                    sArr[leftMostMinCharPair.index] = '*';
                }
            } else {
                // If the character is not a star, add it to the min-heap
                minHeap.add(new Pair(currentChar, i));
            }
        }

        // Build the final string by skipping all '*' characters
        StringBuilder result = new StringBuilder();
        for (char c : sArr) {
            if (c != '*') {
                result.append(c);
            }
        }

        return result.toString();
    }

    /**
     * Pair is a helper class to store a character and its index in the string.
     * Used by the PriorityQueue in the clearStars method to track characters and their positions.
     */
    static class Pair {
        char ch;    // The character
        int index;  // The index of the character in the string

        /**
         * Constructor to initialize the Pair with a character and its index.
         *
         * @param ch The character
         * @param index The index of the character in the string
         */
        public Pair(char ch, int index) {
            this.ch = ch;
            this.index = index;
        }

        /**
         * Returns a string representation of the Pair for debugging purposes.
         *
         * @return A string in the format "(char, index)"
         */
        @Override
        public String toString() {
            return "(" + ch + ", " + index + ")";
        }
    }
}