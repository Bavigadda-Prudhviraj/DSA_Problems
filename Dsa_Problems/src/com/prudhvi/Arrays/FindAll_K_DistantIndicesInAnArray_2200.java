package com.prudhvi.Arrays;

import java.util.ArrayList;
import java.util.List;

public class FindAll_K_DistantIndicesInAnArray_2200 {

	public static void main(String[] args) {
		int[] nums = {3, 4, 9, 1, 3, 9, 5};
		int key = 9;
		int k = 1;
		System.out.println(findKDistantIndices(nums, key, k));
	}

	/**
	 * Finds all indices i such that there exists at least one index j where:
	 * - nums[j] == key
	 * - abs(i - j) <= k
	 *
	 * Time Complexity: O(n)
	 * - Each index is added to the result at most once due to `rightBound` optimization.
	 *
	 * Space Complexity: O(n)
	 * - In the worst case, the result list stores all indices.
	 */
	public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
	    List<Integer> result = new ArrayList<>();
	    int rightBound = 0; // keeps track of the rightmost index already added to avoid duplicates
	    int length = nums.length;

	    for (int currentIndex = 0; currentIndex < length; ++currentIndex) {
	        if (nums[currentIndex] == key) {
	            // Determine the valid starting index for this key match,
	            // avoiding indices already added in previous iterations
	            int start = Math.max(rightBound, currentIndex - k);

	            // Update rightBound to the farthest we can go for this key occurrence
	            // and prepare it for the next iteration (exclusive bound)
	            rightBound = Math.min(length - 1, currentIndex + k) + 1;

	            // Add all valid indices in the current [start, rightBound) window
	            for (int i = start; i < rightBound; ++i) {
	                result.add(i);
	            }
	        }
	    }

	    return result;
	}
}
