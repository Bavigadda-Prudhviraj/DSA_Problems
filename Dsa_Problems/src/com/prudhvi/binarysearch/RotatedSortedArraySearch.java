package com.prudhvi.binarysearch;



public class RotatedSortedArraySearch {
	/*
	 Given a sorted array of integers A of size N and an integer B, 
	 where array A is rotated at some pivot unknown beforehand.
	 For example, the array [0, 1, 2, 4, 5, 6, 7] might become [4, 5, 6, 7, 0, 1, 2].
	 Your task is to search for the target value B in the array. If found, return its index; otherwise, return -1.
	 You can assume that no duplicates exist in the array.
	 NOTE: You are expected to solve this problem with a time complexity of O(log(N)).
	 
	 Hint:
	 	Think of a modified version of the binary search. 
		If the pivot is known, the binary search becomes trivial
		as the array to either side of the pivot is sorted. 
	Approach:
		A naive solution is a linear search.
		To improve, let us break our approach into two steps. First, we find the pivot (the index of minimum in the array).
		Once we know the pivot, to search for x, we can do a conventional binary search in the left and right parts of the pivot in the array.
		Now, let us look at how binary search can be applied in this scenario to find the minimum.
		There are 2 cases:

		1)
		          		mid
		
		           		|
		
		   		6 7 8 9 1 2 3 4 5  
				arr[mid] > arr[end]
		
				The min lies in (mid, end]
				Mid is excluded from the range because arr[mid] > arr[end]

		2)
		         		mid
		
		          		| 
		
		  		6 7 8 9 1 2 3 4 5
				arr[mid] < arr[end]
		
				The min lies in [start, mid]

		3) Note: If there are duplicates, making either decision might be difficult, and hence linear search might be required.

			               mid
			
			                |
			
			1 1 1 1 2 0 1 1 1 1 1 1 1 1 1 1 1 
		arr[mid] = arr[end]

		Indecisive. We would need to explore the whole array.

	 */

	public static void main(String[] args) {
		int[] arr = { 9, 10, 3, 5, 6, 8 };
		int target= 5;
		int ele=binarySearchInRotatedArray(arr,target);
		System.out.println(ele);

	}
	public static int binarySearchInRotatedArray(int[] arr,int target) {
		int index=-1;
		int low=0;
		int high=arr.length-1;
		while (low<=high){
			int mid=(low+((high-low)/2));
			if(arr[mid]==target) {
				index=mid;
				break;
			}
			//if target is present at the second sorted part of the array
			if(target<arr[0]){
				//making the mid element to in the same sorted array were the element is there. 
				if(arr[mid]>=arr[0]){// if mid is greater then the 0th index then we are in the first array
					low=mid+1; //to move to second part right side so we are making low as mid+1
							   //it will run still mid will get into the array were target is present
				}
				else {//after mid is go at the right place apply the binary search
					if(arr[mid]<target) {
						low=mid+1;
					}
					else {
						high=mid-1;
					}
				}
				
			}
			else {//target in part 1,if the target is present at the part 1 sorted array
				//making the mid element to in the same sorted array were the element is there. 
				if(arr[mid]<arr[0]) {// if mid is less then the 0th index then we are in the first array
					high=mid-1;//to move to the left array we have to make high as mid-1
				}
				else {//apply binary search
					if(arr[mid]<target) {
						low=mid+1;
					}
					else {
						high=mid-1;
					}
				}
			}
		}
		return index;
	}
	

}
