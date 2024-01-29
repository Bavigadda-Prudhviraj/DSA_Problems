package com.prudhvi.Arrays.prefix_sum;

import java.util.Arrays;

/*
	Given an array A of N integers. Construct prefix sum of the array in the given array itself.
 */
public class InplacePrefixSum {

	public static void main(String[] args) {
		int[] arr= {1, 2, 3, 4, 5};
		int[] prefixArr=prefixSusConstruction(arr);
		System.out.println(Arrays.toString(prefixArr));

	}
	public static int[] prefixSusConstruction(int[] arr) {
		for(int i=1;i<arr.length;i++) {
			arr[i]=arr[i-1]+arr[i];
		}
		return arr;
		
	}

}
