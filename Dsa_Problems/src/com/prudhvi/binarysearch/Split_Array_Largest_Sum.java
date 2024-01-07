package com.prudhvi.binarysearch;
/*
Given an array arr[] of N elements and a number K., split the given array into K subarrays such that the maximum subarray sum achievable out of K subarrays formed is minimum possible. Find that possible subarray sum.
Example 1:
	Input:
	N = 4, K = 3
	arr[] = {1, 2, 3, 4}
	Output: 4
	Explanation:
		Optimal Split is {1, 2}, {3}, {4}.
		Maximum sum of all subarrays is 4,
		which is minimum possible for 3 splits. 
Example 2:
	Input:
	N = 3, K = 2
	A[] = {1, 1, 2}
	Output: 2
	Explanation:
		Splitting the array as {1,1} and {2} is optimal.
		This results in a maximum sum subarray of 2.
		Your Task:
You do not have to take any input or print anything. The task is to complete the function splitArray() which returns the maximum sum subarray after splitting the array into K subarrays such that maximum sum subarray is minimum possible.

Expected Time Complexity: O(N*log(sum(arr))).
Expected Auxiliary Space: O(1).
 */
public class Split_Array_Largest_Sum {

	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		int N = 4, K = 3;
		System.out.println(splitArray(arr, N, K));
		

	}
	public static int splitArray(int[] arr, int N, int K) {
	    int low = 0;
	    int high = 0;
	    // Initialize low to the maximum element in the array
	    // Initialize high to the sum of all elements in the array
	    for (int i = 0; i < N; i++) {
	        low = Math.max(arr[i], low);
	        high += arr[i];
	    }
	    // Initialize ans to low, as it is the minimum possible maximum sum
	    int ans = low;
	    // Perform binary search to find the minimum possible maximum sum
	    while (low <= high) {
	        int mid = low + (high - low) / 2;
	        // If the current mid satisfies the condition, update ans and search in the left half
	        if (check(mid, arr, K)) {
	            ans = mid;
	            high = mid - 1;
	        } else {
	            // If the current mid doesn't satisfy the condition, search in the right half
	            low = mid + 1;
	        }
	    }
	    return ans;
	}

	public static boolean check(int mid, int[] arr, int k) {
	    int sum = 0;
	    int split = 0;
	    // Check if it's possible to split the array into K subarrays with maximum sum <= mid
	    for (int ele : arr) {
	        if (ele > mid)
	            return false;
	        sum += ele;
	        // If the sum exceeds mid, start a new subarray
	        if (sum > mid) {
	            sum = ele;
	            split++;
	        }
	    }
	    // The total number of subarrays is split + 1
	    return split + 1 <= k;
	}

}
