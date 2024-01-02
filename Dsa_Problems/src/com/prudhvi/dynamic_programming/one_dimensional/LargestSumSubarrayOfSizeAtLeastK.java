package com.prudhvi.dynamic_programming.one_dimensional;
/*
Given an array a of length n and a number k, find the largest sum of the subarray containing at least k numbers. It is guaranteed that the size of array is at-least k.
Example 1:
	Input : 
	n = 4
	a[] = {-4, -2, 1, -3};
	k = 2
	Output : -1
	Explanation :
	The sub-array of length at-least 2
	that produces greatest sum is {-2, 1}
Example 2:
	Input :
	n = 6 
	a[] = {1, 1, 1, 1, 1, 1}
	k = 2
	Output : 6
	Explanation :
	The sub-array of length at-least 2
	that produces greatest sum is {1, 1, 1, 1, 1, 1}
 */
public class LargestSumSubarrayOfSizeAtLeastK {

	public static void main(String[] args) {
		long[] a = {-4, -2, 1, -3};
		int n = 4;
		int k = 2;
		long maxSum = maxSumWithK(a, n, k);
		System.out.println(maxSum);

	}
	/*
	This method implement an algorithm to find the maximum subarray sum of size k in an array. 
	The code uses dynamic programming to efficiently compute the cumulative sum array and then iterates through the array to find the maximum subarray sum of size k
	
	Time Complexity:
		The time complexity of this algorithm is O(n) because it iterates through the array once.
		The backward iteration for prefix sums and the sliding window technique both contribute to the linear time complexity.
	Space Complexity:
		The space complexity is O(n) due to the dp array of size n+1.
	 */
	public static long maxSumWithK(long a[], long n, long k) {
	    // Create an array to store cumulative sums in reverse order
	    long[] dp = new long[(int) n + 1];

	    // Calculate cumulative sums in reverse order
	    for (int i = (int) n - 1; i >= 0; i--) {
	        dp[i] = a[i] + dp[i + 1];
	        dp[i] = Math.max(dp[i], 0L); // Ensure that negative values don't contribute to the sum
	    }

	    // Initialize the current sum with the sum of the first 'k' elements
	    long currentSum = 0;
	    for (int i = 0; i < k; i++) {
	        currentSum += a[i];
	    }

	    // Initialize the maximum sum with a very small value
	    long maxSum = Long.MIN_VALUE;

	    // Iterate over the array to find the maximum sum of subarrays of size 'k'
	    for (int i = (int) k; i < n; i++) {
	        // Calculate the sum of the current subarray using cumulative sums
	        long sum = dp[i] + currentSum;
	        // Update the maximum sum
	        maxSum = Math.max(sum, maxSum);
	        // Update the current sum by adding the next element and subtracting the first element in the subarray
	        currentSum += a[i];
	        currentSum -= a[i - (int) k];
	    }

	    // Return the maximum of the last calculated currentSum and the maxSum
	    return Math.max(currentSum, maxSum);
	}


}
