package com.prudhvi.Arrays;

public class FindTheMaximumLengthOfValidSubsequenceI_3201 {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 1, 1, 2, 1, 2};
        System.out.println(findMaximumSubsequenceLength(numbers));
    }

    /**
     * Finds the maximum length of a valid subsequence in the given array.
     * A valid subsequence is one of the following:
     * - All even numbers.
     * - All odd numbers.
     * - Alternating even and odd numbers (starting with either even or odd).
     *
     * @param numbers The input array of integers.
     * @return The length of the longest valid subsequence.
     */
    public static int findMaximumSubsequenceLength(int[] numbers) {
        // Get the length of the input array
        int arrayLength = numbers.length;
        
        // Initialize counters for subsequences
        int evenSubsequenceLength = 0; // Length of subsequence with all even numbers
        int oddSubsequenceLength = 0;  // Length of subsequence with all odd numbers
        int alternatingSubsequenceLength = 1; // Length of subsequence with alternating parity
        
        // Track the expected parity for the next number in the alternating subsequence
        // Start with the parity of the first number (0 for even, 1 for odd)
        int expectedParity = numbers[0] % 2;
        
        // Iterate through the array to count valid subsequences
        for (int i = 0; i < arrayLength; i++) {
            // Current number's parity (0 for even, 1 for odd)
            int currentParity = numbers[i] % 2;
            
            // Update even and odd subsequence counts
            if (currentParity == 0) {
                evenSubsequenceLength++; // Increment if number is even
            } else {
                oddSubsequenceLength++;  // Increment if number is odd
            }
            
            // Update alternating subsequence count
            if (i > 0) {
                // Check if current number's parity differs from the expected parity
                if (expectedParity != currentParity) {
                    alternatingSubsequenceLength++; // Increment for valid alternating pattern
                    expectedParity = currentParity; // Update expected parity for next number
                }
            }
        }
        
        // Return the maximum length among all valid subsequences
        return Math.max(alternatingSubsequenceLength, 
                        Math.max(evenSubsequenceLength, oddSubsequenceLength));
    }
}