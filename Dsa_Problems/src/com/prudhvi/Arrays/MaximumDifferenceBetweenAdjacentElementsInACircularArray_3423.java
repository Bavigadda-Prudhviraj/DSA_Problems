package com.prudhvi.Arrays;

/**
 * This class finds the maximum difference between adjacent elements in a circular array.
 * In a circular array, the last element is considered adjacent to the first element.
 */
public class MaximumDifferenceBetweenAdjacentElementsInACircularArray_3423 {

    public static void main(String[] args) {
        // Test case with sample input array
        int[] nums = {3, 2, -5, -3}; // Expected output: 7
        // Print the result of maxAdjacentDistance method
        System.out.println(maxAdjacentDistance(nums));
    }

    /**
     * Calculates the maximum absolute difference between adjacent elements in a circular array.
     * 
     * @param nums The input array of integers
     * @return The maximum absolute difference between any two adjacent elements
     * 
     * Time Complexity: O(n), where n is the length of the input array.
     *                  The method iterates through the array once to compare adjacent elements.
     * Space Complexity: O(1), as only a constant amount of extra space is used regardless of input size.
     */
    public static int maxAdjacentDistance(int[] nums) {
        // Get the length of the input array
        int len = nums.length;
        
        // Initialize maxDifference to the smallest possible integer
        int maxDifference = Integer.MIN_VALUE;
        
        // Edge case: if array has less than 2 elements, return 0
        // (No adjacent elements exist to compare)
        if (len < 2) {
            return 0;
        }
        
        // Iterate through the array to find max difference between consecutive elements
        for (int i = 0; i < len - 1; i++) {
            // Calculate absolute difference between current and next element
            // Update maxDifference if current difference is larger
            maxDifference = Math.max(maxDifference, Math.abs(nums[i] - nums[i + 1]));
        }
        
        // For circular array, compare first and last elements
        // Update maxDifference if this difference is larger
        maxDifference = Math.max(maxDifference, Math.abs(nums[0] - nums[len - 1]));
        
        // Return the maximum difference found
        return maxDifference;
    }
}