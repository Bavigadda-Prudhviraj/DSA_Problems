package com.prudhvi.Bit_manuplation;

public class SingleNumber {
	/*
	Given an array of integers A, every element appears twice except for one. Find that integer that occurs once.
	NOTE: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

	Problem Constraints
		1 <= |A| <= 2000000
		0 <= A[i] <= INTMAX
	 */

	public static void main(String[] args) {
		final int[] A = {1, 2, 2, 3, 1};
		int answer=singleNumber(A);
		System.out.println(answer);
		
	}
	/*
	The code uses the XOR operation to find the single element that appears only once in the array, while all other elements appear twice.
	
	Time Complexity: 
			The time complexity of this code is O(n), where n is the number of elements in the input array A. 
			The for loop iterates through all the elements once.

	Space Complexity: 
			The space complexity of this code is O(1), as it uses a constant amount of extra memory, independent of the input size. 
			The additional space is used for the variable answer, which requires a constant amount of memory.
	 */
	public static int singleNumber(final int[] A) {
		//answer is initialized to the first element of the array A. 
		//This is done because XOR (exclusive OR) operation with zero does not change the value, so answer starts as the first element of the array.
        int answer=A[0];
        //loop starts from the second element of the array (i = 1) and iterates through all the elements.
        for(int i=1;i<A.length;i++){
        	//the answer is updated using the XOR operator (^). XOR of two equal elements is 0, and XOR of an element with 0 is the element itself. 
        	//So, in each iteration, the answer is XORed with the current element A[i].
            answer=answer^A[i];
        }
        //Since all elements appear twice in the array except for the single element, when we XOR all the elements together, 
        //the duplicates will cancel each other out, leaving only the single element XORed with 0, which is the single element itself.
        
        //After the loop, the function returns the answer, which now holds the single element.
        return answer;
    }

}
