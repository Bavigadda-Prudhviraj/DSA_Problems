package com.prudhvi.Arrays;

/**
 * This class calculates the maximum difference between two elements in an array
 * such that the larger element appears after the smaller element (i.e., nums[j] - nums[i]
 * where j > i and nums[j] > nums[i]). If no such pair exists, it returns -1.
 */
public class MaximumDifferenceBetweenIncreasingElements_2016 {

    /**
     * Main method to test the maximumDifference functionality with a sample input.
     * 
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[] nums = {1, 5, 2, 10};
        System.out.println(maximumDifference(nums)); // Output: 9
    }

    /**
     * Calculates the maximum difference between two elements in the array where the
     * larger element appears after the smaller element.
     * 
     * Logic:
     * - Initialize smallestSoFar as the first element and maximumDifference as -1.
     * - Iterate through the array starting from the second element:
     *   - If the current element is greater than smallestSoFar, compute the difference
     *     and update maximumDifference if the difference is larger.
     *   - If the current element is less than or equal to smallestSoFar, update
     *     smallestSoFar to the current element to potentially find a larger difference later.
     * - Return maximumDifference. If no valid pair is found, it remains -1.
     * 
     * Example: For nums = [1, 5, 2, 10]
     * - i=1: smallestSoFar=1, current=5, diff=5-1=4, maxDiff=4
     * - i=2: smallestSoFar=1, current=2, diff=2-1=1, maxDiff=4, update smallestSoFar=2
     * - i=3: smallestSoFar=2, current=10, diff=10-2=8, maxDiff=8
     * - Return 8 (since 10-2 is the maximum difference)
     * 
     * Time Complexity: O(n), where n is the length of the array, as it requires a single pass.
     * Space Complexity: O(1), as it uses only a constant amount of extra space.
     * 
     * @param nums Input array of integers
     * @return Maximum difference between increasing elements, or -1 if none exists
     */
    public static int maximumDifference(int[] nums) {
        // Initialize smallest element seen so far and maximum difference
        int smallestSoFar = nums[0];
        int maximumDifference = -1;

        // Iterate through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];

            // If current element is greater than smallestSoFar, compute difference
            if (current > smallestSoFar) {
                int difference = current - smallestSoFar;
                // Update maximumDifference if current difference is larger
                maximumDifference = Math.max(maximumDifference, difference);
            } else {
                // Update smallestSoFar to current element to find larger differences later
                smallestSoFar = current;
            }
        }

        // Return the maximum difference, or -1 if no valid pair was found
        return maximumDifference;
    }
}