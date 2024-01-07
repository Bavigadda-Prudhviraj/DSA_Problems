package com.prudhvi.sorting.selection_sorting;

import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		int[] arr = {9,8,7,6,5,4,3,2,1};
		int n = 9;
		selectionSort(arr, n);
		System.out.println(Arrays.toString(arr));

	}
	/*
	Time complexity:
		The time complexity of selection sort is O(n^2), where n is the number of elements in the array. 
		This is because there are two nested loops, and for each element in the outer loop, the inner loop iterates through the remaining unsorted elements.
	
	Space complexity:
		The space complexity is O(1) because the algorithm sorts the array in-place, using only a constant amount of extra space for variables like minValue, minIndex, and temp. 
		The space required does not depend on the size of the input array.
	 */
	public static void selectionSort(int arr[], int n) {
	    for (int i = 0; i < n - 1; i++) {
	        int minValue = arr[i]; // Assume the current element is the minimum.
	        int minIndex = i;      // Index of the minimum element.

	        // Find the minimum element in the unsorted part of the array.
	        for (int j = i + 1; j < n; j++) {
	            if (arr[j] < minValue) {
	                minValue = arr[j]; // Update minValue if a smaller element is found.
	                minIndex = j;      // Update minIndex to the index of the smaller element.
	            }
	        }

	        // Swap the found minimum element with the first element in the unsorted part.
	        int temp = arr[i];
	        arr[i] = minValue;
	        arr[minIndex] = temp;
	    }
	}


}
