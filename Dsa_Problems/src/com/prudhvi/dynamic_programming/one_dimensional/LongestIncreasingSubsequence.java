package com.prudhvi.dynamic_programming.one_dimensional;

import java.util.Arrays;

/*
	Problem Description
		Find the longest increasing subsequence of a given array of integers, A.
		In other words, find a subsequence of array in which the subsequence's elements are in strictly increasing order, and in which the subsequence is as long as possible.
		In this case, return the length of the longest increasing subsequence.
	Problem Constraints
		1 <= length(A) <= 2500
		0 <= A[i] <= 2500
 */
public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		int[] arr= {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		int len=lIs(arr);
		System.out.println(len);
		int lenMemo=lisMemo(arr);
		System.out.println(lenMemo);
	}
	/*
	 Time Complexity: 
	 		The time complexity of this code is O(n^2) because there are nested loops. For each element at index i, it checks the elements before it (from j=i-1 to j=0).
	Space Complexity: 
			The space complexity is O(n) due to the dp array used to store the length of the LIS ending at each index.
	 */
	private static int lIs(int[] arr) {
		int n=arr.length;
		int[] dp=new int[n];
		dp[0]=1;//It initializes the first element of dp to 1 because the LIS of a single element is always 1.
		int max=1;//a variable max to keep track of the maximum LIS length found so far.
		//It iterates over the elements of the input array arr from the second element (index 1) to the last element (index n-1).
		for(int i=1;i<n;i++) {
			//For each element at index i, it initializes a variable cnt to 0. cnt will be used to store the length of the LIS ending at index i.
			int cnt=0;
			//It then iterates over the elements before index i (from j=i-1 to j=0) to check if there are any elements smaller than arr[i] (i.e., elements that can be included in the increasing subsequence ending at i).
			for(int j=i-1;j>=0;j--) {
				//If arr[j] is less than arr[i], it updates cnt to be the maximum of its current value and dp[j]. 
				//This means that if there is a smaller element at index j that can extend the LIS ending at i,
				if(arr[j]<arr[i]) {
					cnt=Math.max(cnt, dp[j]);
				}
			}
			//it updates dp[i] to be cnt + 1, where cnt represents the length of the LIS ending at index i.
			dp[i]=cnt+1;
			//updates the max variable to keep track of the maximum LIS length found so far.
			max=Math.max(dp[i], max);
		}
		return max;
	}
	public static int lisMemo(int[] arr) {
	    int n = arr.length;
	    //It initializes an integer array dp of the same length as the input array arr and fills it with -1. This array dp will be used for memoization to store the length of the LIS ending at each index.
	    int[] dp = new int[n];
	    Arrays.fill(dp, -1);
	    //It initializes a variable max to 1. This variable will be used to keep track of the maximum LIS length found so far.
	    int max = 1;
	    //The code then iterates over each element in the input array arr from left to right (from index 0 to n-1).
	    for (int i = 0; i < n; i++) 
	    	//For each element at the current index i, it calls the memoizedLIS function with arguments arr, i, and dp.
	        max = Math.max(max, memoizedLIS(arr, i, dp));
	    //The memoizedLIS function is a recursive function that calculates the length of the LIS ending at the current index currentIndex. It uses memoization to avoid redundant calculations.
	    return max;
	}

	private static int memoizedLIS(int[] arr, int currentIndex, int[] dp) {
		//If currentIndex is 0, it means that there's only one element in the subsequence (the current element), so it returns 1.
	    if (currentIndex == 0)
	        return 1;
	    //If dp[currentIndex] is not -1, it means that the length of the LIS ending at this index has already been calculated, so it returns dp[currentIndex].
	    if (dp[currentIndex] != -1) 
	        return dp[currentIndex];
	    //Otherwise, it initializes maxLength to 1 (representing the current element itself) and iterates over elements before the current index (from i=0 to i<currentIndex).
	    int maxLength = 1;
	    //For each element at index i, if the current element at arr[currentIndex] is greater than the element at arr[i], it calculates the length of the LIS ending at i and adds 1 to it. It updates maxLength to be the maximum of its current value and the calculated length.
	    for (int i = 0; i < currentIndex; i++) 
	        if (arr[currentIndex] > arr[i]) 
	            maxLength = Math.max(maxLength, memoizedLIS(arr, i, dp) + 1);
	    //After the loop, it assigns maxLength to dp[currentIndex] for memoization and returns maxLength.
	    dp[currentIndex] = maxLength;
	    //After iterating through all elements in the input array, the lisMemo function returns the value of max, which represents the length of the longest increasing subsequence in the input array arr.
	    return maxLength;
	}

}
