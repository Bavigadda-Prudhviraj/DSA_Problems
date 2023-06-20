package com.prudhvi.sorting.quick_sorting;

import java.util.Arrays;

public class PlaceFirstvalueAtIndexSCOne {

	public static void main(String[] args) {
		int[] arr= {10,3,8,15,6,12,2,18,7,15,14};
		System.out.println(Arrays.toString(arr));
		placeFirstValueAtRightIndex(arr);
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		

	}

	private static void placeFirstValueAtRightIndex(int[] arr) {
		int p1=1;
		int p2=arr.length-1;
		//here we are considering happy element and unhappy element
		//we have to place <= array[0] left side , this are happy elements according to array[0]
		//we have to place > array[0] right side , this are happy elements according to array[0]
		//when we reach p1<=p2 all elements (happy elements) are placed  
		while (p1<=p2) {
			//we faced an element which is greater then array[0], this is unhappy element
			if(arr[p1]<=arr[0]) {
				p1++;
			}
			else if(arr[p2]>arr[0]) {
				p2--;
			}
			//we faced an element which is array[p1] greater then array[0], this is unhappy element
			//we faced an element which is  array[p2] less then array[0], this is unhappy element
			//we have to place all greater elements right side and <= elements in left side
			//there are two unhappy elements we have to swap those elements to make happy elements
			else {
				int temp=arr[p1];
				arr[p1]=arr[p2];
				arr[p2]=temp;
			}
		}
		//when p1 and p2 are crossed we have to swap with p2 element
		int temp=arr[0];
		arr[0]=arr[p2];
		arr[p2]=temp;
		System.out.println(Arrays.toString(arr));
	}
	

}
