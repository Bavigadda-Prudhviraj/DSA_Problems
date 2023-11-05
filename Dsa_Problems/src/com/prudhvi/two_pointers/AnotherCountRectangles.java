package com.prudhvi.two_pointers;
/*
	Problem Description
		Given a sorted array of distinct integers A and an integer B, find and return how many rectangles with distinct configurations can be created using elements of this array as length and breadth whose area is lesser than B.
		(Note that a rectangle of 2 x 3 is different from 3 x 2 if we take configuration into view)
	
	Problem Constraints
		1 <= |A| <= 100000
		1 <= A[i] <= 109
		1 <= B <= 109
	
	Input Format
		The first argument given is the integer array A.
		The second argument given is integer B.
	
	Output Format
		Return the number of rectangles with distinct configurations with area less than B modulo (109 + 7).
 */
public class AnotherCountRectangles {

	public static void main(String[] args) {
		int[] A = {1, 2};
		int	  B = 5;
		int noOfRectangles=anotherCountRectangles(A, B);
		System.out.println(noOfRectangles);

	}
	/*
	This method is used to count the number of rectangles that can be formed by selecting two sides from an array of integers (arr) such that the product of the selected sides is less than the given value B. 
	The code follows a two-pointer approach to efficiently count such rectangles.
	 */
	public static int anotherCountRectangles(int[] arr, int B) {
		int n = arr.length;
	    long ans = 0;//The variable ans is used to store the count of rectangles that meet the criteria.
	    //It initializes two pointers l and r to the start and end of the array arr, respectively. 
	    //These pointers are used to represent two sides of the rectangle.
	    int l = 0, r = n-1;
	    //Inside a while loop that runs while l is less than or equal to r,
	    while(l <= r) {
	    	//it calculates the product of the elements at positions l and r in the array. It uses the multiply variable to store this product.
	    	long multiply = (long)arr[l] * (long)arr[r];
	    	//If the product multiply is less than the given value B, it means a valid rectangle can be formed with these sides. 
	    	//The code updates the ans by adding 2 * (r - l) + 1. 
	    	//The 2 * (r - l) part represents the number of rectangles that can be formed with the current l and various r positions. 
	    	//Adding 1 is for the rectangle formed by selecting l and r.
	        if(multiply < B) {
	            ans = ans + (2 * (r-l) + 1);
	            l++;
	        }
	        //If multiply is not less than B, it means the product is too large, and a smaller product is needed.
	        //So, the r pointer is moved one position to the left.
	        else {
	            r--;
	        }
	    }
	    return (int)(ans % 1000000007);
	}

}
