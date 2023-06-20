package com.prudhvi.sorting.quick_sorting;

import java.util.Arrays;

public class QuickSortWithSubarrayWithOneElement {
		/*
		 Given N array elements & sub array [ s e ]such that,
		 array[s],should come to correct position and ever thing smaller from it on left side &
		  and anything greater on right side
		 */
	public static void main(String[] args) {
		int[] arr= {10,3,8,6,14,7,4,12,7,1};
		int start=2;
		int end=7;
		oneElementAtrightPlace(arr,start,end);
		

	}

		private static void oneElementAtrightPlace(int[] arr, int start, int end) {
			int p1=start+1;
			int p2=end;
			while (p1<=p2){
				if(arr[p1]<=arr[start]) {
					p1++;
				}
				else if(arr[p2]>arr[start]) {
					p2--;
				}
				else {
					int temp=arr[p1];
					arr[p1]=arr[p2];
					arr[p2]=temp;
				}
	
			}
			int temp=arr[p2];
			arr[p2]=arr[start];
			arr[start]=temp;
			System.out.println(Arrays.toString(arr));
			
		}

}
