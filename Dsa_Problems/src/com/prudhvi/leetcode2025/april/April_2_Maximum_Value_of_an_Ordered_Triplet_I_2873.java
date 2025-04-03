package com.prudhvi.leetcode2025.april;

import java.util.Arrays;

public class April_2_Maximum_Value_of_an_Ordered_Triplet_I_2873 {

    public static void main(String[] args) {
        int[] nums = {10, 13, 6, 2};
        System.out.println(findMaximumTripletValue(nums));
        System.out.println(findMaximumTripletValueOptimized(nums));
    }

    /**
     * Finds the maximum value of an ordered triplet (i, j, k) such that:
     * - i < j < k
     * - The value is calculated as (nums[i] - nums[j]) * nums[k]
     *
     * Core Logic:
     * 1. Create two arrays:
     *    - `leftMaxValues[i]` stores the maximum value found to the left of index `i`.
     *    - `rightMaxValues[i]` stores the maximum value found to the right of index `i`.
     * 2. Populate `leftMaxValues` by iterating from left to right.
     * 3. Populate `rightMaxValues` by iterating from right to left.
     * 4. Iterate through the array and compute the maximum triplet value by checking:
     *    (leftMaxValues[i] - nums[i]) * rightMaxValues[i].
     * 5. Return the maximum triplet value found.
     *
     * Why do we use left and right max arrays?
     * - Instead of scanning the left and right of every `j` separately in O(N) time for each `j` (leading to O(N²) or worse),
     *   we **precompute** the max values using a **single pass** (O(N)) and store them in **leftMaxValues** and **rightMaxValues**.
     * - This avoids nested loops and improves efficiency.
     *
     * @param nums The input array of integers
     * @return The maximum triplet value
     *
     * Time Complexity: O(N) - Since we iterate through the array three times.
     * Space Complexity: O(N) - Additional arrays leftMaxValues and rightMaxValues are used.
     */
    public static long findMaximumTripletValue(int[] nums) {
        int length = nums.length;
        int[] leftMaxValues = new int[length]; // Stores max value to the left of each index
        int[] rightMaxValues = new int[length]; // Stores max value to the right of each index

        // Step 1: Populate left max values
        for (int i = 1; i < length; i++) {
            leftMaxValues[i] = Math.max(leftMaxValues[i - 1], nums[i - 1]);
        }

        // Step 2: Populate right max values
        for (int i = length - 2; i >= 0; i--) {
            rightMaxValues[i] = Math.max(rightMaxValues[i + 1], nums[i + 1]);
        }

        long maxTripletValue = 0;
        // Step 3: Iterate through the array to compute the maximum triplet value
        for (int i = 0; i < length; i++) {
            maxTripletValue = Math.max(maxTripletValue, ((long) (leftMaxValues[i] - nums[i]) * rightMaxValues[i]));
        }
        return maxTripletValue;
    }

    /**
     * Optimized approach to find the maximum value of an ordered triplet.
     * Uses a single pass and avoids extra space usage.
     *
     * Core Logic:
     * 1. Maintain three variables:
     *    - `maxTripletValue`: Stores the maximum triplet value found so far.
     *    - `maxDifference`: Keeps track of the largest (nums[i] - nums[j]) encountered.
     *    - `maxElementSoFar`: Stores the maximum element seen so far.
     * 2. Iterate through the array and update these values dynamically:
     *    - Update `maxTripletValue` as maxDifference * nums[i].
     *    - Update `maxDifference` as max(maxDifference, maxElementSoFar - nums[i]).
     *    - Update `maxElementSoFar` as max(maxElementSoFar, nums[i]).
     * 3. Return the maximum triplet value found.
     *
     * @param nums The input array of integers
     * @return The maximum triplet value
     *
     * Time Complexity: O(N) - Single pass through the array.
     * Space Complexity: O(1) - No additional arrays used.
     */
    public static long findMaximumTripletValueOptimized(int[] nums) {
        int length = nums.length;
        long maxTripletValue = 0;
        long maxDifference = 0; // Tracks the maximum (nums[i] - nums[j]) encountered so far
        long maxElementSoFar = 0; // Tracks the maximum element encountered so far

        // Step 1: Iterate through the array while maintaining running maximums
        for (int i = 0; i < length; i++) {
            // Step 2: Calculate the triplet value using the max difference seen so far
            maxTripletValue = Math.max(maxTripletValue, maxDifference * nums[i]);
            // Step 3: Update maxDifference for future calculations
            maxDifference = Math.max(maxDifference, maxElementSoFar - nums[i]);
            // Step 4: Update maxElementSoFar for future iterations
            maxElementSoFar = Math.max(maxElementSoFar, nums[i]);
        }
        return maxTripletValue;
    }
}
