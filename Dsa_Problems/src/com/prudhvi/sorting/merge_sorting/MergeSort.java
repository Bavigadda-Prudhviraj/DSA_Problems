package com.prudhvi.sorting.merge_sorting;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] arr= {1,4,2,3,8,5,6,10};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	public static void sort(int[] arr) {
		mergeSort(arr,0,arr.length-1);
	}
	public static void  mergeSort(int[] arr,int leftPointer,int rightPointer) {
		if(leftPointer==rightPointer) {
			return;
		}
		int mid=leftPointer+(rightPointer-leftPointer)/2;
		mergeSort(arr, leftPointer, mid);
		mergeSort(arr, mid+1, rightPointer);
		merge(arr,leftPointer,mid,rightPointer);
	}
	public static void merge(int[] arr,int leftPointer,int mid,int rightPointer){
		int p1=leftPointer;
		int p2=mid+1;
		int index=0;
		int[] sortedArr=new int[rightPointer-leftPointer+1];
		while(p1<=mid && p2<=rightPointer){
			if(arr[p1]<=arr[p2]) {
				sortedArr[index]=arr[p1];
				index++;p1++;
			}else {
				sortedArr[index]=arr[p2];
				index++;p2++;
			}
		}
		while(p1<=mid) {
			sortedArr[index]=arr[p1];
			index++;p1++;
		}
		while((p2<=rightPointer)){
			sortedArr[index]=arr[p2];
			index++;p2++;
		}
		for(int i=leftPointer;i<=rightPointer;i++)
			arr[i]=sortedArr[i-leftPointer];
	}

}
