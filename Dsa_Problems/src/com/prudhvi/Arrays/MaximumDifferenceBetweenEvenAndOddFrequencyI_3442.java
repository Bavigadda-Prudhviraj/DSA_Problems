package com.prudhvi.Arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution to LeetCode #3442: Maximum Difference Between Even and Odd Frequency (Medium).
 * Given a string s, find the maximum difference between the frequency of any character with an odd
 * frequency and any character with an even frequency. If there are no characters with even or odd
 * frequency, the corresponding frequency is considered 0, and the result can be negative.
 * This solution uses a HashMap to count character frequencies and then finds the minimum even
 * frequency and maximum odd frequency to compute the difference.
 *
 * Time Complexity: O(n)
 * - Iterating through the string to build the frequency map takes O(n), where n is the string length.
 * - Iterating through the map entries (at most 26 for lowercase letters or bounded by unique characters)
 *   takes O(1) for practical purposes, as it’s independent of n.
 * - Total: O(n).
 *
 * Space Complexity: O(k)
 * - The HashMap stores at most k entries, where k is the number of unique characters in the string
 *   (bounded by 26 for lowercase letters in typical constraints).
 * - Additional variables (minEvenFrequency, maxOddFrequency) use O(1) space.
 * - Total: O(k), effectively O(1) for a fixed alphabet.
 */
public class MaximumDifferenceBetweenEvenAndOddFrequencyI_3442 {

    /**
     * Main method to test the maxDifference function with sample inputs.
     * Tests various strings to compute the maximum difference between odd and even character frequencies.
     */
    public static void main(String[] args) {
        System.out.println(maxDifference("tzt")); // Expected output: -1
        System.out.println(maxDifference("abcabcab")); // Expected output: 1
        System.out.println(maxDifference("aaaaabbc")); // Expected output: 3
        System.out.println(maxDifference("mmsmsym")); // Expected output: -1
    }

    /**
     * Computes the maximum difference between the frequency of any character with an odd frequency
     * and any character with an even frequency in the input string. If no characters have even or
     * odd frequency, their respective frequencies are set to 0.
     *
     * @param s The input string containing lowercase letters.
     * @return The maximum difference (odd frequency - even frequency), or 0 if no valid difference exists.
     */
    public static int maxDifference(String s) {
        // Initialize a HashMap to store the frequency of each character in the string
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Iterate through each character in the string to count its frequency
        for (Character currentChar : s.toCharArray()) {
            // Update the frequency of the current character, defaulting to 0 if not present
            frequencyMap.put(currentChar, frequencyMap.getOrDefault(currentChar, 0) + 1);
        }

        // Initialize variables to track the minimum even frequency and maximum odd frequency
        // Use Integer.MAX_VALUE for minEvenFrequency to handle cases with no even frequencies
        // Use Integer.MIN_VALUE for maxOddFrequency to handle cases with no odd frequencies
        int minEvenFrequency = Integer.MAX_VALUE;
        int maxOddFrequency = Integer.MIN_VALUE;

        // Iterate through the frequency map to categorize frequencies as even or odd
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            // Get the frequency of the current character
            int currentFrequency = entry.getValue();

            // Check if the frequency is even
            if (currentFrequency % 2 == 0) {
                // Update minEvenFrequency to the smallest even frequency encountered
                // This ensures we pick the smallest even frequency for the maximum difference
                minEvenFrequency = Math.min(minEvenFrequency, currentFrequency);
            } else {
                // Update maxOddFrequency to the largest odd frequency encountered
                // This ensures we pick the largest odd frequency for the maximum difference
                maxOddFrequency = Math.max(maxOddFrequency, currentFrequency);
            }
        }

        // Handle edge cases: if no even or odd frequencies exist, set to 0
        // If no even frequencies were found, minEvenFrequency remains Integer.MAX_VALUE
        minEvenFrequency = minEvenFrequency == Integer.MAX_VALUE ? 0 : minEvenFrequency;
        // If no odd frequencies were found, maxOddFrequency remains Integer.MIN_VALUE
        maxOddFrequency = maxOddFrequency == Integer.MIN_VALUE ? 0 : maxOddFrequency;

        // Return the maximum difference between the largest odd frequency and smallest even frequency
        // This can be negative if maxOddFrequency < minEvenFrequency
        return maxOddFrequency - minEvenFrequency;
    }
}