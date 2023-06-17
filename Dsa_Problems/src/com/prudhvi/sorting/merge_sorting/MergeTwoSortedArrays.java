package com.prudhvi.sorting.merge_sorting;

import java.util.Arrays;

public class MergeTwoSortedArrays {
	/*
	 Given two sorted integer arrays A and B, merge B and A as one sorted array and return it as an output
	 Inputs: A = [4, 7, 9]
  			 B = [2, 11, 19]
	Output:[2, 4, 7, 9, 11, 19]
	
	Hint: Use of additional space is allowed. So, maybe you should try collecting the output in a separate array.
		  Note:
				You need two pointers at the head of each array,
				and you need to compare the values at the head of the arrays to get the current minimum.

				Since A is sorted, all values after the pointer are going to be bigger.
				Since B is sorted, all values after the pointer are going to be bigger.

				All values before the pointer have already been put in the result array.
				So, all you need to do is to choose the smaller value from the two heads and move on
		  Corner cases:
				what if pointer 1 reaches the end of the array first?
				What if pointer 2 reaches the end of the array first?

				If pointer 1 reaches the end we can just keep on putting the elements from B.
				in the result array while the pointer 2 does not reach the end.
				The same process goes for if pointer 2 reaches the end.
	  
	 */

	public static void main(String[] args) {
		int[] A = {4, 7, 9};
	  	int[] B = {2, 11, 19};
	  	int n=A.length;
		int m=B.length;
		int[] ansArr=new int[m+n];
		int p1=0; // Pointer for arr1
		int p2=0; // Pointer for arr2
		int i=0;  // Pointer for result
		// Compare elements from arr1 and arr2 and add the smaller element to the result array
		while (p1<n && p2<m){
			if(A[p1]<=B[p2]) {
				ansArr[i]=A[p1];
				p1++;
				i++;
				
			}
			else if(A[p1]>B[p2]) {
				ansArr[i]=B[p2];
				p2++;
				i++;
			}
			
			
		}
		 // Copy any remaining elements from arr1 (if any)
		while(p1<n) {
			ansArr[i]=A[p1];
			p1++;
			i++;	
		}
		// Copy any remaining elements from arr2 (if any)
		while(p2<m) {
			ansArr[i]=B[p2];
			p2++;
			i++;
		}
		
		System.out.println(Arrays.toString(ansArr));
		

	}

}
