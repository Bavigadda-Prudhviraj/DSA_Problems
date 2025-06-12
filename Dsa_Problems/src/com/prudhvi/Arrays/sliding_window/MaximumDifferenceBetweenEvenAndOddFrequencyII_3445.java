
package com.prudhvi.Arrays.sliding_window;

import java.util.Arrays;

/**
 * Solves LeetCode Problem 3445: Finds the maximum difference between the counts of two distinct characters
 * in a substring of length at least k, where one character has an odd frequency and the other has an even frequency.
 */
public class MaximumDifferenceBetweenEvenAndOddFrequencyII_3445 {

    public static void main(String[] args) {
        // Test case: String "1122211" with minimum substring length k=3
        System.out.println(maxDifference("1122211", 3));
    }

    /**
     * Computes the maximum difference between counts of two distinct characters in a substring of length
     * at least k, where one character has an odd frequency and the other has an even frequency.
     *
     * @param s The input string containing digits '0' to '4'.
     * @param k The minimum length of the substring.
     * @return The maximum difference, or Integer.MIN_VALUE if no valid substring is found.
     */
    public static int maxDifference(String s, int k) {
        int stringLength = s.length();
        int maxDifference = Integer.MIN_VALUE;

        // Iterate over all possible pairs of distinct characters (char1 and char2)
        for (char char1 = '0'; char1 <= '4'; char1++) {
            for (char char2 = '0'; char2 <= '4'; char2++) {
                if (char1 == char2) continue; // Skip if characters are the same

                // Array to store the minimum difference (char1_count - char2_count) for each parity state
                int[] minDifferenceByState = new int[4];
                Arrays.fill(minDifferenceByState, Integer.MAX_VALUE);

                // Counters for characters in the current window
                int char1Count = 0;
                int char2Count = 0;

                // Counters for characters in the left part of the window (for shrinking)
                int char1CountLeft = 0;
                int char2CountLeft = 0;

                // Sliding window pointers: leftPointer starts at -1, rightPointer at 0
                int leftPointer = -1;
                int rightPointer = 0;

                // Process the string using a sliding window
                while (rightPointer < stringLength) {
                    // Update counts for char1 and char2 at the right end of the window
                    if (s.charAt(rightPointer) == char1) char1Count++;
                    if (s.charAt(rightPointer) == char2) char2Count++;

                    // Shrink the window if it's at least length k and has at least 2 char2 and 1 char1
                    while (rightPointer - leftPointer >= k && 
                           (char2Count - char2CountLeft) >= 2 && 
                           (char1Count - char1CountLeft) >= 1) {
                        // Get the parity state of the left side (before shrinking)
                        int leftState = getParityState(char1CountLeft, char2CountLeft);
                        // Update the minimum difference for this state
                        minDifferenceByState[leftState] = Math.min(
                            minDifferenceByState[leftState], 
                            char1CountLeft - char2CountLeft
                        );

                        // Move left pointer and update left counts
                        leftPointer++;
                        if (s.charAt(leftPointer) == char1) char1CountLeft++;
                        if (s.charAt(leftPointer) == char2) char2CountLeft++;
                    }

                    // Get the parity state of the current window's right side
                    int rightState = getParityState(char1Count, char2Count);
                    // The target state has char1 odd and char2 even (state 2: odd-even)
                    int targetLeftState = rightState ^ 2;
                    int minDifferenceLeft = minDifferenceByState[targetLeftState];

                    // If a valid left state was found, update the maximum difference
                    if (minDifferenceLeft != Integer.MAX_VALUE) {
                        maxDifference = Math.max(
                            maxDifference, 
                            (char1Count - char2Count) - minDifferenceLeft
                        );
                    }

                    rightPointer++;
                }
            }
        }

        return maxDifference;
    }

    /**
     * Determines the parity state based on the counts of two characters.
     * States: 0 (even-even), 1 (even-odd), 2 (odd-even), 3 (odd-odd).
     *
     * @param char1Count Count of the first character.
     * @param char2Count Count of the second character.
     * @return The parity state (0 to 3).
     */
    private static int getParityState(int char1Count, int char2Count) {
        int char1Parity = char1Count % 2; // 0 for even, 1 for odd
        int char2Parity = char2Count % 2; // 0 for even, 1 for odd

        if (char1Parity == 0 && char2Parity == 0) return 0; // Even-Even
        if (char1Parity == 0 && char2Parity == 1) return 1; // Even-Odd
        if (char1Parity == 1 && char2Parity == 0) return 2; // Odd-Even
        return 3; // Odd-Odd
    }
}
