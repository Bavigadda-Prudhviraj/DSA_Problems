package com.prudhvi.sorting.quick_sorting;

public class MaxChunksToMakeSorted {
	/*
	 The "Max Chunks To Make Sorted" problem involves determining the maximum number of chunks into which an array can be partitioned such that sorting each chunk individually will result in the entire array being sorted.
		Note:the range of the numbers are from 0 to n-1
		
	 	Observations :-
			1.Understanding the problem: 
				Given an array of integers, we want to determine the maximum number of chunks we can create such that sorting each chunk separately will lead to the entire array being sorted.

			2.Observations: 
				To devise an approach, we need to make some observations about the problem. Consider the sorted version of the given array. 
				Each chunk in the sorted array must correspond to a chunk in the original array. 
				Furthermore, each chunk in the original array must be sorted within itself. 
				This means that the maximum number of chunks in the original array is equal to the number of chunks in the sorted array.

		Approach: 
			1.Based on the above observations, we can approach the problem by comparing the elements of the original array with the sorted version of the array and count the number of chunks.
			2.Initialize two variables: maxChunks to keep track of the maximum number of chunks found so far and maxValue to keep track of the maximum value encountered.
			3.Iterate through the array, and for each element at index i, compare it with the element at the same index in the sorted array.
			4.Update maxValue to the maximum of the current element and maxValue.
			5.If maxValue is equal to i, it means that all elements up to index i are smaller than or equal to i, and therefore, they can form a chunk. Increment maxChunks by 1.
			6.After the iteration, maxChunks will hold the maximum number of chunks in the original array.




	 */

	public static void main(String[] args) {
		//int[] arr = {4, 3, 2, 1, 0};
		int[] arr= {2, 0, 1, 3};
		 int max=0;
	     int cnt=0;
	     for(int i=0;i<arr.length;i++){
	    	 max=Math.max(max,arr[i]);
	    	 //max==i give the number of elements are at the right place
	         if(max==i){
	        	 //if the elements are at the right place after sorting increment the answer
	        	 cnt++;
	          }
	     }
        System.out.println(cnt); 

	}

}
