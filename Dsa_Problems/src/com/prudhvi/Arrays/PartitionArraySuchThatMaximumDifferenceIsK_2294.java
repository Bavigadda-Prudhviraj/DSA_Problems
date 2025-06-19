package com.prudhvi.Arrays;

import java.util.Arrays;

public class PartitionArraySuchThatMaximumDifferenceIsK_2294 {
    public static void main(String[] args) {
        // Initialize test array and maximum allowed difference
        int[] nums = {2, 2, 4, 5};
        int k = 0;
        // Print the number of partitions needed
        System.out.println(partitionArray(nums, k)); // Output: 4
    }

    // Method to partition array such that max difference in each partition is at most k
    public static int partitionArray(int[] nums, int maxDifference) {
        // Sort the array to group numbers that are close together
        // This helps in forming partitions with minimal difference
        Arrays.sort(nums);
        // Print sorted array for debugging
        System.out.println(Arrays.toString(nums));

        // Initialize partition counter (at least one partition is needed)
        int partitionCount = 1;
        // Set the start of the current partition as the first element
        int partitionStart = nums[0];

        // Iterate through the sorted array starting from second element
        for (int i = 1; i < nums.length; i++) {
            // Calculate difference between current element and partition start
            int currentDifference = nums[i] - partitionStart;

            // If difference exceeds max allowed difference (k)
            if (currentDifference > maxDifference) {
                // Increment partition count as we need a new partition
                partitionCount++;
                // Update partition start to current element for new partition
                partitionStart = nums[i];
            }
            // If difference is within k, continue with current partition
        }

        // Return total number of partitions needed
        return partitionCount;
    }
}