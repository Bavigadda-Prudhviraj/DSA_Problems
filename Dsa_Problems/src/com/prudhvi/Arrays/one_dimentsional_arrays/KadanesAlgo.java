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
	
		
		int[] arr1= {1, 2, 3, 4, -10};
		int ans1=kadanes(arr1);
		System.out.println(ans1);
		
		int[] arr3= {-163,-20};
		int ans2=kadanes(arr3);
		System.out.println(ans2);
		
		
	}
	public static int kadanes(int[] arr) {
		int sum=0;
		int ans=Integer.MIN_VALUE;
		for(int i=0;i<arr.length;i++) {
			if(sum<0) {
				ans=Math.max(ans, arr[i]); // this for corner case arr3
				//here if we found the  negative sum values then make the present element as sum element
				sum=arr[i];
			}
			else if(sum>=0) {
				sum+=arr[i];
				ans=Math.max(ans, sum);
			}
		}
		return ans;
		
	}

}
