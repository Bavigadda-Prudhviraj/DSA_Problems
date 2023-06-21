package com.prudhvi.Arrays.one_dimentsional_arrays;

import java.util.Arrays;

public class MakeAllElementsUnique {
	/*
	 You are given an array A of N elements. You have to make all elements unique. To do so, in one step you can increase any number by one.
	 Find the minimum number of steps.
	 
	 Hint1:- The task is to make all the array elements unique, which needs to be done optimally in the minimum number of steps.Is there any way that sorting can help?
	 Hint2:- The task is to make all the array elements unique, which needs to be done optimally in the minimum number of steps.
			 Sort the Array and then start the traversing from the 2nd element.
			 If the current element is the same as the previous one, then make this element equal to (previous + 1) and count the steps.
	
	Time complexity: O(nlogn) because we are using inbuilt sort function
	space complexity: O(1)
	 */

	public static void main(String[] args) {
		int[] arr= {4,5,4,1,3,7,6,3,3};
		System.out.println(Arrays.toString(arr));
		int ans=stepsTomakeArrayUnique(arr);
		System.out.println(ans);
		System.out.println(Arrays.toString(arr)+" All elements are became unique");
	}

	private static int stepsTomakeArrayUnique(int[] arr) {
		/*
		 why sorting?
		 		1.when we sort the elements we can find duplicate elements by comparing adjacent elements
		 		2.If no adjacent elements are same then the entire array is unique
		 		 	Lets consider array after sorting [1,1,1,1]
		 		 	
		 		 	step one:if(Both elements are same) we can increase the next to one element by one then array will be [ 1,2,1,1]
		 		 			 we will update the answer as difference between same elements +1;
		 		 			 Note:make present element greater then previous element
		 		 			 	  To make this we have to get the previous element and add one to it
		 		 	step two:if(present element is smaller then previous element) at 1 and 2 indexes [1,2,1,1]
		 		 			  Note:make present element greater then previous element
		 		 			 	  To make this we have to get the previous element and add one to it[1,2,3,1];
		 		 	
		 */
		Arrays.sort(arr);
		int ans=0;
		for(int i=1;i<arr.length;i++) {
			if(arr[i]<=arr[i-1]) {// ==[1,1], < [2,1]
				int difference=arr[i-1]-arr[i];
				//we will update the answer as difference between same elements +1
				ans+=difference+1;
				arr[i]=arr[i-1]+1;
			}
		}		
		return ans;
	}

}
