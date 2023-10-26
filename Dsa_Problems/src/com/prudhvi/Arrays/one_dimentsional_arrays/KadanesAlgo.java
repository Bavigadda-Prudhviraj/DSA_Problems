package com.prudhvi.Arrays.one_dimentsional_arrays;




/*
this kadane's algo is used to find the maximum sub array sum Question
Question:- Find the contiguous non-empty sub array within an array, A of length N, with the largest sum.

Algo:- here we will add all the contiguous elements until we get an negative elements
		once we get the negative elements we will make answer=0, and carry forward the answer by adding the elements 
*/

public class KadanesAlgo {

	public static void main(String[] args) {
		int[] arr= {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		int ans=kadanes(arr);
		System.out.println(ans);		
	}
	//Kadane's algorithm is an efficient way to find the maximum subarray sum in a given array. 
	//The time complexity of this algorithm is O(n), where n is the number of elements in the array, as it only requires a single pass through the array. 
	//The space complexity is O(1), as it uses a constant amount of extra space to store variables (sum and ans) regardless of the size of the input array.
	public static int kadanes(int[] arr) {
		int sum=0;//keep track of the current subarray sum.
		int ans=Integer.MIN_VALUE;// to store the maximum subarray sum found so far. It is initially set to Integer.MIN_VALUE to handle cases where all elements in the array are negative.
		//It then enters a loop that iterates through the elements of the input array arr from left to right.
		for(int i=0;i<arr.length;i++) {
			//If sum becomes negative, it means the current subarray should start from the current element because including any previous elements would reduce the sum. 
			//In this case, it updates ans with the current element (checking for the corner case of all negative elements in the array) and sets sum to the current element.
			if(sum<0) {
				ans=Math.max(ans, arr[i]); // this for corner case arr3
				//here if we found the  negative sum values then make the present element as sum element
				sum=arr[i];
			}
			//If sum is non-negative (greater than or equal to zero), it continues to extend the current subarray by adding the current element to sum. 
			//It also updates ans to track the maximum subarray sum found so far.
			else if(sum>=0) {
				sum+=arr[i];
				ans=Math.max(ans, sum);
			}
		}
		//After the loop, the function returns ans, which holds the maximum subarray sum.
		return ans;
		
	}

}
