package com.prudhvi.sorting.quick_sorting;

import java.util.Arrays;

import com.prudhvi.sorting.selection_sorting.KthSmallestElement;

public class FindKthSamllestElement {
	//solved by sorting for sorting implemented quick sort
	//to know about quick sort go through " Actual quick sort class or problem "

	public static void main(String[] args) {
		int[] arr= {5,4,6,7,3,1,8,2};
		int kSmallestEelement=4;
		quickRec(arr,0,arr.length-1,kSmallestEelement);
		//kSmallestEelement-1 because it is 0 index based
		System.out.println(arr[kSmallestEelement-1]);
		

	}

	private static void quickRec(int[] arr, int start, int end, int kSmallestEelement) {
		if(start>=end) {
			return ;
		}
		int index=quickSort(arr,start,end,kSmallestEelement);
		quickRec(arr, start, index-1, kSmallestEelement);
		quickRec(arr, index+1, end, kSmallestEelement);
	}

	private static int quickSort(int[] arr, int start, int end, int kSmallestEelement) {
		int randomindex=(int) (Math.random()*(end-start)+start);
		int temp1=arr[randomindex];
		arr[randomindex]=arr[start];
		arr[start]=temp1;
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
		return p2;
		
		
	}

}
