package com.prudhvi.hashing;


import java.util.HashSet;

public class SubArrayWith0Sum {
	/*
	Given an array of integers A, find and return whether the given array contains a non-empty sub array with a sum equal to 0.
	If the given array contains a sub-array with sum zero return 1, else return 0.
	Problem Constraints
		1 <= |A| <= 100000
		-10^9 <= A[i] <= 10^9
	 */

	public static void main(String[] args) {
		int[] A = {4, -1, 1};
		int ans=subArrayWithSumZero(A);
		System.out.println(ans);

	}
	//subArrayWithSumZero function takes an array of integers as input and checks if there is a sub array whose sum is equal to zero. 
	//It does this by first computing the prefix sums of the input array and then using a HashSet to track the prefix sum values encountered.
	//If it finds any duplicate prefix sum value or encounters a prefix sum of zero during the process, it returns 1, indicating the presence of a sub array with a sum of zero. 
	//Otherwise, it returns 0, indicating the absence of such a sub array.
	public static int subArrayWithSumZero(int[] arr) {
		//his line declares a new array prefixSumArray of type long, with the same length as the input array arr. This array will store the prefix sums of the elements in arr.
		long[] prefixSumArray=new long[arr.length];
		//The first element of the prefix sum array is initialized with the value of the first element in the input array arr. 
		//This means prefixSumArray[0] will store the sum of the first element of the input array.
		prefixSumArray[0]=arr[0];
		for(int i=1;i<arr.length;i++) {
			//The current element of the prefix sum array prefixSumArray[i] is calculated by adding the previous element prefixSumArray[i - 1] to the current element of the input array arr[i]. 
			//This step computes the prefix sum for each element of the input array.
			prefixSumArray[i]=prefixSumArray[i-1]+arr[i];	
			//After calculating the prefix sum for the current element, it checks if the prefix sum is zero. 
			//If it is, it means there is a sub array with a sum of zero starting from the first element. 
			//In this case, the function returns 1
			if(prefixSumArray[i]==0) {
				return 1;
			}
		}
		//A HashSet named findDuplicateSet is created to store the prefix sum values encountered during the previous loop.
		HashSet<Long> findDuplicateSet=new HashSet<>();
		for(int i=0;i<prefixSumArray.length;i++) {
			//For each element in the prefix sum array, it checks if the current prefix sum value is already present in the findDuplicateSet. 
			//If it is, it means there is a sub array with a sum of zero starting from an earlier position in the input array. 
			//In this case, the function returns 1.
			if(findDuplicateSet.contains(prefixSumArray[i]) ) {
				return 1;
			}
			//f the current prefix sum value is not already present in the set, it is added to the findDuplicateSet
			else {
				findDuplicateSet.add(prefixSumArray[i]);
			}
		}
		//If the loop completes without finding any duplicate prefix sum values, it means there is no sub array with a sum of zero. The function returns 0.
		return 0;
	}

}
