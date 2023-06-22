package com.prudhvi.sorting.count_sorting;

import java.util.Arrays;

public class CountSorting {
	/*
	 	Given an array A. Sort this array using Count Sort Algorithm and return the sorted array
	 	Problem Constraints
			1 <= |A| <= 10^5
			1 <= A[i] <= 10^5
		Note: For Counting sort constraints are very very important based on the constrains only we can choose can we go with this sorting algorithm or not.
		
		Hint: We will keep the count of frequency of each of the element of the array using a frequency array. Then we will move from 1 to size of the the frequency array and insert them in our final sorted array.


		
		Let's go through each step of the Counting Sort algorithm:
			1.Find the maximum value in the input array:
				i.The find findMaxValue in the input array. It iterates through each element and updates the max variable if a larger value is found.

			2.Create a frequency array and count the frequency of each element:
				i.We create a count array, FrequncyArray, with a length of max + 1, where max is the maximum value in the input array.
				ii.We iterate through the input array and increment the frequency for the corresponding element in countArray.

			3.Iterate over the frequency array:
				i.check weather values is greater than 0 or not,if frequency at the particular index say that that i index values repeatedly occurring in the input array for frequency[index] times
				ii.if add i element to original array for frequency[i] times

			4.By end of the above process the array we can see is in sorted order
		
		where N is the size and K is the maximum value of the given array
		Time Complexity : O(N + K)
							(K <= N) TC: O(N)
							(K>N log n) TC:O(k)
		Space Complexity : O(K)
			
			
	 */

	public static void main(String[] args) {
		int[] arr= {6,3,3,6,7,8,7,3,7};
	
		int[] ans=countSort(arr);
		System.out.println(Arrays.toString(ans));

	}

	private static int[] countSort(int[] A) {
		 int maxElement=Integer.MIN_VALUE;
		 // Find the maximum value in the input array
	        for(int i=0;i<A.length;i++){
	            if(A[i]>maxElement){
	                maxElement=A[i];
	            }
	        }
	     // Created a count array to store the frequencies
	        int[] frequencyArr=new int[maxElement+1];
	     // Step 1: Count the occurrences of each element
	        for(int i=0;i<A.length;i++){
	            frequencyArr[A[i]]=frequencyArr[A[i]]+1;//// Increment the count for the corresponding element
	        }
	        int index=0;
	        for(int i=0;i<frequencyArr.length;i++) {
	        	if(frequencyArr[i]!=0) {// checking weather values is there in the input array or not
	        		int ele=frequencyArr[i];
	        		while (ele>0){// adding that i element for frequencyArr[i] times
	        			A[index]=i; // Place the element in the correct position
	        			ele--; // Decrement the count for the corresponding element
	        			index++;
	        			
				
					}
	        		
	        	}
	        }
	        return A;
		
	}

}
