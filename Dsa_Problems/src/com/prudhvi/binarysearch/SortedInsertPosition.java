package com.prudhvi.binarysearch;

public class SortedInsertPosition {
	/*
	 You are given a sorted array A of size N and a target value B.
	Your task is to find the index (0-based indexing) of the target value in the array.

	If the target value is present, return its index.
	**If the target value is not found, return the index were should that element should be placed.
	
	Note:-Your solution should have a time complexity of O(log(N))
	
	Hint:
		Given a sorted array and you need to find the index of given element B in the array.
		So, most optimized way to search an element in a sorted array is Binary Search.
		In order to apply binary search, follow three steps -
			1.Define the search space - In this question our complete array is the search space i.e. from index 0 to N-1.
			2.Check if middle element is the answer or not. If middle element is the answer, return its index.
			3.Decide whether to go in the left half or right half - Based on the value of middle element and given target try to decide this.
		If B is not present in the array, then think about the answer?
		
	Approach:
		Using the Binary Search algorithm to find the index.
		Start with two pointers, one pointing to the beginning of the array (low) and the other pointing to the end of the array (high).
		While the low pointer is less than or equal to the high pointer, do the following:
		Find the middle element of the array by calculating the average of low and high. Let’s call it mid.
		Compare the target value (B) with the element at the mid index in the array (A[mid]):
			1.If A[mid] == B, it means we have found the target value. Return the value of mid as the index of the target value.
			2.If A[mid] < B, it means the target value should be in the right half of the array. Update the low pointer to mid + 1 and continue searching in the right half.
			3.If A[mid] > B, it means the target value should be in the left half of the array. Update the high pointer to mid - 1 and continue searching in the left half.
			4.If the loop ends without finding the target value, it means the target value is not present in the array.
		In this case, return the value of mid as the index where the target value would be inserted to maintain the sorted order
	 */

	public static void main(String[] args) {
		int[] arr= {1, 3, 5, 6};
		int target = 5;
		int target2=7;
		int ans=binarySearch(arr,target2);
		System.out.println(ans);
	}
	public static int binarySearch(int[] arr,int B) {
		//Making this low point for dividing purpose
		 int low=0;
		//Making this low point for dividing purpose
	     int high=arr.length-1;
	    //Taking mid to compare with target
	     //why mid main purpose of mid is to ignore unwanted part in the input 
	     int mid=low+((high-low)/2);
	     while(low<=high){
	    	 //if we found the element at the right place we will return the index not the element
	    	 if(arr[mid]==B){
	    		 return mid;
	          }
	    	 //if mid is lesser then the target we will move right side
	    	 //because searching lesser side always gives less values not the target values
	          else if(arr[mid]<B){
	        	  //so we will update the low as mid+1,to search right side
	              low=mid+1;
	              //updating the mid values for further calculation, if we don't update the values then same mid always compared with different low and high redirects to infinite loop
	              mid=(int)low+((high-low)/2);
	            }
	    	//if mid is greater then the target we will move left side
	    	//because searching greater side always gives greater values not the target values
	          else{ // if(arr[mid] > B)  if we write this condition in else if will also works.but by the end it will comes to this > when because we are comparing == and <
	        	//so we will update the low as mid+1,to search right side
	        	 high=mid-1;
	        	//updating the mid values for further calculation, if we don't update the values then same mid always compared with different low and high redirects to infinite loop
	        	 mid=(int)low+((high-low)/2);
	            }
	        }
	     //Note here we are returning the mid because. the will be exactly at element if mid is exactly at element we are returning the index on loop
	     //if element not found means we will be at exactly maximum minimum value 
	     //by outer mid condition the mid will be there exactly we the element should be place 
	        return mid;
		
	}

}
