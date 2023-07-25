package com.prudhvi.stack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreater {
	/*
	Problem Description
			Given an array A, find the next greater element G[i] for every element A[i] in the array.
			The next greater element for an element A[i] is the first greater element on the right side of A[i] in the array, A.
			More formally:
				G[i] for an element A[i] = an element A[j] such that 
    			j is minimum possible AND 
    			j > i AND
    			A[j] > A[i]
	Elements for which no greater element exists, consider the next greater element as -1.

	Problem Constraints
		1 <= |A| <= 105
		1 <= A[i] <= 107
	 */

	public static void main(String[] args) {
		int[] arr= {4, 5, 2, 10};
		int[] answer= nextGreater(arr);
		System.out.println(Arrays.toString(answer));

	}
	/*
	The method nextGreater takes an integer array as input and returns an array answer where each element represents the next greater element in the array
	
	Time Complexity:
			The time complexity of this algorithm is O(n), where n is the size of the input array.
			The loop iterates through all the elements in the array once, and each iteration involves constant time operations for stack manipulations.
	
	Space Complexity:
			The space complexity of this algorithm is O(n), where n is the size of the input array. 
			The algorithm uses additional space to store the answer array and the stack stack, both of which depend on the size of the input array. 
	 */
	public static int[] nextGreater(int[] arr) {
		//An array answer is created to store the next greater element for each element in the input array.
		//The size of the answer array is the same as the size of the input array.
        int[] ans=new int[arr.length];
        //A stack stack is created to keep track of potential next greater elements.
        Stack<Integer> stack=new Stack<>();
        //The loop starts from the last element of the input array array and iterates backwards from right to left.
        for(int i=arr.length-1;i>=0;i--){
        	//The while loop checks if the stack stack is not empty and the top element of the stack is smaller than or equal to the current element array[i].
        	//If this condition is true, it means the current element array[i] is the next greater element for the elements that are on top of the stack, so we remove those elements from the stack
            while(!stack.isEmpty() && stack.peek()<=arr[i]){
                stack.pop();
            }
            //If the stack stack is not empty after the while loop, it means there is a next greater element for the current element array[i]. 
            //We store that element in the answer array at index i
            if(!stack.isEmpty()){
                ans[i]=stack.peek();
            }
            //If the stack stack is empty after the while loop, it means there is no next greater element for the current element array[i], so we set the value at index i in the answer array to -1
            else{
                ans[i]=-1;
            }
            //Finally, we push the current element array[i] onto the stack to be considered for the next iteration.
            stack.push(arr[i]);
        }
        //After processing all elements in the input array , the answer array contains the next greater element for each element.
        return ans;
    }

}
