package com.prudhvi.Arrays.one_dimentsional_arrays;

import java.util.Arrays;

public class MoveZerosToEnd {
	/*
	Given an integer array , move all 0's to the end of it while maintaining the relative order of the non-zero elements.
		Note:
			1..Time Complexity O(N)
			2.SpaceComplexity O(1),you must do this in-place without making a copy of the array.

 	Example 1:
		Input : [0,1,0,3,12]
		Output: [1,3,12,0,0]
	 */

	public static void main(String[] args) {
		int[] arr = {1, 2, 0, 4, 3, 0, 5, 0};
		int[] answerArr=moveZeros(arr);
		System.out.println(Arrays.toString(answerArr));

	}
	/*
	The method moveZeros takes an integer array  as input and returns the modified array with all zeros moved to the end while maintaining the relative order of non-zero elements.
	
	Time Complexity:
			The time complexity of this algorithm is O(n), where n is the size of the input array.
		 	The while loop iterates through the array once, and each iteration involves constant time operations.
		 	
	Space Complexity:
			The space complexity of this algorithm is O(1) since it uses only a constant amount of additional space to store the zero and nonZero pointers and a temporary variable.
			The input array  is modified in place, so no additional space is used for storing the result.
	 */
	public  static int[] moveZeros(int[] arr){
		//The variables zero and nonZero are used to keep track of the positions of zeros and non-zero elements, respectively. 
		int zero=0;//initializes a pointer zero that points to the first element in the array.
		int nonZero=0;//initializes another pointer nonZero that also points to the first element in the array.
		//The while loop runs until nonZero reaches the end of the array.
		//Inside the loop, the code checks for different conditions involving the elements at the zero and nonZero pointers.
		while(nonZero<arr.length){
			//If the element at the zero pointer is 0 and the element at the nonZero pointer is non-zero, it means we have found a zero that needs to be moved to the end of the array. 
			if(arr[zero]==0 && arr[nonZero]!=0) {
				////In this case, the elements at positions zero and nonZero are swapped, effectively moving the zero to the end.
				int temp=arr[zero];
				arr[zero]=arr[nonZero];
				arr[nonZero]=temp;
			}
			//If both the elements at the zero and nonZero pointers are 0, it means we have found a sequence of consecutive zeros. 
			else if(arr[zero]==0 && arr[nonZero]==0) {
				//In this case, we simply increment the nonZero pointer to skip over the sequence of zeros.
				nonZero++;
			}
			//If the element at zero is non-zero and the element at nonZero is also non-zero, it means we have found a non-zero element, and we need to move both zero and nonZero to the right to continue searching.
			else {
				zero++;
				nonZero++;
			}
		}
		return arr;
		
	}
	
	public static int[] moveZerosToEnd(int[] arr) {
		int zero=0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]!=0 && arr[zero]==0) {
				int temp=arr[i];
				arr[i]=arr[zero];
				arr[zero]=temp;
			}
			if(arr[zero]!=0) {
				zero++;
			}
		}
		return arr;
	}
	

}
