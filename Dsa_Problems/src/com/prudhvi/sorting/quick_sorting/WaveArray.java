package com.prudhvi.sorting.quick_sorting;

import java.util.Arrays;

public class WaveArray {
	/*
	 Given an array of integers A, sort the array into a wave-like array and return it.
		In other words, arrange the elements into a sequence such that
			a1 >= a2 <= a3 >= a4 <= a5..... 
		NOTE: If multiple answers are possible, return the lexicographically smallest one.
	 
	 */

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4};
		//sorting the array using the quick sorting
		quickRecursion(arr, 0, arr.length-1);
		//to get the wave array we have swap the alternate elements then we will get the wave array
		for(int i=0;i<arr.length-1;i=i+2){
            int temp=arr[i];
            arr[i]=arr[i+1];
            arr[i+1]=temp;
    }
		System.out.println(Arrays.toString(arr));
   
		
		
	}
private static void quickRecursion(int[] arr, int start, int end) {
		
		if(start>=end) {
			return;
		}
		//here are we are selecting the index passing that index will be placed at right place 
		int index=quickSort(arr,start,end);
		//calling recursion function for left part of the index to sort left part
		quickRecursion(arr, start, index-1);
		//calling the recursion function for right part of the present index
		quickRecursion(arr, index+1, end);	
	}
	private static int quickSort(int[] arr, int start, int end) {
		int randomIndex=(int) (Math.random()*(end-start)+start);
		int temp1=arr[randomIndex];
		arr[randomIndex]=arr[start];
		arr[start]=temp1;
		//here we are selecting p1 as start+1 because we have to place the start index at the right place
		int p1=start+1;
		int p2=end;
		//here we are considering happy element and unhappy element
		//we have to place <= array[0] left side , this are happy elements according to array[0]
		//we have to place > array[0] right side , this are happy elements according to array[0]
		//when we reach p1<=p2 all elements (happy elements) are placed 
		while (p1<=p2){
			//we faced an element which is greater then array[0], this is unhappy element
			if(arr[p1]<=arr[start]) {
				p1++;
			}
			else if(arr[p1]>arr[start]) {
				p2--;
			}
			//we faced an element which is array[p1] greater then array[0], this is unhappy element
			//we faced an element which is  array[p2] less then array[0], this is unhappy element
			//we have to place all greater elements right side and <= elements in left side
			//there are two unhappy elements we have to swap those elements to make happy elements
			else{
				int temp=arr[p1];
				arr[p1]=arr[p2];
				arr[p2]=temp;
			}	
		}
		//when p1 and p2 are crossed we have to swap with p2 element
		int temp=arr[start];
		arr[start]=arr[p2];
		arr[p2]=temp;
		return p2;
	}

}
