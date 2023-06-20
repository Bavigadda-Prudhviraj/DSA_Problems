package com.prudhvi.sorting.insertion_sorting;

import java.util.Arrays;

public class InsertationSort {
	/*
	 Insertion sort is a simple sorting algorithm that works by dividing the input array into two parts: a sorted sub array and an unsorted sub array. 
	 Initially, the sorted sub array contains only the first element of the input array, and the unsorted sub array contains the remaining elements. 
	 The algorithm then iterates through the unsorted sub array, selecting one element at a time and inserting it into the correct position within the sorted sub array
	 
	 * */

	public static void main(String[] args) {
		int[] arr= {2,1,3,-2,0};
		insertationSort(arr);
		System.out.println(Arrays.toString(arr));

	}
	public static void insertationSort(int[] A) {
		for(int i=1;i<A.length-1;i++) {
			//here we are considering up to i-1 array is sorted we have to move A[i] element into sorted array and swap the until it gets at right place in the array
			int j=i-1;
			// Move elements of the sorted sub array that are greater than the key to one position ahead of their current position
			while (j>=0 && A[j]>=A[j+1]){
				int temp=A[j];
				A[j]=A[j+1];
				A[j+1]=temp;
				//here we are decreasing the j-- after swap is there there is a chance high values element is present left side then also we to swap we will compare at until 0th element
				j--;
			}
		}
		
		
	}

}
