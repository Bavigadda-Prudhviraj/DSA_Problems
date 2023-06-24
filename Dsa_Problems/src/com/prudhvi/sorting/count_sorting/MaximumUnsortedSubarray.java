package com.prudhvi.sorting.count_sorting;

import java.util.Arrays;

public class MaximumUnsortedSubarray {
	/*
	 Given an array A of non-negative integers of size N. Find the minimum sub-array Al, Al+1 ,..., Ar such that if we sort(in ascending order) that sub-array, then the whole array should get sorted. If A is already sorted, output -1.
	Note : Follow 0-based indexing, while returning the sub-array's starting and ending indexes.
	 */

	public static void main(String[] args) {
		int[] arr = {1,1,10,10,15,10,15,10,10,15,10,15};
		System.out.println(Arrays.toString(arr));
		int[] ans=UnsortedPartOfArray(arr);
		System.out.println(Arrays.toString(ans));
		int[] arr1= {1,1,10,10,15,10,15,10,10,15,10,15};
		Arrays.sort(arr1);
		System.out.println(Arrays.toString(arr1));
	}

	private static int[] UnsortedPartOfArray(int[] arr) {
		
		int firstIndex=-1;
		int firstIndexValue=Integer.MIN_VALUE;
		int secondIndex=-1;
		int secondIndexValues=Integer.MIN_VALUE;
		for(int i=1;i<arr.length;i++){
			if(arr[i-1]>arr[i]) {
				firstIndex=i-1;
				firstIndexValue=arr[i-1];
			}
			
		}
		for(int i=arr.length-1;i>=1;i--) {
			if(arr[i-1]>arr[i]) {
				secondIndex=i;
				secondIndexValues=arr[i];
				
			}
		}
		System.out.println("first Indexvalue: "+firstIndexValue+" second Indexvalue:"+secondIndexValues);
		System.out.println("first Index: "+firstIndex +" second Index:"+secondIndex);
		if(firstIndex==0 && secondIndex==arr.length-1) {
			int[] ans= {0,arr.length-1};
		}
		if(firstIndex==-1) {
			int[] ans= {-1};
			return ans;
		}
		int minValue=Math.min(firstIndexValue, secondIndexValues);
		int maxValue=Math.max(firstIndexValue, secondIndexValues);
		
		System.out.println("Min Values: "+minValue+" MaxValues: "+ maxValue);
		for(int m=0;m<arr.length;m++) {
			if(arr[m]>minValue) {
				firstIndex=m;
				break;
			}
		}
		for(int m=arr.length-1;m>=0;m--) {
			if(arr[m]<maxValue) {
				secondIndex=m;
				break;
			}
		}
		int[] ans= {firstIndex,secondIndex};
		
		return ans;

		}
		
		
		
	
	}


