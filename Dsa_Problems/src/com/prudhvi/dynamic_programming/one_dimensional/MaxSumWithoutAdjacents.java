package com.prudhvi.dynamic_programming.one_dimensional;

import java.util.Arrays;

public class MaxSumWithoutAdjacents {
	/*
	https://practice.geeksforgeeks.org/problems/max-sum-without-adjacents2430/1
	Given an array Arr of size N containing positive integers. Find the maximum sum of a subsequence such that no two numbers in the sequence should be adjacent in the array.
	Your Task:
		You don't need to read input or print anything. Your task is to complete the function findMaxSum() which takes the array of integers arr and n as parameters and returns an integer denoting the answer.
		Expected Time Complexity: O(N)
		Expected Auxiliary Space: O(1)
		Constraints:
		1 ≤ N ≤ 106
		1 ≤ Arr[i] ≤ 107
		*/
	public static void main(String[] args) {
		int[] arr={3, 2, 7, 10};//13
		int maxSum=maxSum(arr);
		System.out.println(maxSum);
		int maxSum1=maxSumCTC(arr);
		System.out.println(maxSum1);
		int maxSum2=maxSumMemo(arr);
		System.out.println(maxSum2);
		
	}
	//TC=O(N) SC=O(1)
	private static int maxSumCTC(int[] arr) {
		if(arr.length==1) {
			return arr[0];
		}
		int withoutEle=arr[0];
		int with=Math.max(arr[0],arr[1]);
		int currentMax=0;
		for(int i=2;i<arr.length;i++) {
			currentMax=Math.max(withoutEle+arr[i],with);
			withoutEle=with;
			with=currentMax;
			
		}
		return with;
	}
	/*
	Code calculates the maximum sum of non-adjacent elements in an array using dynamic programming. 
	The goal is to find the maximum sum such that no two selected elements are adjacent in the array.
	
	Time Complexity:
		The dynamic programming loop iterates through the entire array once.
		Therefore, the time complexity of this approach is O(n), where n is the length of the input array.
	Space Complexity:
		The space complexity is O(n) due to the dp array used to store the maximum sum of non-adjacent elements up to each index.
	 */
	private static int maxSum(int[] arr) {
		//If the array length is 1, there's only one element, so the maximum sum is simply that element
		if(arr.length==1) {
			return arr[0];
		}
		//Initialize an array dp of the same length as the input array to store the maximum sum at each position.
		int[] dp=new int[arr.length];
		dp[0]=arr[0];//the first element of the input array since there's only one element.
		dp[1]=Math.max(arr[0], arr[1]);//he maximum value between the first and second elements of the input array. This ensures that we start considering non-adjacent elements from the beginning.
		//Iterate through the array starting from the third element (index 2).
		for(int i=2;i<dp.length;i++) {
			//For each position i, update dp[i] as the maximum value between the sum of dp[i - 2] (maximum sum up to the previous non-adjacent element) and the current element arr[i], and dp[i - 1] (maximum sum up to the previous position). 
			//This way, we decide whether to include the current element in the sum or skip it and continue with the sum from the previous position.
			dp[i]=Math.max(dp[i-2]+arr[i],dp[i-1]);
		}
		return dp[arr.length-1];
	}
	//Memorization: TC=(N),SC(N)
	private static int maxSumMemo(int[] arr) {
	    if (arr.length == 1) {
	        return arr[0];
	    }
	    int[] memo = new int[arr.length];
	    Arrays.fill(memo, -1);
	    return memoMaxSum(arr, arr.length - 1, memo);
	}

	private static int memoMaxSum(int[] arr, int idx, int[] memo) {
	    if (idx < 0) {
	        return 0;
	    }
	    if (memo[idx] != -1) {
	        return memo[idx];
	    }
	    int chooseCurrent = arr[idx] + memoMaxSum(arr, idx - 2, memo);
	    int skipCurrent = memoMaxSum(arr, idx - 1, memo);
	    memo[idx] = Math.max(chooseCurrent, skipCurrent);
	    return memo[idx];
	}

	

}
