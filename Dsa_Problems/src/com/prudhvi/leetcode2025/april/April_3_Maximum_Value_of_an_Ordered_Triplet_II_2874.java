package com.prudhvi.leetcode2025.april;

public class April_3_Maximum_Value_of_an_Ordered_Triplet_II_2874 {

	public static void main(String[] args) {
        int[] nums = {10, 13, 6, 2};
        System.out.println(findMaximumTripletValueOptimized(nums));

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
