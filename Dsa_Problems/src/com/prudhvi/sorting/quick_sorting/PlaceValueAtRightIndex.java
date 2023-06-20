package com.prudhvi.sorting.quick_sorting;

import java.util.Arrays;

public class PlaceValueAtRightIndex {
	/*
	  Given N array elements rearrange it
	  A[0],Should go its sorted position,
	  All elements <=array[0], go is left side of array[0]
	  all > array[0] should go to right of array[0]
	 
	 
	  */

	public static void main(String[] args) {
		int[] arr= {10,3,8,15,6,12,2,18,7,15,14};
		System.out.println("Given array    : "+Arrays.toString(arr));
		place0thIndexatrightplace(arr);
		Arrays.sort(arr);
		System.out.println("Sorted array   : "+Arrays.toString(arr));
		System.out.println("you can see in anwer array element arr[0] is 10 is correctly placed were it should be after sorting ");
		
		
		

	}

	private static void place0thIndexatrightplace(int[] arr) {
		//creating an array to store the answer
		int[] ans=new int[arr.length];
		int p1=0;
		int p2=arr.length-1;
		for(int i=1;i<arr.length;i++) {
			//if the element is less than or equal to array[0] element then we are adding that elements to left side
			if(arr[i]<=arr[0]) {
				ans[p1]=arr[i];
				//incrementing the pointer 1 to add next <= elements
				p1++;
			}
			//if the elements is greater than the array[0] we are adding that element into end of the array
			else if(arr[i]>arr[0]) {
				ans[p2]=arr[i];
				p2--;
			}//if p1==p2 here is the right position to place the array[0] element
			if(p1==p2) {
				ans[p1]=arr[0];
				break;
			}
		}
		
		System.out.println("Answer Array   : "+Arrays.toString(ans));
		
		
	}

}
