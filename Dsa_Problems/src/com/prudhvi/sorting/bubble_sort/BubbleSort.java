package com.prudhvi.sorting.bubble_sort;

import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		int[] arr= {1,3,2,4,6,5,4,6,7,2,3};
		bubbleSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	public static void bubbleSort(int[] arr) {
		int n=arr.length;
		for(int i=0;i<n;i++) {
			boolean isArraySorted=true;
			for(int j=0;j<n-1-i;j++) {
				if(arr[j]>arr[j+1]) {
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
					isArraySorted=false;
				}
			}
			if(isArraySorted)
				break;
		}
		
	}

}
