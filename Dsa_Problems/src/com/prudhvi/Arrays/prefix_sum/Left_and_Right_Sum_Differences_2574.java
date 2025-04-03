package com.prudhvi.Arrays.prefix_sum;

import java.util.Arrays;

public class Left_and_Right_Sum_Differences_2574 {

    public static void main(String[] args) {
        int[] nums = {10, 4, 8, 3};
        System.out.println(Arrays.toString(leftRightDifference(nums)));
        System.out.println(Arrays.toString(leftRightDifferenceOptimized(nums)));
    }

    /**
     * Brute Force Approach: Computes left and right sums separately
     * 
     * Approach:
     * 1. Create two arrays:
     *    - `leftSum[i]`: Stores the sum of elements to the left of index `i`.
     *    - `rightSum[i]`: Stores the sum of elements to the right of index `i`.
     * 2. Fill `leftSum[]` by iterating from **left to right**.
     * 3. Fill `rightSum[]` by iterating from **right to left**.
     * 4. Compute the absolute difference `|leftSum[i] - rightSum[i]|` for each index.
     * 5. Return the resulting array.
     *
     * Time Complexity: O(N) - Two passes for left and right sums, one pass for computing the result.
     * Space Complexity: O(N) - Additional arrays `leftSum[]` and `rightSum[]` are used.
     *
     * @param nums The input array
     * @return The difference array
     */
    public static int[] leftRightDifference(int[] nums) {
        int len = nums.length;
        int[] leftSum = new int[len];
        int[] rightSum = new int[len];
        int[] answer = new int[len];

        // Step 1: Compute leftSum[]
        for (int i = 1; i < len; i++) {
            leftSum[i] = nums[i - 1] + leftSum[i - 1];
        }

        // Step 2: Compute rightSum[]
        for (int i = len - 2; i >= 0; i--) {
            rightSum[i] = nums[i + 1] + rightSum[i + 1];
        }

        // Step 3: Compute absolute differences
        for (int i = 0; i < len; i++) {
            answer[i] = Math.abs(leftSum[i] - rightSum[i]);
        }

        return answer;
    }

    /**
     * Optimized Approach: Uses a **single pass** to calculate left and right sums dynamically.
     *
     * Optimizations:
     * - Eliminates `leftSum[]` and `rightSum[]` arrays to save space.
     * - Uses a **running sum** to keep track of `leftSumSoFar` while iterating.
     * - Computes `rightSum` dynamically using `totalSum - leftSumSoFar - nums[i]`.
     *
     * Core Logic:
     * 1. Compute the `totalSum` of the array in O(N).
     * 2. Iterate through the array while updating `leftSumSoFar` dynamically.
     * 3. Compute `rightSum` for the current index using `totalSum - leftSumSoFar - nums[i]`.
     * 4. Compute `|leftSumSoFar - rightSum|` and store it in `diffArr`.
     * 5. Update `leftSumSoFar` after processing each index.
     *
     * Time Complexity: O(N) - One pass for total sum, one pass for computing differences.
     * Space Complexity: O(1) - Uses only a single result array.
     *
     * @param nums The input array
     * @return The optimized difference array
     */
    public static int[] leftRightDifferenceOptimized(int[] nums) {
        int len = nums.length;
        int leftSumSoFar = 0;
        int totalSum = 0;
        int[] diffArr = new int[len];

        // Step 1: Compute total sum of all elements
        for (int num : nums) {
            totalSum += num;
        }

        // Step 2: Compute differences dynamically
        for (int i = 0; i < len; i++) {
            int rightSum = totalSum - leftSumSoFar - nums[i];
            diffArr[i] = Math.abs(leftSumSoFar - rightSum);
            leftSumSoFar += nums[i]; // Update left sum for the next iteration
        }

        return diffArr;
    }
}
