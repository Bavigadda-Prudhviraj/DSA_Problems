package com.prudhvi.dynamic_programming.one_dimensional;
import java.util.HashMap;
/*
Given an integer array nums, return the number of all the arithmetic subsequences of nums.
A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
	For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
	For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
	A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
The test cases are generated so that the answer fits in 32-bit integer.
Example 1:
	Input: nums = [2,4,6,8,10]
	Output: 7
	Explanation: All arithmetic subsequence slices are:
				[2,4,6]
				[4,6,8]
				[6,8,10]
				[2,4,6,8]
				[4,6,8,10]
				[2,4,6,8,10]
				[2,6,10]
 */
public class Arithmetic_Slices_II_Subsequence {

	public static void main(String[] args) {
		int[] nums = {2,4,6,8,10};
		System.out.println(numberOfArithmeticSlices(nums));

	}
/*
	Time Complexity:
			1. The code has two nested loops. The outer loop runs N times, where N is the length of the `nums` array.
			2. The inner loop runs for each element before the current element i. In the worst case, for each i, it can iterate through all previous elements j. Therefore, the inner loop has a worst-case time complexity of O(N^2).
			3. Inside the inner loop, there are constant-time operations and hash map operations. The hash map operations have an average case time complexity of O(1), but in the worst case, the size of the hash map can be proportional to N.
		The overall time complexity is dominated by the nested loops, so the final time complexity is O(N^2).
	Space Complexity:
		1. The code uses an array of hash maps (`diffFreqArr`), where each hash map stores the frequency of differences for a specific index. The size of each hash map is proportional to N.
		2. Therefore, the space complexity is O(N^2) due to the array of hash maps.
		3. Additional space is used for integer variables, but they are constant with respect to the input.
	In summary, the time complexity is O(N^2), and the space complexity is O(N^2). 
	The code efficiently counts the number of arithmetic slices using a dynamic programming approach with a hash map to store differences between pairs of elements.
 */
	public static int numberOfArithmeticSlices(int[] nums) {
        int arithmeticSlices = 0;
        // Use an array of HashMaps to store the differences and their frequencies for each index
        HashMap<Integer, Integer>[] diffFreqArr = new HashMap[nums.length];
        // Initialize each HashMap
        for (int i = 0; i < nums.length; i++) 
            diffFreqArr[i] = new HashMap<>();
        // Iterate through the array
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // Use long to prevent overflow
                long diff = (long) nums[i] - (long) nums[j];
                // Check for potential overflow
                if (diff <= Integer.MIN_VALUE || diff >= Integer.MAX_VALUE)
                    continue;
                // Retrieve frequencies from HashMaps
                int diffJFreq = diffFreqArr[j].getOrDefault((int) diff, 0);
                int diffIFreq = diffFreqArr[i].getOrDefault((int) diff, 0);
                // Update arithmeticSlices count and HashMaps
                arithmeticSlices += diffJFreq;
                diffFreqArr[i].put((int) diff, diffIFreq + diffJFreq + 1);
            }
        }

        return arithmeticSlices;

	}

}
